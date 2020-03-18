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
import www.mys.com.security.node.pojo.CreditCard;
import www.mys.com.security.node.pojo.Receipt;
import www.mys.com.security.node.utils.DataUtils;
import www.mys.com.security.node.utils.StringUtils;
import www.mys.com.security.node.view.ItemView;

public class CreditCardActivity extends BaseNoteActivity {

    @ViewDesc(viewId = R.id.credit_card_name)
    public ItemView creditCardName;
    @ViewDesc(viewId = R.id.credit_card_user_name)
    public ItemView creditCardUserName;
    @ViewDesc(viewId = R.id.credit_card_number)
    public ItemView creditCardNumber;
    @ViewDesc(viewId = R.id.credit_card_year)
    public ItemView creditCardYear;
    @ViewDesc(viewId = R.id.credit_card_month)
    public ItemView creditCardMonth;
    @ViewDesc(viewId = R.id.credit_card_security_code)
    public ItemView creditCardSecurityCode;
    @ViewDesc(viewId = R.id.credit_card_phone)
    public ItemView creditCardPhone;
    @ViewDesc(viewId = R.id.credit_card_branch)
    public ItemView creditCardBranch;
    private CreditCard realData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!StringUtils.isEmpty(currentData.name)) {
            textTitle.setText(currentData.name);
        }
        realData = new CreditCard(currentData.data);
        if (!StringUtils.isEmpty(realData.name)) {
            creditCardName.setText(realData.name);
        }
        if (!StringUtils.isEmpty(realData.userName)) {
            creditCardUserName.setText(realData.userName);
        }
        if (!StringUtils.isEmpty(realData.number)) {
            creditCardNumber.setText(realData.number);
        }
        if (!StringUtils.isEmpty(realData.year)) {
            creditCardYear.setText(realData.year);
        }
        if (!StringUtils.isEmpty(realData.month)) {
            creditCardMonth.setText(realData.month);
        }
        if (!StringUtils.isEmpty(realData.securityCode)) {
            creditCardSecurityCode.setText(realData.securityCode);
        }
        if (!StringUtils.isEmpty(realData.phone)) {
            creditCardPhone.setText(realData.phone);
        }
        if (!StringUtils.isEmpty(realData.branch)) {
            creditCardBranch.setText(realData.branch);
        }
        currentData.type = BaseRealNode.Type.CREDIT_CARD;
        currentData.typeName = getAppBarTitle();
    }

    @Override
    public View.OnClickListener onSaveButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreditCard creditCard = new CreditCard(null);
                Editable editable = textTitle.getText();
                String tempStr = "";
                boolean sameTitle = false, isEmpty = true;
                if (editable != null) {
                    currentData.name = editable.toString();
                    sameTitle = editable.toString().equals(getString(R.string.demo_title));
                }
                editable = creditCardName.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    creditCard.name = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                editable = creditCardUserName.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    creditCard.userName = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                editable = creditCardNumber.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    creditCard.number = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                editable = creditCardYear.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    creditCard.year = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                editable = creditCardMonth.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    creditCard.month = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                editable = creditCardSecurityCode.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    creditCard.securityCode = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                editable = creditCardPhone.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    creditCard.phone = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                editable = creditCardBranch.getText();
                if (editable != null) {
                    tempStr += editable.toString();
                    creditCard.branch = editable.toString();
                    isEmpty = StringUtils.isEmpty(tempStr);
                }
                currentData.data = creditCard.toString();
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
        return getString(R.string.credit_card);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_credit_card;
    }
}
