package zhangz.com.imoocmusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import zhangz.com.imoocmusic.R;
import zhangz.com.imoocmusic.activitys.AlbumListActivity;

/**
 * Created by lenovo on 2019/9/2.
 */

public class MusicGridAdapter extends RecyclerView.Adapter<MusicGridAdapter.ViweHolder> {
    private Context mContext;

    public MusicGridAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViweHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViweHolder(LayoutInflater.from(mContext).inflate(R.layout.item_grid_music,parent,false));
    }

    @Override
    public void onBindViewHolder(ViweHolder holder, int position) {
        Glide.with(mContext)
                .load("http://img4.imgtn.bdimg.com/it/u=2993120435,1961993013&fm=26&gp=0.jpg")
                .into(holder.mIvIcon);

        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AlbumListActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class ViweHolder extends RecyclerView.ViewHolder {

        private View mItemView;

        private ImageView mIvIcon;

        public ViweHolder(View itemView) {
            super(itemView);
            this.mItemView = itemView;
            mIvIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
        }
    }
}
