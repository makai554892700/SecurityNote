package www.mys.com.security.node.adapter;

import android.content.Context;
import android.view.ViewGroup;

import www.mys.com.security.node.R;
import www.mys.com.security.node.base.BaseRecyclerAdapter;
import www.mys.com.security.node.base.BaseRecyclerHolder;
import www.mys.com.security.node.holder.AddresstHolder;
import www.mys.com.security.node.holder.BankCardtHolder;
import www.mys.com.security.node.pojo.BaseRealNode;

public class BankCardAdapter extends BaseRecyclerAdapter<BaseRealNode> {

    public BankCardAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerHolder<BaseRealNode> onCreateViewHolder(ViewGroup parent, int viewType) {
        rootView = layoutInflater.inflate(R.layout.item_bank_card, parent, false);
        return new BankCardtHolder(context, rootView);
    }

}
