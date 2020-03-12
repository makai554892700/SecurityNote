package www.mys.com.security.node.base;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mayousheng.www.initview.ViewDesc;

import www.mys.com.security.node.R;

public abstract class BaseLoadingActivity extends BaseActivity {

    @ViewDesc(viewId = R.id.common_loading)
    public RelativeLayout loading;
    @ViewDesc(viewId = R.id.common_left_img)
    public ImageView leftImg;
    @ViewDesc(viewId = R.id.common_right_img)
    public ImageView rightImg;
    private AnimatorSet animatorSet = new AnimatorSet();  //组合动画

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loading.setOnClickListener(getOnLoadClickListener());
        ObjectAnimator translationX = ObjectAnimator.ofFloat(leftImg, "translationX"
                , 0, getResources().getDimension(R.dimen.size_20dp));
        translationX.setRepeatCount(10000);
        translationX.setCurrentPlayTime(10000);
        translationX.setRepeatMode(ValueAnimator.REVERSE);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(rightImg, "translationX"
                , 0, -getResources().getDimension(R.dimen.size_20dp));
        translationY.setRepeatCount(10000);
        translationY.setCurrentPlayTime(10000);
        translationY.setRepeatMode(ValueAnimator.REVERSE);
        animatorSet.playTogether(translationX, translationY); //设置动画
    }

    protected void isLoading(final boolean isLoading) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loading.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    animatorSet.start();
                } else {
                    animatorSet.cancel();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        isLoading(false);
    }

    @Override
    protected void onStop() {
        isLoading(true);
        super.onStop();
    }

    private View.OnClickListener getOnLoadClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLoading(false);
            }
        };
    }

}
