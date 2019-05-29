package com.lilliemountain.guardian.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cooltechworks.views.WhatsAppTextView;
import com.lilliemountain.guardian.R;
import com.lilliemountain.guardian.model.Messages;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ParentTeacherForumAdapter extends RecyclerView.Adapter<ParentTeacherForumAdapter.ParentTeacherForumHolder> {
    List<Messages> messages;

    public ParentTeacherForumAdapter(List<Messages> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public ParentTeacherForumHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_messages,viewGroup,false);
        return new ParentTeacherForumHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentTeacherForumHolder parentTeacherForumHolder, int i) {
        Messages msg=messages.get(i);
        if (msg.isMymessage())
        {
            parentTeacherForumHolder.them.setVisibility(View.GONE);
            parentTeacherForumHolder.me.setVisibility(View.VISIBLE);
            parentTeacherForumHolder.messageme.setText(msg.getMessage());
            long tempStamp= Long.parseLong(msg.getTimestamp()) ;
            Date date = new Date(tempStamp);
            SimpleDateFormat formatDate = new SimpleDateFormat("dd MMM yy-EEE hh:mm a");
            String tempDate=formatDate.format(date.getTime());
            tempDate=tempDate.replace("-","\n");
            parentTeacherForumHolder.timestampme.setText(tempDate);
            if (msg.isSeen()) {
                parentTeacherForumHolder.done.setVisibility(View.GONE);
                parentTeacherForumHolder.doneall.setVisibility(View.VISIBLE);
            }
            else {
                parentTeacherForumHolder.done.setVisibility(View.VISIBLE);
                parentTeacherForumHolder.doneall.setVisibility(View.GONE);
            }

        }
        else
        {
            parentTeacherForumHolder.me.setVisibility(View.GONE);
            parentTeacherForumHolder.them.setVisibility(View.VISIBLE);
            parentTeacherForumHolder.messagethem.setText(msg.getMessage());
            long tempStamp= Long.parseLong(msg.getTimestamp())*1000 ;
            Date date = new Date(tempStamp);
            SimpleDateFormat formatDate = new SimpleDateFormat("dd MMM yy-EEE hh:mm a");
            String tempDate=formatDate.format(date.getTime());
            tempDate=tempDate.replace("-","\n");
            parentTeacherForumHolder.timestampme.setText(tempDate);
        }
    }
    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ParentTeacherForumHolder extends RecyclerView.ViewHolder {
        CardView me,them;
        TextView messagethem,messageme;
        TextView timestampthem,timestampme;
        ImageView done,doneall;
        public ParentTeacherForumHolder(@NonNull View itemView) {
            super(itemView);
            me=itemView.findViewById(R.id.me);
            messageme=itemView.findViewById(R.id.messageme);
            timestampme=itemView.findViewById(R.id.timestampme);
            done=itemView.findViewById(R.id.done);
            doneall=itemView.findViewById(R.id.doneall);

            them=itemView.findViewById(R.id.them);
            messagethem=itemView.findViewById(R.id.messagethem);
            timestampthem=itemView.findViewById(R.id.timestampthem);
        }
    }
}
