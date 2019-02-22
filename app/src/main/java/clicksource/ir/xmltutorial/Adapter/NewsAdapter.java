package clicksource.ir.xmltutorial.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import clicksource.ir.xmltutorial.Models.NewsModel;
import clicksource.ir.xmltutorial.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context context;
    private List<NewsModel> newsModels;
    public NewsAdapter(Context context, List<NewsModel> newsModels){
        this.context=context;
        this.newsModels=newsModels;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.news_row,viewGroup,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        final NewsModel newsModel=newsModels.get(position);
        Picasso.get().load(newsModel.getImageUrl()).into(holder.imageView);
        holder.txtTitle.setText(newsModel.getTitle());
        holder.txtDate.setText(newsModel.getDate());
        holder.txtSource.setText(newsModel.getSource());
        holder.url=newsModel.getUrl();

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsModel.getUrl()));
                context.startActivity(browserIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return newsModels.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        String url;
        CardView parent;
        TextView txtTitle,txtDate,txtSource;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            parent=(CardView)itemView.findViewById(R.id.card_newsRow_parent);
            imageView=(ImageView)itemView.findViewById(R.id.img_newsRow_image);
            txtTitle=(TextView)itemView.findViewById(R.id.txt_newsRow_title);
            txtDate=(TextView)itemView.findViewById(R.id.txt_newsRow_date);
            txtSource=(TextView)itemView.findViewById(R.id.txt_newsRow_source);
        }
    }
}
