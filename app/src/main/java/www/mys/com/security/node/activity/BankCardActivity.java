package www.mys.com.security.node.activity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mayousheng.www.initview.ViewDesc;

import www.mys.com.security.node.R;
import www.mys.com.security.node.base.BaseNoteActivity;
import www.mys.com.security.node.pojo.BankCard;
import www.mys.com.security.node.pojo.BaseRealNode;
import www.mys.com.security.node.utils.DataUtils;
import www.mys.com.security.node.utils.StringUtils;

public class BankCardActivity extends BaseNoteActivity {


    @ViewDesc(viewId = R.id.text_title)
    public EditText textTitle;
    @ViewDesc(viewId = R.id.bank_card_bank_name)
    public EditText bankCardBankName;
    @ViewDesc(viewId = R.id.bank_card_number)
    public EditText bankCardNumber;
    @ViewDesc(viewId = R.id.bank_card_name)
    public EditText bankCardName;
    @ViewDesc(viewId = R.id.bank_card_pass)
    public EditText bankCardPass;
    @ViewDesc(viewId = R.id.bank_card_branch)
    public EditText bankCardBranch;
    @ViewDesc(viewId = R.id.bank_card_phone)
    public EditText bankCardPhone;
    @ViewDesc(viewId = R.id.bank_card_zip_code)
    public EditText bankCardZipCode;
    @ViewDesc(viewId = R.id.bank_card_swift_code)
    public EditText bankCardSwiftCode;
    @ViewDesc(viewId = R.id.bank_card_cnaps)
    public EditText bankCardCnaps;
    private BankCard realData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!StringUtils.isEmpty(currentData.name)) {
            textTitle.setText(currentData.name);
        }
        realData = new BankCard(currentData.data);
        if (!StringUtils.isEmpty(realData.name)) {
            bankCardBankName.setText(realData.name);
        }
        if (!StringUtils.isEmpty(realData.number)) {
            bankCardNumber.setText(realData.number);
        }
        if (!StringUtils.isEmpty(realData.userName)) {
            bankCardName.setText(realData.userName);
        }
        if (!StringUtils.isEmpty(realData.pass)) {
            bankCardPass.setText(realData.pass);
        }
        if (!StringUtils.isEmpty(realData.branch)) {
            bankCardBranch.setText(realData.branch);
        }
        if (!StringUtils.isEmpty(realData.phone)) {
            bankCardPhone.setText(realData.phone);
        }
        if (!StringUtils.isEmpty(realData.swiftCode)) {
            bankCardZipCode.setText(realData.swiftCode);
        }
        if (!StringUtils.isEmpty(realData.number)) {
            bankCardSwiftCode.setText(realData.number);
        }
        if (!StringUtils.isEmpty(realData.CNAPS)) {
            bankCardCnaps.setText(realData.CNAPS);
        }
        currentData.type = BaseRealNode.Type.BANK_CARD;
        currentData.typeName = getAppBarTitle();
    }

    @Override
    public View.OnClickListener onSaveButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankCard bankCard = new BankCard(null);
                Editable editable = textTitle.getText();
                String tempStr = "";
                boolean sameTitle = false, isEmpty = true;
                if (editable != null) {
                    currentData.name = editable.toString();
                    sameTitle = editable.toString().equals(getString(R.string.demo_title));
                }
                editable = bankCardBankName.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    bankCard.name = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                editable = bankCardNumber.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    bankCard.number = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                editable = bankCardName.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    bankCard.userName = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                editable = bankCardPass.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    bankCard.pass = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                editable = bankCardBranch.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    bankCard.branch = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                editable = bankCardPhone.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    bankCard.phone = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                editable = bankCardZipCode.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    bankCard.zipCode = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                editable = bankCardSwiftCode.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    bankCard.swiftCode = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                editable = bankCardCnaps.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    bankCard.CNAPS = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                currentData.data = bankCard.toString();
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
        return getString(R.string.bank_card);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_bank_card;
    }
}
