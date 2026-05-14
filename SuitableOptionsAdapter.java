package com.example.mudepartmentchoice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class SuitableOptionsAdapter extends ArrayAdapter<SuitableOption> {

    public SuitableOptionsAdapter(@NonNull Context context, @NonNull List<SuitableOption> options) {
        super(context, 0, options);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_other_option, parent, false);
        }

        SuitableOption option = getItem(position);

        TextView nameView = convertView.findViewById(R.id.optionName);
        TextView reasonView = convertView.findViewById(R.id.optionReason);

        if (option != null) {
            nameView.setText(option.getName());
            reasonView.setText(option.getReason());
        }

        return convertView;
    }
}