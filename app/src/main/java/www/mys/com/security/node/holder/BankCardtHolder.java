package www.mys.com.security.node.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.mayousheng.www.initview.ViewDesc;

import www.mys.com.security.node.R;
import www.mys.com.security.node.activity.BankCardActivity;
import www.mys.com.security.node.base.BaseNoteHolder;
import www.mys.com.security.node.pojo.BankCard;
import www.mys.com.security.node.pojo.BaseRealNode;

public class BankCardtHolder extends BaseNoteHolder<BaseRealNode> {

    @ViewDesc(viewId = R.id.text)
    public TextView text;

    public BankCardtHolder(final Context context, View view) {
        super(context, view);
    }

    @Override
    public void inViewBind(final BaseRealNode baseRealNode) {
        super.inViewBind(baseRealNode);
        BankCard bankCard = BankCard.fromJsonStr(String.valueOf(baseRealNode.data)
                , new BankCard(null));
        text.setText(
                bankCard.name + " _ "
                        + bankCard.branch + " _ "
                        + bankCard.userName + " _ "
                        + bankCard.phone + " _ "
                        + bankCard.number + " _ "
                        + bankCard.zipCode + " _ "
                        + bankCard.swiftCode + " _ "
                        + bankCard.CNAPS + " _ "
                        + bankCard.pass);
    }

    @Override
    public Class getActivityClass() {
        return BankCardActivity.class;
    }
}
