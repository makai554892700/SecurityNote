package www.mys.com.security.node.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mayousheng.www.initview.ViewUtils;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ViewUtils.initAllView(BaseActivity.class, this);
    }

    public abstract int getLayout();
}
