package clicksource.ir.donyayesokhan.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import clicksource.ir.donyayesokhan.model.MyDatabase;
import clicksource.ir.donyayesokhan.R;
import clicksource.ir.donyayesokhan.activity.DetailActivity;
import clicksource.ir.donyayesokhan.model.Fish;

public class FishAdapter extends RecyclerView.Adapter<FishAdapter.FishViewHolder> {
    private Context context;
    private List<Fish> fishList;
    MyDatabase myDatabase;

    public FishAdapter(Context context) {
        this.fishList = new ArrayList<>();
        this.context = context;
        myDatabase = new MyDatabase(context);

    }

    @NonNull
    @Override
    public FishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fish_row, parent, false);
        return new FishViewHolder(view);
    }

    public void addDate(List<Fish> fishList) {
        this.fishList.addAll(fishList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final FishViewHolder holder, int position) {

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

        Cursor cursor=myDatabase.getInfos();
        for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            Log.i("LOG", cursor.getString(1));
            /*if(cursor.getString(1).equals(fish.getId())){
                holder.imgFav.setImageResource(R.drawable.ic_favorite_red);
                holder.imgFav.setContentDescription("red");
            }else{
                holder.imgFav.setImageResource(R.drawable.ic_favorite_white);
                holder.imgFav.setContentDescription("white");
            }*/
        }
        holder.txtSeenCount.setText(fish.getVisitCount());


        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> audios = fish.getAudio();
                List<String> video = fish.getVideo();
                List<String> images = fish.getImages();
                List<String> other = fish.getOther();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("text", fish.getText());
                intent.putExtra("id", fish.getId());
                intent.putExtra("cat2", fish.getCat2());
                intent.putExtra("cat3", fish.getCat3());
                intent.putExtra("source", fish.getSource());
                if (!audios.isEmpty()) {
                    intent.putExtra("audio", audios.get(0));
                }
                if (!video.isEmpty()) {
                    intent.putExtra("video", video.get(0));
                }
                if (!images.isEmpty()) {
                    intent.putExtra("image", images.get(0));
                }
                if (!other.isEmpty()) {
                    intent.putExtra("other", other.get(0));
                }


                context.startActivity(intent);

            }
        });

        holder.imgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.imgFav.getContentDescription().equals("white")) {
                    holder.imgFav.setImageResource(R.drawable.ic_favorite_red);
                    holder.imgFav.setContentDescription("red");
                    myDatabase.addInfo(fish.getId(), fish.getCat2(), fish.getText(), fish.getSource()
                            , fish.getCat3(), fish.getAudio().size() > 0 ? fish.getAudio().get(0) : "", fish.getVideo().size() > 0 ? fish.getVideo().get(0) : "", fish.getImages().size() > 0 ? fish.getImages().get(0) : ""
                            , fish.getOther().size() > 0 ? fish.getOther().get(0) : "");
                } else {
                    holder.imgFav.setImageResource(R.drawable.ic_favorite_white);
                    holder.imgFav.setContentDescription("white");
                    myDatabase.deleteRow(fish.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return fishList.size();
    }

  