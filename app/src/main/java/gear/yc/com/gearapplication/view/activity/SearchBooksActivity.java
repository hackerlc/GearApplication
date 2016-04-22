package gear.yc.com.gearapplication.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import gear.yc.com.gearapplication.BaseActivity;
import gear.yc.com.gearapplication.R;

/**
 * GearApplication
 * Created by YichenZ on 2016/4/21 16:51.
 */
public class SearchBooksActivity extends BaseActivity{
    ImageView mBack;
    TextView mTitle;
    EditText mSearchBooks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    @Override
    public void initUI() {
        super.initUI();
        setContentView(R.layout.activity_search_books);
        mBack=(ImageView)findViewById(R.id.iv_back);
        mBack.setOnClickListener(this);
        mTitle=(TextView)findViewById(R.id.tv_title);
        mTitle.setText("搜索");
        mTitle.setOnClickListener(this);
        mSearchBooks=(EditText)findViewById(R.id.et_search_books);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_back:finish(true);break;
            case R.id.tv_title:
                //搜索
                strActivity(this,TravelNotesActivity.class,true,true,mSearchBooks.getText().toString());
                break;
        }
    }
}
