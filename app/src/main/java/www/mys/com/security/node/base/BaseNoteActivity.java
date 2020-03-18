package www.mys.com.security.node.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mayousheng.www.initview.ViewDesc;

import www.mys.com.security.node.R;
import www.mys.com.security.node.pojo.BaseRealNode;
import www.mys.com.security.node.utils.DataUtils;

public abstract class BaseNoteActivity extends BaseAppbarLoadingActivity {

    @ViewDesc(viewId = R.id.text_title)
    public EditText textTitle;
    @ViewDesc(viewId = R.id.action_save)
    public FloatingActionButton saveButton;
    public BaseRealNode currentData;

    @Override
    public int getLeftIcon() {
        return R.drawable.ic_back;
    }

    @Override
    public int getRightIcon() {
        return R.drawable.ic_delete;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String data = intent.getStringExtra(StaticParam.DATA);
        if (data != null) {
            currentData = new BaseRealNode(data);
        } else {
            currentData = new BaseRealNode(null);
        }
        saveButton.setOnClickListener(onSaveButtonClick());
    }

    public abstract View.OnClickListener onSaveButtonClick();


    @Override
    public View.OnClickListener onLeftIconClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getString(R.string.not_save)
                        , Toast.LENGTH_LONG).show();
                finish();
            }
        };
    }

    @Override
    public View.OnClickListener onRightIconClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataUtils.deleteData(currentData.key);
                finish();
            }
        };
    }

}
