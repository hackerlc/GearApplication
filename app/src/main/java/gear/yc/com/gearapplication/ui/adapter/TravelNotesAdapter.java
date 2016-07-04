package gear.yc.com.gearapplication.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import gear.yc.com.gearapplication.R;
import gear.yc.com.gearapplication.pojo.TravelNoteBook;
import gear.yc.com.gearlibrary.intarface.view.GearRecyclerMore;
import gear.yc.com.gearlibrary.ui.adapter.GearRecyclerViewAdapter;

/**
 * GearApplication
 * Created by YichenZ on 2016/4/21 14:09.
 */
public class TravelNotesAdapter extends GearRecyclerViewAdapter<TravelNoteBook.Books,TravelNotesAdapter.Holder>implements GearRecyclerMore{
    FooterHolder  mFooterHolder;
    private static int oldPos=0;
    public TravelNotesAdapter(Context context,ArrayList<TravelNoteBook.Books> dates){
        super(context,dates);
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==1){
            view =LayoutInflater.from(mContext).inflate(
                    R.layout.item_foot,parent,false
            );
            mFooterHolder =new FooterHolder(view);
            return mFooterHolder;
        }
        view =LayoutInflater.from(mContext).inflate(
                R.layout.item_travel_notes,parent,false
        );
        view.setOnClickListener(this);
        Holder holder =new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        super.onBindViewHolder(holder,position);
        if (position + 1 == getItemCount())
            return;
//        if(oldPos-position<=0) {
//            holder.itemView.setTranslationY(CommonManager.getInstance().getMobileHeight(mContext));
//            holder.itemView.animate().translationY(0)
//                    .setStartDelay(100 * position)
//                    .setInterpolator(new DecelerateInterpolator(3.f))
//                    .setDuration(700)
//                    .start();
//        }
        TravelNoteBook.Books data=mData.get(position);
        Glide.with(mContext)
                .load(data.getHeadImage())
                .thumbnail(0.1f)
                .placeholder(R.drawable.bg_img)
                .crossFade()
                .into(holder.booksImg);
        holder.tv_title.setText(data.getTitle());
        holder.itemView.setTag(data);
        oldPos=position;
        data=null;
    }

    public class Holder extends RecyclerView.ViewHolder{
        private ImageView booksImg;
        private TextView tv_title;
        public Holder(View itemView) {
            super(itemView);
            booksImg=(ImageView)itemView.findViewById(R.id.sdv_books_img);
            tv_title=(TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    protected class FooterHolder extends Holder{
        public TextView mFooter;
        public FooterHolder(View itemView){
            super(itemView);
            mFooter=(TextView) itemView.findViewById(R.id.tv_foot_text);
        }
    }
    protected GearRecyclerMore mSetMoreViewListener= (more) -> {
        if(mFooterHolder!=null){
            if(more)
                mFooterHolder.mFooter.setText("加载更多信息中...");
            else
                mFooterHolder.mFooter.setText("已经加载全部信息");
        }
    };

    public void setMoreView(boolean more){
        mSetMoreViewListener.setMoreView(more);
    }
}
