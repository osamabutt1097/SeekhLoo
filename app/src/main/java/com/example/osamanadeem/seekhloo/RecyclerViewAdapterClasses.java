package com.example.osamanadeem.seekhloo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;

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

        Glide.with(mContext).load(typetopic(attr.get(position).getCatagory())).into(holder.imageView);
        holder.cardView.setClickable(true);

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(mContext, "long clicked!", Toast.LENGTH_SHORT).show();
/*
                AlertDialog.Builder adb = new AlertDialog.Builder(mContext);
                adb.setTitle("Delete this class");
                adb.setIcon(android.R.drawable.ic_dialog_alert);
                adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "OK", Toast.LENGTH_SHORT).show();
                    }
                });
                adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "Cancel", Toast.LENGTH_SHORT).show();
                        //finish();
                    }
                });
                adb.show();*/

                return true;
            }
        });


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                Context context = view.getContext();
                Intent intent = new Intent(context, ClassExistedActivity.class);


                SharedPreferences.Editor editor = mContext.getSharedPreferences("User", MODE_PRIVATE).edit();
                editor.putString("subjectname", attr.get(position).getName());
                editor.putString("s_id", FirebaseAuth.getInstance().getCurrentUser().getUid());
                editor.putString("t_id", attr.get(position).getTutor());
                editor.putString("c_name", attr.get(position).getName());

                editor.apply();



                intent.putExtra("subjectname",attr.get(position).getName());
                intent.putExtra("s_id", FirebaseAuth.getInstance().getCurrentUser().getUid());
                intent.putExtra("t_id",attr.get(position).getTutor());
               // Toast.makeText(context, attr.get(position).getTutor()+"", Toast.LENGTH_SHORT).show();
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
