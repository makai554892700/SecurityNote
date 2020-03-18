package www.mys.com.security.node.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import www.mys.com.security.node.R;
import www.mys.com.security.node.utils.StringUtils;
import www.mys.com.security.node.utils.ToolUtils;

public class ItemView extends RelativeLayout {

    private int itemTitle, itemSrc;
    private TextView title;
    private ImageView rightImg;
    private EditText text;

    public ItemView(Context context) {
        super(context);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        if (null != attrs && !isInEditMode()) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ItemView);
            itemTitle = ta.getResourceId(R.styleable.ItemView_item_title, -1);
            itemSrc = ta.getResourceId(R.styleable.ItemView_item_src, -1);
            ta.recycle();
        }
        initView();
    }

    private void initView() {
        View childView = inflate(getContext(), R.layout.item_text_parse, this);
        title = childView.findViewById(R.id.item_name);
        text = childView.findViewById(R.id.item_text);
        rightImg = childView.findViewById(R.id.item_parse);
        if (itemTitle > 0 && title != null) {
            title.setText(itemTitle);
        }
        if (rightImg != null) {
            rightImg.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (text == null) {
                        Toast.makeText(getContext(), R.string.system_error, Toast.LENGTH_LONG).show();
                        return;
                    }
                    Editable editable = text.getText();
                    if (editable != null && !StringUtils.isEmpty(editable.toString())) {
                        ToolUtils.copyText(ToolUtils.getClipboardManager(getContext()), editable.toString());
                        Toast.makeText(getContext(), R.string.save_ed2, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), R.string.empty_text, Toast.LENGTH_LONG).show();
                    }
                }
            });
            if (itemSrc > 0) {
                rightImg.setImageResource(itemSrc);
            }
        }
    }

    public void setText(String data) {
        if (text != null) {
            text.setText(data);
        }
    }

    public Editable getText() {
        if (text != null) {
            return text.getText();
        }
        return null;
    }

    public void setRightImgOnClickListener(OnClickListener onClickListener) {
        if (rightImg != null) {
            rightImg.setOnClickListener(onClickListener);
        }
    }

}
