package gear.yc.com.gearapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import gear.yc.com.gearapplication.BaseActivity;
import gear.yc.com.gearapplication.R;
import gear.yc.com.gearapplication.network.APIServiceManager;
import gear.yc.com.gearapplication.pojo.ResponseJson;
import gear.yc.com.gearapplication.pojo.TravelNoteBook;
import gear.yc.com.gearapplication.ui.adapter.TravelNotesAdapter;
import gear.yc.com.gearlibrary.rxbus.RxBus;
import gear.yc.com.gearlibrary.rxbus.annotation.Subscribe;
import gear.yc.com.gearlibrary.rxbus.thread.EventThread;
import gear.yc.com.gearlibrary.utils.ToastUtil;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * GearApplication
 * Created by YichenZ on 2016/4/20 15:59.
 */
public class TravelNotesActivity extends BaseActivity {
    XRecyclerView mRecyclerView;
    ImageView mBack;
    ImageView mLeft;
    TextView mTitle;
    FloatingActionButton mSearch;

    TravelNotesAdapter mNotesAdapter;
    LinearLayoutManager mLinearLayoutManager;
    GridLayoutManager mGridLayoutManager;

    //负责记录list样式
    boolean isLinear = true;

    ArrayList<TravelNoteBook.Books> mBookses = new ArrayList<>();
    int page = 1;
    String query = "";
    String initQuery = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.getInstance().register(this);
        initUI();
        initData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        query = getIntent().getStringExtra(J_FLAG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!initQuery.equals(query) && query != null) {
            initQuery = query;
            page = 1;
            loadData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unRegister(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    public void initUI() {
        super.initUI();
        setContentView(R.layout.activity_travel_notes);
        mRecyclerView = (XRecyclerView) findViewById(R.id.rv_books);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mBack = (ImageView) findViewById(R.id.iv_back);
        mBack.setOnClickListener(this);
        mLeft = (ImageView) findViewById(R.id.iv_left);
        mLeft.setVisibility(View.VISIBLE);
        mLeft.setImageResource(R.drawable.img_lv);
        mLeft.setOnClickListener(this);

        mTitle = (TextView) findViewById(R.id.tv_title);
        mTitle.setText("游记");

    }

    @Override
    public void initData() {
        super.initData();
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                loadData();
            }

            @Override
            public void onLoadMore() {
                loadData();
            }
        });
        mNotesAdapter = new TravelNotesAdapter(this, mBookses);
        mRecyclerView.setAdapter(mNotesAdapter);
        mNotesAdapter.setOnItemClickListener((v, d) ->
                strActivity(this, TravelNotesBookDetailsActivity.class, false, false
                        , ((TravelNoteBook.Books) d).getBookUrl()
                        , "游记详情"));

        mSearch = (FloatingActionButton) findViewById(R.id.fab_search);
        mSearch.setOnClickListener(this);
        loadData();
    }

    private void loadData() {
        if (!isNetworkConnected()) {
            return;
        }
        APIServiceManager.getInstance()
                .getTravelNotesAPI()
                .getTravelNotesList(query, page + "")
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if(s==null || s.getErrcode()!=0){
                        RxBus.getInstance().post(-1,s);
                    }else{
                        RxBus.getInstance().post(0,s.getData().getBookses());
                    }
                });
    }

    @Subscribe(tag = 0, thread = EventThread.MAIN_THREAD)
    private void dataBinding(ArrayList<TravelNoteBook.Books> bookses) {
        if (page == 1) {
            mNotesAdapter.setData(bookses);
            mRecyclerView.refreshComplete();
        } else {
            mNotesAdapter.getData().addAll(bookses);
            mRecyclerView.loadMoreComplete();
        }
        mNotesAdapter.notifyDataSetChanged();
        page++;
    }

    @Subscribe(tag = -1, thread = EventThread.MAIN_THREAD)
    private void dataError(ResponseJson responseJson) {
        ToastUtil.getInstance().makeShortToast(this,responseJson.getErrmsg());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish(true);
                break;
            case R.id.iv_left:
                if (isLinear) {
                    isLinear = false;
                    mLeft.setImageResource(R.drawable.img_gridview);
                    if (mGridLayoutManager == null) {
                        mGridLayoutManager = new GridLayoutManager(this, 2);
                    }
                    mGridLayoutManager.scrollToPositionWithOffset(mLinearLayoutManager.findFirstVisibleItemPosition(), 1);
                    mRecyclerView.setLayoutManager(mGridLayoutManager);
                } else {
                    isLinear = true;
                    mLeft.setImageResource(R.drawable.img_lv);
                    if (mLinearLayoutManager == null) {
                        mLinearLayoutManager = new LinearLayoutManager(this);
                    }
                    mLinearLayoutManager.scrollToPositionWithOffset(mGridLayoutManager.findFirstVisibleItemPosition(), -1);
                    mRecyclerView.setLayoutManager(mLinearLayoutManager);
                }
                break;
            case R.id.fab_search:
                strActivity(this, SearchBooksActivity.class, false, false);
                break;
        }
    }

}
