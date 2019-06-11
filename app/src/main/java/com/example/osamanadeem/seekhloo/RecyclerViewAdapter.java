package com.example.osamanadeem.seekhloo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{



    ArrayList<NewsLetters> news = new ArrayList<>();
    ArrayList<classattributes> attr = new ArrayList<>();

    private Context mContext;

    public  RecyclerViewAdapter(Context mContext)
    {
        this.mContext = mContext;

    }


    public RecyclerViewAdapter( Context mContext,ArrayList<NewsLetters> news) {

        this.mContext = mContext;
        this.news = news;
    }
    public RecyclerViewAdapter( ArrayList<classattributes> attr,Context mContext) {

        this.mContext = mContext;
        this.attr = attr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notifications_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.subject.setText(news.get(position).getSubject());
        holder.messagebody.setText(news.get(position).getMessageBody());
        holder.cardView.setClickable(true);
        if (!news.get(position).getPicurl().isEmpty())
            Glide.with(mContext).load(news.get(position).getPicurl()).into(holder.imageView);

        holder.cardView.setClickable(true);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, SeeNewsletter.class);
                intent.putExtra("subject",news.get(position).getSubject());
                intent.putExtra("messagebody",news.get(position).getMessageBody());
                intent.putExtra("picurl",news.get(position).getPicurl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class   ViewHolder extends RecyclerView.ViewHolder{
        TextView messagebody;
        TextView subject;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.notify_img);
            messagebody = itemView.findViewById(R.id.messagebody_notify);
            subject = itemView.findViewById(R.id.subject_notify);
            cardView = itemView.findViewById(R.id.notifycard);
        }
    }

}