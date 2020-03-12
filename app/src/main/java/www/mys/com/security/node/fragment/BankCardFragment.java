package www.mys.com.security.node.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import www.mys.com.security.node.R;
import www.mys.com.security.node.adapter.AddressAdapter;
import www.mys.com.security.node.adapter.BankCardAdapter;
import www.mys.com.security.node.base.BaseNoteFragment;
import www.mys.com.security.node.pojo.BaseRealNode;
import www.mys.com.security.node.utils.DataUtils;

public class BankCardFragment extends BaseNoteFragment<BaseRealNode> {

    @Override
    public void onLoadMore(int currentPage) {
        initData(currentPage);
    }

    @Override
    public void initData(int page) {
        if (page == 0) {
            ArrayList<BaseRealNode> baseRealNodes = DataUtils.getResultData().get(BaseRealNode.Type.BANK_CARD);
            onResult(baseRealNodes, page);
        }
    }

    @Override
    protected int getLayoutId() {
        linearLayoutManager = new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false);
        return R.layout.fragment_base_note;
    }

    @Override
    protected void initView(View view) {
        recyclerAdapter = new BankCardAdapter(getContext());
    }
}
