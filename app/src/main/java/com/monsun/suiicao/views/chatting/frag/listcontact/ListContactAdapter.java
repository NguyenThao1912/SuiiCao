package com.monsun.suiicao.views.chatting.frag.listcontact;

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

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListContactAdapter extends RecyclerView.Adapter<ListContactAdapter.ViewHolder> {
    private static final String TAG = "ListContactAdapter";
    private List<Contact> lcontact ;
    private Context context;
    private OnContactListener onContactListener;
    public ListContactAdapter(List<Contact> list,OnContactListener onContactListener)
    {
        lcontact = list;
        this.onContactListener = onContactListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row_item,parent,false);
        return new ListContactAdapter.ViewHolder(view,onContactListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.contact_name.setText(  lcontact.get(position).getContact_name());
        // TODO Nếu là mentor thì thấy sv
        holder.contact_id.setText("MSV : " + lcontact.get(position).getContact_id());

        if (lcontact.get(position).getContact_img().equals("default"))
            Glide.with(context).load(R.drawable.testprofile).into(holder.contact_image);
        else
            Glide.with(context).load(lcontact.get(position).getContact_img()).into(holder.contact_image);
        // TODO  Nếu là Sinh viên thì thấy mentor
    }

    @Override
    public int getItemCount() {
        return lcontact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private CircleImageView contact_image;
        private TextView contact_id,contact_name;
        OnContactListener onContactListener;
        public ViewHolder(@NonNull View itemView ,OnContactListener onContactListener) {
            super(itemView);
            contact_image =  itemView.findViewById(R.id.contact_img);
            contact_id = itemView.findViewById(R.id.contact_id);
            contact_name = itemView.findViewById(R.id.contact_name);
            this.onContactListener = onContactListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onContactListener.onContactClick(getAdapterPosition());
        }
    }

    public interface OnContactListener
    {
        void onContactClick(int position);
    }
}
