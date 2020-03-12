package www.mys.com.security.node.activity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mayousheng.www.initview.ViewDesc;

import www.mys.com.security.node.R;
import www.mys.com.security.node.base.BaseNoteActivity;
import www.mys.com.security.node.pojo.BaseRealNode;
import www.mys.com.security.node.utils.DataUtils;
import www.mys.com.security.node.utils.StringUtils;

public class TextActivity extends BaseNoteActivity {

    @ViewDesc(viewId = R.id.text_title)
    public EditText textTitle;
    @ViewDesc(viewId = R.id.text_text)
    public EditText textText;

    @Override
    public View.OnClickListener onSaveButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable editable = textTitle.getText();
                boolean sameTitle = false, sameText = false;
                if (editable != null) {
                    currentData.name = editable.toString();
                    sameTitle = editable.toString().equals(getString(R.string.demo_title));
                }
                editable = textText.getText();
                if (editable != null) {
                    currentData.data = editable.toString();
                    sameText = editable.toString().equals(getString(R.string.demo_text));
                }
                if (StringUtils.isEmpty(currentData.name)) {
                    if (!StringUtils.isEmpty(currentData.data)) {
                        if (currentData.data.length() > 6) {
                            currentData.name = currentData.data.substring(0, 5);
                        } else {
                            currentData.name = currentData.data;
                        }
                    }
                }
                if (!StringUtils.isEmpty(currentData.data) && (!sameText || !sameTitle)) {
                    DataUtils.saveData(currentData);
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.save_empty)
                            , Toast.LENGTH_LONG).show();
                }
                finish();
            }
        };
    }

    @Override
    public String getAppBarTitle() {
        return getString(R.string.text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (currentData.name != null) {
            textTitle.setText(currentData.name);
        }
        if (currentData.data != null) {
            textText.setText(currentData.data);
        }
        currentData.type = BaseRealNode.Type.TEXT;
        currentData.typeName = getAppBarTitle();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_text;
    }
}
