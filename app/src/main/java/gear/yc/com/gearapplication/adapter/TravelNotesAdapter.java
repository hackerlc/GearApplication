package gear.yc.com.gearapplication.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import gear.yc.com.gearapplication.R;
import gear.yc.com.gearapplication.pojo.TravelNoteBook;

/**
 * GearApplication
 * Created by YichenZ on 2016/4/21 14:09.
 */
public class TravelNotesAdapter extends GearRecyclerViewAdapter<TravelNoteBook.Books,TravelNotesAdapter.Holder>{

    public TravelNotesAdapter(Context context,ArrayList<TravelNoteBook.Books> dates){
        mContext=context;
        mData=dates;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        view =LayoutInflater.from(mContext).inflate(
                R.layout.item_travel_notes,parent,false
        );
        Holder holder =new Holder(view);
        super.onCreateViewHolder(parent,viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        super.onBindViewHolder(holder,position);
        TravelNoteBook.Books data=mData.get(position);
        holder.booksImg.setImageURI(Uri.parse(data.getHeadImage()));
        holder.tv_title.setText(data.getTitle());
        holder.itemView.setTag(data);
        data=null;
    }

    public class Holder extends RecyclerView.ViewHolder{
        SimpleDraweeView booksImg;
        TextView tv_title;
        public Holder(View itemView) {
            super(itemView);
            booksImg=(SimpleDraweeView)itemView.findViewById(R.id.sdv_books_img);
            tv_title=(TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
