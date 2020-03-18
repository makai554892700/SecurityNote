package www.mys.com.security.node.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.mayousheng.www.initview.ViewDesc;

import www.mys.com.security.node.R;
import www.mys.com.security.node.activity.ReceiptActivity;
import www.mys.com.security.node.base.BaseNoteHolder;
import www.mys.com.security.node.pojo.BaseRealNode;
import www.mys.com.security.node.pojo.ContactAddress;
import www.mys.com.security.node.pojo.Receipt;

public class ReceipttHolder extends BaseNoteHolder<BaseRealNode> {

    public ReceipttHolder(final Context context, View view) {
        super(context, view);
    }

    @Override
    public void inViewBind(final BaseRealNode baseRealNode) {
        super.inViewBind(baseRealNode);
        Receipt receipt = Receipt.fromJsonStr(String.valueOf(baseRealNode.data)
                , new Receipt(null));
        text.setText(
                receipt.name + " _ "
                        + receipt.phone + " _ "
                        + receipt.number);
    }

    @Override
    public Class getActivityClass() {
        return ReceiptActivity.class;
    }
}
