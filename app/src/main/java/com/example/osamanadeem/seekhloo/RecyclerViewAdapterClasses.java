package com.example.osamanadeem.seekhloo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapterClasses extends RecyclerView.Adapter<RecyclerViewAdapterClasses.ViewHolder>{



    ArrayList<classattributes> attr = new ArrayList<>();

    private Context mContext;

    public RecyclerViewAdapterClasses(Context mContext)
    {
        this.mContext = mContext;

    }


    public RecyclerViewAdapterClasses(ArrayList<classattributes> attr, Context mContext) {

        this.mContext = mContext;
        this.attr = attr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classes_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {



        holder.classname.setText(attr.get(position).getName());

        Glide.with(mContext).load(typetopic(attr.get(position).getType())).into(holder.imageView);
        holder.cardView.setClickable(true);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, ClassExistedActivity.class);
                intent.putExtra("subjectname",attr.get(position).getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return attr.size();
    }

    public class   ViewHolder extends RecyclerView.ViewHolder{
        TextView classname;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.class_img);
            classname = itemView.findViewById(R.id.class_name_card);
            cardView = itemView.findViewById(R.id.classcard);
        }
    }

    String typetopic(String type)
    {
        if (type.equals("Arts and Humanities"))
        {

            return "https://firebasestorage.googleapis.com/v0/b/seekhloo.appspot.com/o/ClassBackgroundImages%2Farts_humanities_424_283_0.jpg?alt=media&token=9e1aad87-f691-4326-a28f-8eeef77064d3";
        }
        else if (type.equals("Business"))
        {


            return "https://firebasestorage.googleapis.com/v0/b/seekhloo.appspot.com/o/ClassBackgroundImages%2Fbusiness.jpg?alt=media&token=c75415a0-f910-48af-9c05-38b41134ab06";

        }
        else if (type.equals("Computer Sciences"))
        {

            return "https://firebasestorage.googleapis.com/v0/b/seekhloo.appspot.com/o/ClassBackgroundImages%2Fbtech-csbs2018.jpg?alt=media&token=7753cc57-2332-4349-98b9-6f09d9ad1505";

        }else if (type.equals("Health"))
        {
            return "https://firebasestorage.googleapis.com/v0/b/seekhloo.appspot.com/o/ClassBackgroundImages%2Fdownload.png?alt=media&token=aeda7f04-c7d8-4fe8-9afa-da755048d30e";


        }else if (type.equals("Mathematics"))
        {

            return "https://firebasestorage.googleapis.com/v0/b/seekhloo.appspot.com/o/ClassBackgroundImages%2Fmaxresdefault.jpg?alt=media&token=a2dc83c5-b3f0-4bd2-b4ba-4f0b3879c1e4";

        }else if (type.equals("Physical Science"))
        {

            return "";


        }else if (type.equals("Social Studies"))
        {

            return "https://firebasestorage.googleapis.com/v0/b/seekhloo.appspot.com/o/ClassBackgroundImages%2Fsocial%20studies.jpg?alt=media&token=f25ac667-7b14-4fc5-a7b4-d497f36f5820";

        }


        return null;
    }

}
