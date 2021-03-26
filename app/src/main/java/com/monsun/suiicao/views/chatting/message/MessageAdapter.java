package com.monsun.suiicao.views.chatting.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.monsun.suiicao.R;
import com.monsun.suiicao.firebase.FirebaseSer;
import com.monsun.suiicao.models.Chat;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    public static final int MSG_TYPE_RIGHT = 0;
    public static final int MSG_TYPE_LEFT = 1;
    private List<Chat> chatList;
    private Context context;
    public MessageAdapter(List<Chat> chats)
    {
        this.chatList = chats;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        if (viewType == MSG_TYPE_RIGHT)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_right_layout,parent,false);
            return new MessageAdapter.ViewHolder(v);
        }
        else
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_left_layout,parent,false);
            return new MessageAdapter.ViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chat chat = chatList.get(position);
        holder.message.setText(chat.getMessage());
        //TODO set user img from firebase
        Glide.with(context).load(R.drawable.testprofile).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView imageView;
        private TextView message;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.chat_profile_image);
            message = itemView.findViewById(R.id.chat_message);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (chatList.get(position).getSender().equals(FirebaseSer.FireAuth_User.getUid()))
        {
            return MSG_TYPE_RIGHT;
        }
        return MSG_TYPE_LEFT;
    }
}
