package com.example.osamanadeem.seekhloo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterSearchTutor extends RecyclerView.Adapter<RecyclerViewAdapterSearchTutor.ViewHolder> {


    private ArrayList<classattributes> classes = new ArrayList<>();
    ArrayList<TutorInfo> attr = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapterSearchTutor(Context mContext) {
        this.mContext = mContext;

    }


    public RecyclerViewAdapterSearchTutor(ArrayList<TutorInfo> attr, Context mContext) {

        this.mContext = mContext;
        this.attr = attr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchtutor_card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.name.setText(attr.get(position).getFirstname() + " " + attr.get(position).getLastname());
        Glide.with(mContext).load(attr.get(position).getPicUrL()).into(holder.imageViews);

        holder.cardViews.setClickable(true);
        load_data(attr.get(position).getId());

        holder.cardViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Toast.makeText(mContext, attr.get(position).getId(), Toast.LENGTH_SHORT).show();
                Context context = view.getContext();
                Bundle b = new Bundle();

                Intent intent = new Intent(context, TutorInfoActivity.class);
               //Toast.makeText(context, classes.get(0).getCatagory(), Toast.LENGTH_SHORT).show();
                intent.putExtra("PicUrl",attr.get(position).getPicUrL());
                intent.putExtra("id",attr.get(position).getId());
                intent.putExtra("TutorName",attr.get(position).getFirstname()+" "+attr.get(position).getLastname());
                intent.putExtra("position",position);
                intent.putExtra("gig",classes);
//                intent.putExtra("Time",classes.getTime());
          //      b.putParcelable("value", (Parcelable) classes);
                //intent.putParcelableArrayListExtra("DaysSelected",classes.getList());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return attr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        CardView cardViews;
        CircleImageView imageViews;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViews = itemView.findViewById(R.id.imgsearchtutor);
            name = itemView.findViewById(R.id.search_tutor_name);
            cardViews = itemView.findViewById(R.id.search_tutor_card);
        }
    }

    public void load_data(String id) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://seekhloo.firebaseio.com/Tutor/"+id+"/Gigs");

        //Toast.makeText(mContext, currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot children : dataSnapshot.getChildren()) {
                    classattributes attr = children.getValue(classattributes.class);

                    classes.add(attr);
                    Toast.makeText(mContext, classes.get(0).getCatagory(), Toast.LENGTH_SHORT).show();


                    //add you mediaItem to list that you provided
                }
                //Toast.makeText(getContext(), classes.size() + "", Toast.LENGTH_SHORT).show();


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show();
            }

        });


        ////////////////////////////////////



    }

    }
