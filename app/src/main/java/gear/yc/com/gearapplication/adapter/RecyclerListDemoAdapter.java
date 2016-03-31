package gear.yc.com.gearapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gear.yc.com.gearapplication.R;
import gear.yc.com.gearapplication.pojo.User;
import gear.yc.com.gearlibrary.ImageLoading.DisplayImg;

/**
 * GearApplication
 * Created by YichenZ on 2016/3/30 16:24.
 */
public class RecyclerListDemoAdapter extends RecyclerView.Adapter<RecyclerListDemoAdapter.DemoHolder>{

    List<User> mData;
    Context context;

    @Override
    public DemoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DemoHolder demoHolder=new DemoHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_recycler_view,parent,false));
        return demoHolder;
    }

    @Override
    public void onBindViewHolder(DemoHolder holder, int position) {
        User data =mData.get(position);
        holder.name.setText(data.getUser());
        holder.content.setText(data.getId());
        DisplayImg.getInstance().displayImg(context,holder.headPortrait,data.getHeadPortrait());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public List<User> getmData() {
        return mData;
    }

    public RecyclerListDemoAdapter setmData(List<User> mData) {
        this.mData = mData;
        return this;
    }

    public RecyclerListDemoAdapter setContext(Context context) {
        this.context = context;
        return this;
    }

    class DemoHolder extends RecyclerView.ViewHolder{

        ImageView headPortrait;
        TextView name;
        TextView content;

        public DemoHolder(View itemView) {
            super(itemView);
            headPortrait=(ImageView)itemView.findViewById(R.id.iv_head_portrait);
            name=(TextView)itemView.findViewById(R.id.tv_name);
            content=(TextView)itemView.findViewById(R.id.tv_content);
        }
    }
}
