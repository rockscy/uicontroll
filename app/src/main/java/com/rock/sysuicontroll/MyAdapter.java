package com.rock.sysuicontroll;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by timeDoMain.
 * User: scy
 * Date: 3/2/21  3:25 PM
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == MESSAGE_SELF) {
            view = inflater.inflate(R.layout.message_bubble_self, parent, false);
        } else {
            view = inflater.inflate(R.layout.message_bubble_other, parent, false);
        }
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? MESSAGE_OTHER : MESSAGE_SELF;
    }

    @Override
    public int getItemCount() {
        return MESSAGE_COUNT;
    }

    public static final int MESSAGE_SELF = 0;
    public static final int MESSAGE_OTHER = 1;
    public static final int MESSAGE_COUNT = 50;

    private static class MessageViewHolder extends RecyclerView.ViewHolder {

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
