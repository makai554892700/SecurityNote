package www.mys.com.security.node.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mayousheng.www.initview.ViewDesc;

import java.util.ArrayList;

import www.mys.com.security.node.R;
import www.mys.com.security.node.pojo.BaseRealNode;
import www.mys.com.security.node.utils.OnLoadMoreListener;


public abstract class BaseNoteFragment<T extends BaseRealNode> extends BaseFragment {

    @ViewDesc(viewId = R.id.swipe_refresh_layout)
    public SwipeRefreshLayout swipeRefreshLayout;
    @ViewDesc(viewId = R.id.recycler_view)
    public RecyclerView recyclerView;
    protected BaseRecyclerAdapter<T> recyclerAdapter;
    protected LinearLayoutManager linearLayoutManager;
    protected OnLoadMoreListener onLoadMoreListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        onLoadMoreListener = new OnLoadMoreListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                BaseNoteFragment.this.onLoadMore(currentPage);
            }
        };
        recyclerView.setOnScrollListener(onLoadMoreListener);
        recyclerView.setLayoutManager(linearLayoutManager);
        initData(0);
        recyclerView.setAdapter(recyclerAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                StaticParam.executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        initData(0);
                        Activity activity = getActivity();
                        if (activity != null) {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    swipeRefreshLayout.setRefreshing(false);
                                }
                            });
                        }
                    }
                });
            }
        });
        recyclerView.addItemDecoration(StaticParam.DEFAULT_ITEM_DECORATION);
        return view;
    }

    public void refreshData() {
        onLoadMoreListener.reSetAll();
    }

    protected void onResult(ArrayList<T> responses, int page) {
        if (recyclerAdapter == null) {
            return;
        }
        if (page == 0) {
            recyclerAdapter.setData(responses);
            refreshData();
        } else {
            recyclerAdapter.addData(responses);
        }
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recyclerAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    public abstract void onLoadMore(int currentPage);

    public abstract void initData(int page);
}
