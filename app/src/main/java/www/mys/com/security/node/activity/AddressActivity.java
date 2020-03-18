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
import www.mys.com.security.node.pojo.ContactAddress;
import www.mys.com.security.node.utils.DataUtils;
import www.mys.com.security.node.utils.StringUtils;
import www.mys.com.security.node.view.ItemView;

public class AddressActivity extends BaseNoteActivity {

    @ViewDesc(viewId = R.id.address_name)
    public ItemView addressName;
    @ViewDesc(viewId = R.id.address_phone)
    public ItemView addressPhone;
    @ViewDesc(viewId = R.id.address_address)
    public ItemView address;
    @ViewDesc(viewId = R.id.address_zip_code)
    public ItemView addressZipCode;
    private ContactAddress realData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!StringUtils.isEmpty(currentData.name)) {
            textTitle.setText(currentData.name);
        }
        realData = new ContactAddress(currentData.data);
        if (!StringUtils.isEmpty(realData.name)) {
            addressName.setText(realData.name);
        }
        if (!StringUtils.isEmpty(realData.phone)) {
            addressPhone.setText(realData.phone);
        }
        if (!StringUtils.isEmpty(realData.address)) {
            address.setText(realData.address);
        }
        if (!StringUtils.isEmpty(realData.zipCode)) {
            addressZipCode.setText(realData.zipCode);
        }
        currentData.type = BaseRealNode.Type.ADDRESS;
        currentData.typeName = getAppBarTitle();
    }

    @Override
    public View.OnClickListener onSaveButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactAddress contactAddress = new ContactAddress(null);
                Editable editable = textTitle.getText();
                String tempStr = "";
                boolean sameTitle = false, isEmpty = true;
                if (editable != null) {
                    currentData.name = editable.toString();
                    sameTitle = editable.toString().equals(getString(R.string.demo_title));
                }
                editable = addressName.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    contactAddress.name = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                editable = addressPhone.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    contactAddress.phone = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                editable = address.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    contactAddress.address = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                editable = addressZipCode.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    contactAddress.zipCode = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                currentData.data = contactAddress.toString();
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
        return getString(R.string.address);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_address;
    }

}
