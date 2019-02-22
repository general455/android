package clicksource.ir.donyayesokhan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import clicksource.ir.donyayesokhan.model.MyDatabase;
import clicksource.ir.donyayesokhan.R;
import clicksource.ir.donyayesokhan.activity.DetailActivity;
import clicksource.ir.donyayesokhan.model.Fish;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavViewHolder> {

    Context context;
    List<Fish> fishList;
    MyDatabase myDatabase;
    public FavAdapter(Context context, List<Fish> fishList){
        this.fishList=fishList;
        this.context=context;
        myDatabase=new MyDatabase(context);
    }
    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.fish_row,parent,false);
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavViewHolder holder, int position) {

        final Fish fish = fishList.get(position);
        holder.txtTitle.setText(fish.getCat2());
        holder.txtId.setText(fish.getId());
        holder.txtCat.setText(fish.getCat3());
        holder.id = fish.getId();
        if (fish.getImages().size() > 0 || fish.getOther().size() > 0) {

            holder.imgAttach.setVisibility(View.VISIBLE);
        }

        if (fish.getVideo().size() > 0 || fish.getAudio().size() > 0) {
            holder.imgMediaStyle.setVisibility(View.VISIBLE);
        }

        if (fish.getCat5Photo() != null) {
            Picasso.get().load(fish.getCat5Photo()).into(holder.imgIcon);
        }


        holder.txtSeenCount.setText(fish.getVisitCount());


        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> audios = fish.getAudio();
                List<Str