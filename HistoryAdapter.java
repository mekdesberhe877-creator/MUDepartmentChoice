package com.example.mudepartmentchoice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class HistoryAdapter extends ArrayAdapter<HistoryItem> {

    private OnDeleteClickListener deleteListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(HistoryItem item);
    }

    public HistoryAdapter(@NonNull Context context, @NonNull List<HistoryItem> items, OnDeleteClickListener listener) {
        super(context, 0, items);
        this.deleteListener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_history, parent, false);
        }

        HistoryItem item = getItem(position);

        TextView text = convertView.findViewById(R.id.historyText);
        ImageView delete = convertView.findViewById(R.id.deleteIcon);

        if (item != null) {
            text.setText(item.getName());
            delete.setOnClickListener(v -> {
                if (deleteListener != null) {
                    deleteListener.onDeleteClick(item);
                }
            });
        }

        return convertView;
    }
}