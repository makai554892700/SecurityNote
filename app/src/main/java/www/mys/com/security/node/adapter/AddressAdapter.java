package www.mys.com.security.node.adapter;

import android.content.Context;
import android.view.ViewGroup;

import www.mys.com.security.node.R;
import www.mys.com.security.node.base.BaseRecyclerAdapter;
import www.mys.com.security.node.base.BaseRecyclerHolder;
import www.mys.com.security.node.holder.AddresstHolder;
import www.mys.com.security.node.pojo.BaseRealNode;

public class AddressAdapter extends BaseRecyclerAdapter<BaseRealNode> {

    public AddressAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerHolder<BaseRealNode> onCreateViewHolder(ViewGroup parent, int viewType) {
        rootView = layoutInflater.inflate(R.layout.item_address, parent, false);
        return new AddresstHolder(context, rootView);
    }

}
