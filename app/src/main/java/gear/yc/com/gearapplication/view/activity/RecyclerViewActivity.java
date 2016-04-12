package gear.yc.com.gearapplication.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import gear.yc.com.gearapplication.BaseActivity;
import gear.yc.com.gearapplication.R;
import gear.yc.com.gearapplication.adapter.RecyclerListDemoAdapter;
import gear.yc.com.gearapplication.pojo.User;
import gear.yc.com.gearapplication.view.custom.GearRecyclerItemDecoration;

/**
 * GearApplication
 * Created by YichenZ on 2016/3/30 14:10.
 */
public class RecyclerViewActivity extends BaseActivity{
    private RecyclerView recyclerView;
    private List<User> users;
    private RecyclerListDemoAdapter rListDemoAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initUI() {
        super.initUI();
        setContentView(R.layout.activity_recycler_view);
        recyclerView=(RecyclerView)findViewById(R.id.rv_data_list);
    }

    @Override
    public void initData() {
        super.initData();
        users=new ArrayList();
        for (int i='A';i<'z';i++){
            User user=new User();
            user.setUser("joker:"+(char)i);
            user.setHeadPortrait("");
            user.setId("89723");
            users.add(user);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new GearRecyclerItemDecoration(this,LinearLayoutManager.VERTICAL));
        rListDemoAdapter=new RecyclerListDemoAdapter()
                .setContext(this)
                .setmData(users);
        recyclerView.setAdapter(rListDemoAdapter);
    }
}
