package www.mys.com.security.node.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.mayousheng.www.initview.ViewDesc;

import www.mys.com.security.node.R;
import www.mys.com.security.node.activity.TextActivity;
import www.mys.com.security.node.base.BaseNoteHolder;
import www.mys.com.security.node.pojo.BaseRealNode;

public class TextHolder extends BaseNoteHolder<BaseRealNode> {

    public TextHolder(final Context context, View view) {
        super(context, view);
    }

    @Override
    public void inViewBind(final BaseRealNode baseRealNode) {
        super.inViewBind(baseRealNode);
        text.setText(baseRealNode.data);
    }

    @Override
    public Class getActivityClass() {
        return TextActivity.class;
    }
}
