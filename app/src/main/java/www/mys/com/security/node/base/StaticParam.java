package www.mys.com.security.node.base;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import www.mys.com.security.node.utils.ToolUtils;

public class StaticParam {

    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(2000, 20000
            , 2000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(2000));
    public static final RecyclerView.ItemDecoration DEFAULT_ITEM_DECORATION = new RecyclerView.ItemDecoration() {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0, 0, 0, 1);
        }
    };
    public static final String DATA = "activity_data";
    public static final String BASE_SDCARD = ToolUtils.getSDCardPath();
    public static final String BASE_ROOT_PARENT = BASE_SDCARD + File.separatorChar + "security_note";
    public static final String FILE_END = ".data";
    public static final String INIT_ED = "is_init_ed";
    public static final String SECURITY_CODE = "security_code";
    public static final String SAVE_ED = "is_save_ed";

}
