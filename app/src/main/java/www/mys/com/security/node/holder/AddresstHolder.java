package www.mys.com.security.node.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.mayousheng.www.initview.ViewDesc;

import www.mys.com.security.node.R;
import www.mys.com.security.node.activity.AddressActivity;
import www.mys.com.security.node.base.BaseNoteHolder;
import www.mys.com.security.node.pojo.BaseRealNode;
import www.mys.com.security.node.pojo.ContactAddress;
import www.mys.com.security.node.utils.LogUtils;

public class AddresstHolder extends BaseNoteHolder<BaseRealNode> {

    @ViewDesc(viewId = R.id.text)
    public TextView text;

    public AddresstHolder(final Context context, View view) {
        super(context, view);
    }

    @Override
    public void inViewBind(final BaseRealNode baseRealNode) {
        super.inViewBind(baseRealNode);
        ContactAddress contactAddress = ContactAddress.fromJsonStr(String.valueOf(baseRealNode.data)
                , new ContactAddress(null));
        text.setText(
                contactAddress.name + " _ "
                        + contactAddress.phone + " _ "
                        + contactAddress.address + " _ "
                        + contactAddress.zipCode);
    }

    @Override
    public Class getActivityClass() {
        return AddressActivity.class;
    }
}
