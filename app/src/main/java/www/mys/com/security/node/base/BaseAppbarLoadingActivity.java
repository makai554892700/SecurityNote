package www.mys.com.security.node.base;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mayousheng.www.initview.ViewDesc;

import www.mys.com.security.node.R;

public abstract class BaseAppbarLoadingActivity extends BaseLoadingActivity {

    @ViewDesc(viewId = R.id.appbar_left)
    public ImageView appBarLeftIcon;
    @ViewDesc(viewId = R.id.appbar_right)
    public ImageView appBarRightIcon;
    @ViewDesc(viewId = R.id.appbar_title)
    public TextView appBarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appBarLeftIcon.setImageResource(getLeftIcon());
        appBarLeftIcon.setOnClickListener(onLeftIconClick());
        if (getRightIcon() != -1) {
            appBarRightIcon.setImageResource(getRightIcon());
            appBarRightIcon.setOnClickListener(onRightIconClick());
        }
        appBarTitle.setText(getAppBarTitle());
    }

    public int getRightIcon() {
        return -1;
    }

    public View.OnClickListener onRightIconClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
    }

    public int getLeftIcon() {
        return R.drawable.ic_menu;
    }

    public View.OnClickListener onLeftIconClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
    }

    public abstract String getAppBarTitle();

}
