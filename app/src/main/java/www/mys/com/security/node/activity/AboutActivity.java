package www.mys.com.security.node.activity;

import www.mys.com.security.node.R;
import www.mys.com.security.node.base.BaseAppbarLoadingActivity;

public class AboutActivity extends BaseAppbarLoadingActivity {

    @Override
    public String getAppBarTitle() {
        return getString(R.string.nav_more);
    }

    @Override
    public int getLeftIcon() {
        return R.drawable.ic_back;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_about;
    }
}
