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

public class DepartmentAdapter extends ArrayAdapter<String> {

    public DepartmentAdapter(@NonNull Context context, @NonNull List<String> departments) {
        super(context, 0, departments);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_department, parent, false);
        }

        String departmentName = getItem(position);

        TextView deptName = convertView.findViewById(R.id.deptName);
        if (departmentName != null) {
            deptName.setText(departmentName);
        }

        return convertView;
    }
}
