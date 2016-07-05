package gear.yc.com.gearapplication.ui.mvp.travelnotes;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import gear.yc.com.gearapplication.R;
import gear.yc.com.gearapplication.databinding.ItemTravelNotesBinding;
import gear.yc.com.gearapplication.pojo.TravelNoteBook;
import gear.yc.com.gearlibrary.ui.adapter.GearRecyclerViewAdapter;

/**
 * GearApplication
 * Created by YichenZ on 2016/4/21 14:09.
 */
public class TravelNotesAdapter extends GearRecyclerViewAdapter<TravelNoteBook.Books,TravelNotesAdapter.Holder>{
    private static int oldPos=0;
    public TravelNotesAdapter(Context context,ArrayList<TravelNoteBook.Books> dates){
        super(context,dates);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemTravelNotesBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.item_travel_notes,parent,false);
        binding.getRoot().setOnClickListener(this);
        Holder holder =new Holder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        super.onBindViewHolder(holder,position);
//        if(oldPos-position<=0) {
//            holder.itemView.setTranslationY(CommonManager.getInstance().getMobileHeight(mContext));
//            holder.itemView.animate().translationY(0)
//                    .setStartDelay(100 * position)
//                    .setInterpolator(new DecelerateInterpolator(3.f))
//                    .setDuration(700)
//                    .start();
//        }
        TravelNoteBook.Books data=mData.get(position);
        holder.binding.setBook(data);
        Glide.with(mContext)
                .load(data.getHeadImage())
                .thumbnail(0.1f)
                .placeholder(R.drawable.bg_img)
                .crossFade()
                .into(holder.binding.sdvBooksImg);
        oldPos=position;
        data=null;
    }

    public class Holder extends RecyclerView.ViewHolder{
        private final ItemTravelNotesBinding binding;
        public Holder(ItemTravelNotesBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }

}
