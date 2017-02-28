package gear.yc.com.gearlibrary.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * GearApplication
 * 在最后调用super会自动加载Click事件
 * 如果使用view或者不最后调用super，click事件不会被加载
 * 也可以使用view.setOnClickLister(this)
 * Created by YichenZ on 2016/4/22 11:10.
 */
public class GearRecyclerViewAdapter<T,R extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<R>
        implements View.OnClickListener{
    protected ArrayList<T> mData;
    protected Context mContext;
    protected OnRecyclerViewItemClickListener<T> mListener;
    protected View view;

    public GearRecyclerViewAdapter(Context mContext, ArrayList<T> mData){
        this.mContext=mContext;
        this.mData=mData;
    }

    @Override
    public R onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(R holder, int position) {

    }

    @Override
    public int getItemCount() {
        if(mData==null)
            return 0;
        return mData.size();
    }

    public ArrayList<T> getData() {
        return mData;
    }

    public void setData(ArrayList<T> data) {
        mData = data;
    }

    @Override
    public void onClick(View v) {
        if(mListener!=null){
            mListener.onItemClick(v,(T)v.getTag());
        }
    }

    public interface OnRecyclerViewItemClickListener<T> {
        void onItemClick(View view, T data);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mListener = listener;
    }

}
