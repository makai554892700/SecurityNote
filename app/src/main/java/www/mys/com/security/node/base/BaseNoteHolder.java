package www.mys.com.security.node.base;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.mayousheng.www.initview.ViewDesc;

import java.util.Date;

import www.mys.com.security.node.R;
import www.mys.com.security.node.activity.TextActivity;
import www.mys.com.security.node.pojo.BaseRealNode;
import www.mys.com.security.node.utils.StringUtils;
import www.mys.com.security.node.utils.TimeEnum;
import www.mys.com.security.node.utils.TimeUtils;
import www.mys.com.security.node.utils.ToolUtils;

public abstract class BaseNoteHolder<T extends BaseRealNode> extends BaseRecyclerHolder<T> {

    @ViewDesc(viewId = R.id.card_view)
    public CardView rootView;
    @ViewDesc(viewId = R.id.title)
    public TextView title;
    @ViewDesc(viewId = R.id.time)
    public TextView time;
    @ViewDesc(viewId = R.id.appbar_title)
    public TextView appBarTitle;
    @ViewDesc(viewId = R.id.appbar_left)
    public ImageView appBarLeft;
    @ViewDesc(viewId = R.id.item_copy)
    public ImageView copy;
    @ViewDesc(viewId = R.id.text)
    public TextView text;
    protected int width, height;
    private T data;
    private Class activityClass;

    public BaseNoteHolder(final Context context, View view) {
        super(context, view);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            Display display = windowManager.getDefaultDisplay();
            if (display != null) {
                width = display.getWidth();
                height = display.getHeight();
            }
        }
        activityClass = getActivityClass();
    }

    @Override
    public void inViewBind(final T baseResponse) {
        data = baseResponse;
        title.setText(data.name);
        time.setText(TimeUtils.getTimeZoneDateString(new Date(data.updateAt)
                , 8, TimeEnum.FORMAT_DAY_SECOND));
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, activityClass);
                intent.putExtra(StaticParam.DATA, baseResponse.toString());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence charSequence = text.getText();
                if (charSequence != null && !StringUtils.isEmpty(charSequence.toString())) {
                    ToolUtils.copyText(ToolUtils.getClipboardManager(context), charSequence.toString());
                    Toast.makeText(context, R.string.save_ed2, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, R.string.empty_text, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public abstract Class getActivityClass();

}
