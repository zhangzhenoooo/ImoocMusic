package zhangz.com.imoocmusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import zhangz.com.imoocmusic.R;
import zhangz.com.imoocmusic.activitys.PlayMusicActivity;

/**
 * Created by lenovo on 2019/9/2.
 */

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {
    private Context mContext;
    private  View mItemView;
    private RecyclerView mRV;
    private  boolean isCalcaulationRvHeight = false;

    public MusicListAdapter(Context context,RecyclerView rv) {
        this.mContext = context;
        this.mRV = rv;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mItemView = LayoutInflater.from(mContext).inflate(R.layout.item_list_music, parent, false);
        return new ViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        setRecyclerViewHeight();
        Glide.with(mContext)
                .load("http://img5.imgtn.bdimg.com/it/u=3688092661,1885721330&fm=26&gp=0.jpg")
                .into(holder.ivIcon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PlayMusicActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    /**
     * 设置recycleView （list）的高度
     */
    private   void setRecyclerViewHeight(){
        if ( isCalcaulationRvHeight || mRV == null )  return;

        isCalcaulationRvHeight = true;
//        获取item高度
        RecyclerView.LayoutParams  itemView_LP = (RecyclerView.LayoutParams) mItemView.getLayoutParams();
//       获取item数量
        int itemCount =  getItemCount();
        int recycleViewHeight = itemView_LP.height * itemCount;
        LinearLayout.LayoutParams  recycleView_lp = (LinearLayout.LayoutParams) mRV.getLayoutParams();
        recycleView_lp.height = recycleViewHeight;
        mRV.setLayoutParams(recycleView_lp);


    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        private  View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            this.itemView = itemView;
        }
    }
}
