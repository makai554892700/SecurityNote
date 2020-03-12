package www.mys.com.security.node.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import www.mys.com.security.node.base.StaticParam;
import www.mys.com.security.node.pojo.AllRealData;
import www.mys.com.security.node.pojo.AllSaveData;
import www.mys.com.security.node.pojo.BankCard;
import www.mys.com.security.node.pojo.BaseRealNode;
import www.mys.com.security.node.pojo.BaseSaveNode;
import www.mys.com.security.node.pojo.ContactAddress;
import www.mys.com.security.node.pojo.CreditCard;
import www.mys.com.security.node.pojo.Receipt;
import www.mys.com.security.node.utils.file.FileUtils;

public class DataUtils {

    private static final String ALL_DATA = "all_data";

    public static synchronized HashMap<Integer, ArrayList<BaseRealNode>> getResultData() {
        HashMap<Integer, ArrayList<BaseRealNode>> result = new HashMap<>();
        AllRealData allRealData = getAllData();
        if (allRealData.datas != null) {
            ArrayList<BaseRealNode> tempData;
            for (BaseRealNode baseRealNode : allRealData.datas) {
                tempData = result.get(baseRealNode.type);
                if (tempData == null) {
                    tempData = new ArrayList<>();
                }
                tempData.add(baseRealNode);
                result.put(baseRealNode.type, tempData);
            }
        }
        return result;
    }

    private static BaseRealNode getRealNode(BaseSaveNode saveNode) {
        BaseRealNode result = new BaseRealNode(null);
        result.key = saveNode.key;
        result.type = saveNode.type;
        result.typeName = saveNode.typeName;
        result.name = saveNode.name;
        result.createAt = saveNode.createAt;
        result.updateAt = saveNode.updateAt;
        switch (saveNode.type) {
            case BaseRealNode.Type.ADDRESS:
                result.data = new ContactAddress(saveNode.data).toString();//联系地址
                break;
            case BaseRealNode.Type.RECEIPT:
                result.data = new Receipt(saveNode.data).toString();//发票信息
                break;
            case BaseRealNode.Type.BANK_CARD:
                result.data = new BankCard(saveNode.data).toString();//银行卡
                break;
            case BaseRealNode.Type.CREDIT_CARD:
                result.data = new CreditCard(saveNode.data).toString();//信用卡
                break;
            case BaseRealNode.Type.TEXT:
            default:
                result.data = saveNode.data;
        }
        return result;
    }

    public static synchronized AllRealData getAllData() {
        AllSaveData allSaveData = new AllSaveData(MySettings.getInstance().getStringSetting(ALL_DATA));
        AllRealData result = new AllRealData(null);
        result.version = allSaveData.version;
        result.datas = new ArrayList<>();
        if (allSaveData.datas != null) {
            for (BaseSaveNode saveNode : allSaveData.datas) {
                result.datas.add(getRealNode(saveNode));
            }
        }
        result.createAt = allSaveData.createAt;
        result.updateAt = allSaveData.updateAt;
        return result;
    }

    public static synchronized void deleteData(String key) {
        AllRealData allRealData = getAllData();
        if (allRealData.datas != null && key != null) {
            for (int i = 0; i < allRealData.datas.size(); i++) {
                if (allRealData.datas.get(i).key.equals(key)) {
                    allRealData.datas.remove(i);
                    saveAllData(allRealData);
                    break;
                }
            }
        }
    }

    public static synchronized boolean saveData(BaseRealNode data) {
        AllRealData allRealData = getAllData();
        if (allRealData.datas == null) {
            allRealData.datas = new ArrayList<>();
        }
        if (data.createAt == 0) {
            data.createAt = System.currentTimeMillis();
        }
        if (data.key == null || data.key.isEmpty()) {
            data.key = MD5Utils.MD5(data.name + data.type + data.createAt, false);
        }
        ArrayList<BaseRealNode> tempData = allRealData.datas;
        for (int i = 0; i < tempData.size(); i++) {
            if (data.key.equals(tempData.get(i).key)) {
                if (tempData.get(i).toString().equals(data.toString())) {
                    return false;
                } else {
                    tempData.remove(i);
                }
                break;
            }
        }
        allRealData.datas = new ArrayList<>();
        data.updateAt = System.currentTimeMillis();
        allRealData.datas.add(data);
        allRealData.datas.addAll(tempData);
        saveAllData(allRealData);
        return true;
    }

    public static synchronized void saveAllData(AllRealData allRealData) {
        if (allRealData.createAt == 0) {
            allRealData.createAt = System.currentTimeMillis();
        }
        allRealData.updateAt = System.currentTimeMillis();
        MySettings.getInstance().saveSetting(ALL_DATA, allRealData.toString());
    }

    public static synchronized int importData(AllRealData data) {
        int result = 0;
        if (data == null || data.datas == null) {
            return result;
        }
        for (BaseRealNode baseRealNode : data.datas) {
            if (saveData(baseRealNode)) {
                result++;
            }
        }
        return result;
    }

    public static File getExportFile() {
        FileUtils.sureDir(StaticParam.BASE_ROOT_PARENT);
        String fileName = StaticParam.BASE_ROOT_PARENT + File.separatorChar +
                TimeUtils.getTimeZoneDateString(new Date()
                        , 8, TimeEnum.FORMAT_FILE_SECOND) + StaticParam.FILE_END;
        return FileUtils.sureFile(fileName);
    }

}
