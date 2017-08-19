package com.ceemart.ceemart.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ceemart.ceemart.R;
import com.ceemart.ceemart.helpers.Message;
import com.ceemart.ceemart.modules.notification.MessageListActivity;

import java.util.List;

/**
 * Created by achu on 15/8/17.
 */

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MyViewHolder> {

    private final MessageListActivity.MessageCallback messageCallback;
    private Context mContext;
    private List<Message> messageList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, messageName;
        public ImageView thumbnail, overflow;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            messageName = (TextView) view.findViewById(R.id.messageName);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }


    public MessageListAdapter(Context mContext, List<Message> messageList, MessageListActivity.MessageCallback messageCallback) {
        this.mContext = mContext;
        this.messageList = messageList;
        this.messageCallback = messageCallback;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Message message = messageList.get(position);
        holder.title.setText(message.getName());
        holder.messageName.setText(message.getNumOfSongs());

        // loading message cover using Glide library
        Glide.with(mContext).load(message.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageCallback.onSuccessObjectResponse(15);
                Toast.makeText(mContext, "message selected"+message.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
     * function showPopupMenu
     * Showing popup menu when tapping on 3 dots
     *
     * @param view
     * @return Null
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_message, popup.getMenu());
        popup.setOnMenuItemClickListener(new MessageMenuItemClickListener());
        popup.show();
    }

    /*
     * class MessageMenuItemClickListener
     *
     * Click listener for popup menu items
     */
    class MessageMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MessageMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_view:
                    Toast.makeText(mContext, "View menu selected", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_delete:
                    Toast.makeText(mContext, "message deleted", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}