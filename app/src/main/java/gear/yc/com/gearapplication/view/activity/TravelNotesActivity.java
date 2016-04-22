package gear.yc.com.gearapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import gear.yc.com.gearapplication.BaseActivity;
import gear.yc.com.gearapplication.R;
import gear.yc.com.gearapplication.adapter.TravelNotesAdapter;
import gear.yc.com.gearapplication.manager.api.APIServiceManager;
import gear.yc.com.gearapplication.pojo.TravelNoteBook;
import gear.yc.com.gearapplication.view.custom.GearRecyclerItemDecoration;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * GearApplication
 * Created by YichenZ on 2016/4/20 15:59.
 */
public class TravelNotesActivity extends BaseActivity{
    RecyclerView mRecyclerView;
    ImageView mBack;
    TextView mTitle;
    FloatingActionButton mSearch;

    TravelNotesAdapter mNotesAdapter;

    ArrayList<TravelNoteBook.Books> mBookses=new ArrayList<>();
    int page =1;
    String query="";
    String initQuery="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        initData();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        query=getIntent().getStringExtra(J_FLAG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!initQuery.equals(query) && query!=null){
            initQuery=query;
            findData();
        }
    }

    @Override
    public void initUI() {
        super.initUI();
        setContentView(R.layout.activity_travel_notes);
        mRecyclerView=(RecyclerView)findViewById(R.id.rv_books);
        mBack=(ImageView)findViewById(R.id.iv_back);
        mBack.setOnClickListener(this);
        mTitle=(TextView)findViewById(R.id.tv_title);
        mTitle.setText("游记");
    }

    @Override
    public void initData() {
        super.initData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new GearRecyclerItemDecoration(this,LinearLayoutManager.VERTICAL));
        mNotesAdapter=new TravelNotesAdapter(this,mBookses);
        mRecyclerView.setAdapter(mNotesAdapter);
        mNotesAdapter.setOnItemClickListener((v,d) -> {
            strActivity(this,TravelNotesBookDetailsActivity.class,false,false
                    , ((TravelNoteBook.Books)d).getBookUrl()
                    , "游记详情");
        });

        mSearch=(FloatingActionButton)findViewById(R.id.fab_search);
        mSearch.setOnClickListener(this);
        findData();
    }

    private void findData() {
        mCSub.add(
                APIServiceManager.getInstance()
                .getTravelNotesAPI()
                .getTravelNotesList(query,page+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Message message=new Message();
                    message.what=s.getErrcode();
                    message.obj=s.getData().getBookses();
                    mHandler.sendMessage(message);
                })
        );
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_back:finish(true);break;
            case R.id.fab_search:
                strActivity(this,SearchBooksActivity.class,false,false);
                break;
        }
    }

    Handler mHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    mNotesAdapter.setData((ArrayList<TravelNoteBook.Books>) msg.obj);
                    mNotesAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
}
