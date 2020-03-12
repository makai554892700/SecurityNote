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
import www.mys.com.security.node.pojo.Receipt;
import www.mys.com.security.node.utils.DataUtils;
import www.mys.com.security.node.utils.StringUtils;

public class ReceiptActivity extends BaseNoteActivity {

    @ViewDesc(viewId = R.id.text_title)
    public EditText textTitle;
    @ViewDesc(viewId = R.id.receipt_name)
    public EditText receiptName;
    @ViewDesc(viewId = R.id.receipt_number)
    public EditText receiptNumber;
    @ViewDesc(viewId = R.id.receipt_phone)
    public EditText receiptPhone;
    private Receipt realData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!StringUtils.isEmpty(currentData.name)) {
            textTitle.setText(currentData.name);
        }
        realData = new Receipt(currentData.data);
        if (!StringUtils.isEmpty(realData.name)) {
            receiptName.setText(realData.name);
        }
        if (!StringUtils.isEmpty(realData.number)) {
            receiptNumber.setText(realData.number);
        }
        if (!StringUtils.isEmpty(realData.phone)) {
            receiptPhone.setText(realData.phone);
        }
        currentData.type = BaseRealNode.Type.RECEIPT;
        currentData.typeName = getAppBarTitle();
    }

    @Override
    public View.OnClickListener onSaveButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Receipt receipt = new Receipt(null);
                Editable editable = textTitle.getText();
                String tempStr = "";
                boolean sameTitle = false, isEmpty = true;
                if (editable != null) {
                    currentData.name = editable.toString();
                    sameTitle = editable.toString().equals(getString(R.string.demo_title));
                }
                editable = receiptName.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    receipt.name = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                editable = receiptNumber.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    receipt.number = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                editable = receiptPhone.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    receipt.phone = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                currentData.data = receipt.toString();
                if (StringUtils.isEmpty(currentData.name)) {
                    if (!StringUtils.isEmpty(tempStr)) {
                        if (tempStr.length() > 6) {
                            currentData.name = tempStr.substring(0, 5);
                        } else {
                            currentData.name = tempStr;
                        }
                    }
                }
                if (!isEmpty || !sameTitle) {
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
        return getString(R.string.receipt);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_receipt;
    }

}
