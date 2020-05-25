package zhangz.com.imoocmusic.views;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by lenovo on 2019/9/2.
 * 自定义grid的分割线，设置网格分割线
 */

public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;

    public GridSpaceItemDecoration(int space,RecyclerView parent) {
        this.mSpace = space;
        getRecycleViewOffsets(parent);
    }

    /**
     *
     * @param outRect item的矩形边界
     * @param view itemView
     * @param parent RecycleView
     * @param state  parent的状态
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mSpace; //通过设置左偏移量实现分割线

    }

    private  void getRecycleViewOffsets(RecyclerView parent){
        /**
         * 通过item的margin设置第一个item左侧无分割线
         */
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) parent.getLayoutParams();
        layoutParams.leftMargin = -mSpace;
        parent.setLayoutParams(layoutParams);
    }
}
