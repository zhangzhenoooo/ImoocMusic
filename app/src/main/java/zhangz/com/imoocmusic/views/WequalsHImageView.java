package zhangz.com.imoocmusic.views;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by lenovo on 2019/9/2.
 */

public class WequalsHImageView extends android.support.v7.widget.AppCompatImageView {

    public WequalsHImageView(Context context) {
        super(context);
    }

    public WequalsHImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WequalsHImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);

    }
}
