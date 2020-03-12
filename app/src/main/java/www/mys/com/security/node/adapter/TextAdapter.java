package www.mys.com.security.node.adapter;

import android.content.Context;
import android.view.ViewGroup;

import www.mys.com.security.node.R;
import www.mys.com.security.node.base.BaseRecyclerAdapter;
import www.mys.com.security.node.base.BaseRecyclerHolder;
import www.mys.com.security.node.holder.TextHolder;
import www.mys.com.security.node.pojo.BaseRealNode;

public class TextAdapter extends BaseRecyclerAdapter<BaseRealNode> {

    public TextAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerHolder<BaseRealNode> onCreateViewHolder(ViewGroup parent, int viewType) {
        rootView = layoutInflater.inflate(R.layout.item_text, parent, false);
        return new TextHolder(context, rootView);
    }

}
