package com.monsun.suiicao.views.chatting.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.R;
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
        // TODO delete message -> click -> lấy id -> xóa message có id
        holder.message.setText(chat.getMessage());
        // TODO set user img from firebase
        StorageReference storageReference;
        if(AppVar.mStudent != null){
            storageReference  = FirebaseStorage.getInstance().getReference().child(AppVar.mStudent.getStudentId().toString());
        }
        else {
            storageReference  = FirebaseStorage.getInstance().getReference().child(AppVar.mMentor.getMentorId().toString());
        }
        Glide.with(context)
                .load(storageReference)
                .into(holder.imageView);
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
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (chatList.get(position).getSender().equals(auth.getCurrentUser().getUid()))
        {
            return MSG_TYPE_RIGHT;
        }
        return MSG_TYPE_LEFT;
    }
}
