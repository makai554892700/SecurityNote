package www.mys.com.security.node.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.mayousheng.www.initview.ViewDesc;

import www.mys.com.security.node.R;
import www.mys.com.security.node.activity.CreditCardActivity;
import www.mys.com.security.node.base.BaseNoteHolder;
import www.mys.com.security.node.pojo.BaseRealNode;
import www.mys.com.security.node.pojo.ContactAddress;
import www.mys.com.security.node.pojo.CreditCard;

public class CreditCardHolder extends BaseNoteHolder<BaseRealNode> {

    public CreditCardHolder(final Context context, View view) {
        super(context, view);
    }

    @Override
    public void inViewBind(final BaseRealNode baseRealNode) {
        super.inViewBind(baseRealNode);
        CreditCard creditCard = CreditCard.fromJsonStr(String.valueOf(baseRealNode.data)
                , new CreditCard(null));
        text.setText(
                creditCard.name + " _ "
                        + creditCard.userName + " _ "
                        + creditCard.phone + " _ "
                        + creditCard.number + " _ "
                        + creditCard.year + " _ "
                        + creditCard.month + " _ "
                        + creditCard.securityCode + " _ "
                        + creditCard.phone + " _ "
                        + creditCard.branch);
    }

    @Override
    public Class getActivityClass() {
        return CreditCardActivity.class;
    }
}
