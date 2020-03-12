package www.mys.com.security.node.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import www.mys.com.security.node.pojo.BaseRealNode;

public abstract class BaseRecyclerAdapter<T extends BaseRealNode> extends RecyclerView.Adapter<BaseRecyclerHolder<T>> {

    protected Context context;
    protected LayoutInflater layoutInflater;
    protected View rootView;
    protected ArrayList<T> data = new ArrayList<>();

    public BaseRecyclerAdapter(Context context) {
        this.context = context.getApplicationContext();
        layoutInflater = LayoutInflater.from(context.getApplicationContext());
    }

    public void addData(ArrayList<T> data) {
        if (data != null) {
            this.data.addAll(data);
        }
    }

    public void setData(ArrayList<T> data) {
        this.data.clear();
        addData(data);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerHolder<T> holder, int position) {
        holder.inViewBind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
