package www.mys.com.security.node.utils.file;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;

import www.mys.com.security.node.utils.LogUtils;
import www.mys.com.security.node.utils.StringUtils;

public class FileUtils {

    public static byte[] readFile2Bytes(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            try {
                byte[] result = new byte[(int) file.length()];
                new FileInputStream(file).read(result);
                return result;
            } catch (Exception e) {
                LogUtils.log("readFile2Bytes error.e=" + e);
            }
        } else {
            LogUtils.log("file not exist.");
        }
        return null;
    }

    public static String inputStream2String(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        StreamUtils.inputStream2OutputStream(inputStream, outputStream);
        String result = new String(outputStream.toByteArray(), Charset.forName("utf-8"));
        CloseUtils.closeSilently(inputStream);
        CloseUtils.closeSilently(outputStream);
        return result;
    }

    public static String readFile(String filePath) {
        final StringBuilder result = new StringBuilder();
        readLine(filePath, new LineBack() {
            @Override
            public void onStart(String fileName) {
            }

            @Override
            public void onLine(String line) {
                result.append(line).append("\n");
            }

            @Override
            public void onEnd(String fileName) {
            }
        });
        return result.toString();
    }

    public static File sureDir(String dir) {
        if (dir == null) {
            return null;
        }
        File tempFile = new File(dir);
        if (!tempFile.exists()) {
            if (!tempFile.mkdir()) {
                return null;
            }
        }
        return tempFile;
    }

    public static boolean delFile(String filePath) {
        if (filePath == null) {
            return true;
        }
        File tempFile = new File(filePath);
        if (tempFile.exists()) {
            try {
                tempFile.delete();
            } catch (Exception e) {
                LogUtils.log("Delete file error.e=" + e);
            }
        }
        return tempFile.exists();
    }

    public static File sureFile(String filePath) {
        if (filePath == null) {
            return null;
        }
        File tempFile = new File(filePath);
        if (!tempFile.exists()) {
            try {
                if (!tempFile.createNewFile()) {
                    return null;
                }
            } catch (Exception e) {
                return null;
            }
        }
        return tempFile;
    }

    public static void listAllFile(String dirPath, final AllBack all) {
        if (all == null) {
            return;
        }
        all.onStart(dirPath);
        final LineBack lineBack = new LineBack() {
            private String fileName;

            @Override
            public void onStart(String fileName) {
                this.fileName = fileName;
                all.onFileStart(fileName);
            }

            @Override
            public void onLine(String line) {
                all.onLine(fileName, line);
            }

            @Override
            public void onEnd(String fileName) {
                all.onFileEnd(fileName);
            }
        };
        DirBack dirBack = new DirBack() {
            @Override
            public void onStart(String fileName) {
            }

            @Override
            public void onDir(String line) {
                File file = new File(line);
                if (file.isDirectory()) {
                    if (all.needReadLine(line)) {
                        listAllFile(line, all);
                    }
                } else {
                    if (all.needReadLine(line)) {
                        readLine(line, lineBack);
                    }
                }
            }

            @Override
            public void onEnd(String fileName) {
            }
        };
        listDir(dirPath, dirBack);
        all.onEnd(dirPath);
    }

    public static void listDir(String dirPath, DirBack dirBack) {
        if (dirBack == null) {
            return;
        }
        dirBack.onStart(dirPath);
        if (StringUtils.isEmpty(dirPath)) {
            dirBack.onEnd(dirPath);
            return;
        }
        File file = new File(dirPath);
        if (!file.exists() || !file.isDirectory()) {
            LogUtils.log("file is not exists or is not a directory");
        }
        File[] children = file.listFiles();
        if (children != null) {
            for (int i = 0; i < children.length; i++) {
                dirBack.onDir(children[i].getAbsolutePath());
            }
        }
        dirBack.onEnd(dirPath);
    }

    public static File sureFileIsNew(String filePath) {
        if (filePath != null && !filePath.isEmpty()) {
            File file = new File(filePath);
            if (file.exists()) {
                boolean isOk = file.delete();
            }
            try {
                boolean isOk = file.createNewFile();
            } catch (Exception e) {
                LogUtils.log("e=" + e);
                return null;
            }
            return file;
        }
        return null;
    }

    public static boolean strToFile(File file, StringBuilder data) {
        if (data == null) {
            return false;
        }
        AppendFileUtils appendFileUtils = AppendFileUtils.getInstance(file);
        final int len = 102400;
        while (data.length() > 0) {
            int tempLen;
            if (data.length() >= len) {
                tempLen = len;
            } else {
                tempLen = data.length();
            }
            appendFileUtils.appendString(data.substring(0, tempLen));
            data.delete(0, tempLen);
        }
        appendFileUtils.endAppendFile();
        return true;
    }

    public static void appendStr2File(File file, String data) {
        AppendFileUtils appendFileUtils = AppendFileUtils.getInstance(file);
        appendFileUtils.appendString(data);
        appendFileUtils.endAppendFile();
    }

    public static boolean byte2File(File file, byte[] data) {
        if (data == null || file == null) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data);
        } catch (Exception e) {
            return false;
        } finally {
            CloseUtils.closeSilently(fileOutputStream);
        }
        return true;
    }

    public static boolean inputStream2File(InputStream inputStream, File file) {
        boolean result = false;
        if (inputStream == null || file == null || !file.exists()) {
            LogUtils.log("message is error.");
        } else {
            OutputStream outputStream;
            try {
                outputStream = new FileOutputStream(file);
            } catch (Exception e) {
                LogUtils.log("e=" + e);
                return false;
            }
            result = StreamUtils.inputStream2OutputStream(inputStream, outputStream);
            CloseUtils.closeSilently(outputStream);
        }
        return result;
    }

    public static boolean readLine(String filePath, LineBack lineBack) {
        boolean result = false;
        if (lineBack != null && filePath != null && !filePath.isEmpty()) {
            lineBack.onStart(filePath);
            File file = new File(filePath);
            if (file.exists()) {
                FileReader fileReader = null;
                try {
                    fileReader = new FileReader(file);
                } catch (Exception e) {
                    LogUtils.log("e=" + e);
                    return false;
                }
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                result = onLine(lineBack, bufferedReader);
                CloseUtils.closeReader(bufferedReader);
                CloseUtils.closeReader(fileReader);
            }
            lineBack.onEnd(filePath);
        } else {
            LogUtils.log("filePath is null or lineBack is null;filePath=" + filePath + ";lineBack=" + lineBack);
        }
        return result;
    }

    public static boolean isExist(String filePath) {
        return filePath != null && isExist(new File(filePath));
    }

    public static boolean isExist(File file) {
        return file != null && file.exists();
    }

    private static boolean onLine(LineBack lineBack, BufferedReader bufferedReader) {
        StringBuilder tempString = new StringBuilder();
        try {
            tempString.append(bufferedReader.readLine());
        } catch (Exception e) {
            LogUtils.log("e=" + e);
            return false;
        }
        while (!"null".equals(tempString.toString())) {
            lineBack.onLine(tempString.toString());
            tempString.delete(0, tempString.length());
            try {
                tempString.append(bufferedReader.readLine());
            } catch (Exception e) {
                LogUtils.log("e=" + e);
                return false;
            }
        }
        return true;
    }

    public static <T extends Serializable> File object2File(File file, T object) {
        if (file == null || !file.exists()) {
            LogUtils.log("file not exists.file=" + file);
            return null;
        }
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
        } catch (Exception e) {
            LogUtils.log("e=" + e);
            return null;
        } finally {
            CloseUtils.closeSilently(fileOutputStream);
            CloseUtils.closeSilently(objectOutputStream);
        }
        return file;
    }

    public static <T extends Serializable> T file2Object(File file) {
        if (file == null || !file.exists()) {
            LogUtils.log("file not exists.file=" + file);
            return null;
        }
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        T result = null;
        try {
            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);
            result = (T) objectInputStream.readObject();
        } catch (Exception e) {
            LogUtils.log("e=" + e);
        } finally {
            CloseUtils.closeSilently(objectInputStream);
            CloseUtils.closeSilently(fileInputStream);
        }
        return result;
    }

    public interface AllBack {
        public void onStart(String fileName);

        public boolean needReadLine(String fileName);

        public void onFileStart(String fileName);

        public void onFileEnd(String fileName);

        public void onLine(String fileName, String line);

        public void onEnd(String fileName);
    }

    public interface LineBack {
        public void onStart(String fileName);

        public void onLine(String line);

        public void onEnd(String fileName);
    }

    public interface DirBack {
        public void onStart(String fileName);

        public void onDir(String line);

        public void onEnd(String fileName);
    }

}
