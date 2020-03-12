package www.mys.com.security.node.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

import www.mys.com.security.node.pojo.BaseRealNode;

public class MainAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    public MainAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList == null ? null :
                (fragmentList.size() > position ? fragmentList.get(position) : null);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }

    public void notifyData(List<Fragment> fragmentList, List<BaseRealNode> data) {
        this.fragmentList = fragmentList;
        notifyDataSetChanged();
    }

}
