package zhangz.com.imoocmusic.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import zhangz.com.imoocmusic.R;
import zhangz.com.imoocmusic.adapters.MusicListAdapter;

public class AlbumListActivity extends BaseActivity {
    private RecyclerView mRVList;
    private MusicListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        initView();
    }

    private  void  initView(){
        initNavBar(true,"专辑列表",true);
        mRVList = (RecyclerView) findViewById(R.id.rv_list);
        mRVList.setLayoutManager( new LinearLayoutManager(this));
        mRVList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mAdapter = new MusicListAdapter(this,null);
        mRVList.setAdapter(mAdapter);
    }
}
