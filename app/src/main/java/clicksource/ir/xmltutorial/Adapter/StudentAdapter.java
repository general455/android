package clicksource.ir.xmltutorial.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import clicksource.ir.xmltutorial.DetailActivity;
import clicksource.ir.xmltutorial.Models.Students;
import clicksource.ir.xmltutorial.MyDatabase;
import clicksource.ir.xmltutorial.R;

public class StudentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ADS = 1;
    private static final int STUDENTROW = 3;
    private Context context;
    private List<Students> studentsList;
    private List<Integer> testId;
    private List<Integer> positions;
    MyDatabase myDatabase;

    public StudentAdapter(Context context, List<Students> studentsList) {
        this.context = context;
        this.studentsList = studentsList;
        myDatabase = new MyDatabase(context);
        testId = new ArrayList<>();
        positions = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        if (studentsList.get(position).isAds()) {
            return ADS;
        } else {
            return STUDENTROW;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        if (viewType == ADS) {
            View view = layoutInflater.inflate(R.layout.ads_row, viewGroup, false);
            return new AdsViewHolder(view);
        } else {
            View view = layoutInflater.inflate(R.layout.student_row, viewGroup, false);
            return new StudentViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {




        final Students students = studentsList.get(position);
        if(students.isAds()){
            ((AdsViewHolder)viewHolder).imgAds.setImageResource(R.drawable.banner);
        }else{
            ((StudentViewHolder)viewHolder).txtName.setText(students.getName());
            ((StudentViewHolder)viewHolder).txtFamily.setText(students.getFamily());
            ((StudentViewHolder)viewHolder).txtField.setText(students.getField());
            ((StudentViewHolder)viewHolder).id=(students.getId());
            ((StudentViewHolder)viewHolder).imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDatabase.delete(students.getId());
                    studentsList.remove(students);
                    Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();
//                notifyDataSetChanged();
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, studentsList.size());

                }
            });

            ((StudentViewHolder)viewHolder).imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    myDatabase.updateRow(students.getId(), "nnnnnn");
                    students.setName("nnnnnn");
                    notifyItemChanged(position);


                }
            });

            ((StudentViewHolder)viewHolder).parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("name", students.getName());
                    intent.putExtra("family", students.getFamily());
                    intent.putExtra("field", students.getField());
                    context.startActivity(intent);
                    Toast.makeText(context, "id:" + students.getId() + "/ position:" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }





       // Log.i("LOG", "id:" + testId.get(position) + " and postion is:" + positions.get(position));
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtFamily, txtField;
        CardView parent;
        ImageView imgDelete, imgEdit;
        int id;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txt_studentRow_name);
            txtFamily = (TextView) itemView.findViewById(R.id.txt_studentRow_family);
            txtField = (TextView) itemView.findViewById(R.id.txt_studentRow_field);
            parent = (CardView) itemView.findViewById(R.id.card_studentRow_parent);
            imgDelete = (ImageView) itemView.findViewById(R.id.img_studentRow_delete);
            imgEdit = (ImageView) itemView.findViewById(R.id.img_studentRow_edit);
        }
    }

    public class AdsViewHolder extends RecyclerView.ViewHolder {

        ImageView imgAds;

        public AdsViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAds = (ImageView) itemView.findViewById(R.id.img_adsRow_ads);
        }
    }
}
