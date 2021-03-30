package com.monsun.suiicao.views.chatting.frag.ListChat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.monsun.suiicao.R;
import com.monsun.suiicao.models.Contact;
import com.monsun.suiicao.views.chatting.frag.listcontact.ListContactAdapter;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<Contact> contacts;
    private Context context;
    OnRecentChatListener onRecentChatListener;
    public ChatAdapter (List<Contact> contactList,OnRecentChatListener onRecentChatListener)
    {
        this.onRecentChatListener = onRecentChatListener;
        this.contacts = contactList;
    }
    private static final String TAG = "ListContactAdapter";
    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_chat_row,parent,false);
        return new ChatAdapter.ViewHolder(view,onRecentChatListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recent_name.setText(contacts.get(position).getContact_name());
        // TODO Nếu là mentor thì thấy sv
        holder.recent_id.setText("MSV : " + contacts.get(position).getContact_id());

        if (contacts.get(position).getContact_img().equals("default"))
            Glide.with(context).load(R.drawable.testprofile).into(holder.recent_image);
        else
            Glide.with(context).load(contacts.get(position).getContact_img()).into(holder.recent_image);
        // TODO  Nếu là Sinh viên thì thấy mentor
    }



    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private CircleImageView recent_image;
        private TextView recent_id,recent_name;
        ChatAdapter.OnRecentChatListener onRecentChatListener;
        public ViewHolder(@NonNull View itemView , ChatAdapter.OnRecentChatListener onRecentChatListener) {
            super(itemView);
            recent_image =  itemView.findViewById(R.id.recent_chat_img);
            recent_id = itemView.findViewById(R.id.recent_chat_id);
            recent_name = itemView.findViewById(R.id.recent_chat_name);
            this.onRecentChatListener = onRecentChatListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRecentChatListener.OnRecentChatClick(getAdapterPosition());
        }

    }
    public interface OnRecentChatListener
    {
        void OnRecentChatClick(int position);
    }
}
