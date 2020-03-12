package www.mys.com.security.node.activity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mayousheng.www.initview.ViewDesc;

import www.mys.com.security.node.R;
import www.mys.com.security.node.base.BaseAppbarLoadingActivity;
import www.mys.com.security.node.utils.StringUtils;
import www.mys.com.security.node.utils.ToolUtils;

public class SecurityCodeActivity extends BaseAppbarLoadingActivity {

    @ViewDesc(viewId = R.id.text_security_code)
    public EditText securityCode;
    @ViewDesc(viewId = R.id.action_random)
    public FloatingActionButton actionRandom;
    @ViewDesc(viewId = R.id.action_save)
    public FloatingActionButton actionSave;
    private String currentSecurityCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentSecurityCode = ToolUtils.getSecurityCode();
        initEvent();
        updateState();
    }

    private void initEvent() {
        actionRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSecurityCode = ToolUtils.getNewSecurityCode();
                updateState();
                Toast.makeText(getApplicationContext(), R.string.security_code_random
                        , Toast.LENGTH_SHORT).show();
            }
        });
        actionSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable editable = securityCode.getText();
                if (editable != null && !StringUtils.isEmpty(editable.toString())) {
                    ToolUtils.saveSecurityCode(editable.toString());
                    Toast.makeText(getApplicationContext(), R.string.success
                            , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.security_code_empty
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateState() {
        securityCode.setText(currentSecurityCode);
    }

    @Override
    public String getAppBarTitle() {
        return getString(R.string.nav_security_code);
    }

    @Override
    public int getLeftIcon() {
        return R.drawable.ic_back;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_security_code;
    }
}
