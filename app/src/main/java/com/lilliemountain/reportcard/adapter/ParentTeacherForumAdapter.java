package com.lilliemountain.reportcard.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cooltechworks.views.WhatsAppTextView;
import com.lilliemountain.reportcard.R;
import com.lilliemountain.reportcard.model.Messages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
            parentTeacherForumHolder.timestampme.setText(msg.getTimestamp());

        }
        else
        {
            parentTeacherForumHolder.me.setVisibility(View.GONE);
            parentTeacherForumHolder.them.setVisibility(View.VISIBLE);
            parentTeacherForumHolder.messagethem.setText(msg.getMessage());
            parentTeacherForumHolder.timestampthem.setText(msg.getTimestamp());
        }
    }
    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ParentTeacherForumHolder extends RecyclerView.ViewHolder {
        CardView me,them;
        WhatsAppTextView messagethem,messageme;
        TextView timestampthem,timestampme;
        public ParentTeacherForumHolder(@NonNull View itemView) {
            super(itemView);
            me=itemView.findViewById(R.id.me);
            messageme=itemView.findViewById(R.id.messageme);
            timestampme=itemView.findViewById(R.id.timestampme);

            them=itemView.findViewById(R.id.them);
            messagethem=itemView.findViewById(R.id.messagethem);
            timestampthem=itemView.findViewById(R.id.timestampthem);
        }
    }
}
