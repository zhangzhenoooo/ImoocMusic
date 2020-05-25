package zhangz.com.imoocmusic.activitys;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import zhangz.com.imoocmusic.R;
import zhangz.com.imoocmusic.adapters.MusicGridAdapter;
import zhangz.com.imoocmusic.adapters.MusicListAdapter;
import zhangz.com.imoocmusic.views.GridSpaceItemDecoration;

public class MainActivity extends BaseActivity {

    private RecyclerView mRVGridTjgd,mRvList;//推荐歌单
    private MusicGridAdapter mGridAdapter;
    private MusicListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        initNavBar(true, "慕课音乐", true);
        mRVGridTjgd = (RecyclerView) findViewById(R.id.rv_grid_tjgd);
        mRVGridTjgd.setLayoutManager(new GridLayoutManager(this,3));// 同一行显示三个
        mRVGridTjgd.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.albumMarginSize),mRVGridTjgd));
        mRVGridTjgd.setNestedScrollingEnabled(false);
        mGridAdapter = new MusicGridAdapter(this);
        mRVGridTjgd.setAdapter(mGridAdapter);

        mRvList = (RecyclerView) findViewById(R.id.rv_list);
        mRvList.setLayoutManager( new LinearLayoutManager(this)); //设置布局
        mRvList.setNestedScrollingEnabled(false);
        mRvList.addItemDecoration( new DividerItemDecoration(this,DividerItemDecoration.VERTICAL)); //设置分割线
        mListAdapter = new MusicListAdapter(this,mRvList);
        mRvList.setAdapter(mListAdapter);
    }
}
