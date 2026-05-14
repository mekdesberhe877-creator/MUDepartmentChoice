package com.example.mudepartmentchoice;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class DepartmentListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_list);

        ListView listView = findViewById(R.id.listView); // Corrected ID
        List<String> departments = new ArrayList<>();

        try (DBHelper dbHelper = new DBHelper(this);
             SQLiteDatabase db = dbHelper.getReadableDatabase();
             Cursor cursor = db.rawQuery("SELECT name FROM departments", null)) {
            while (cursor.moveToNext()) {
                departments.add(cursor.getString(0));
            }
        }

        DepartmentAdapter adapter = new DepartmentAdapter(this, departments);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedName = departments.get(position);
            Intent intent = new Intent(this, DepartmentDetailActivity.class);
            intent.putExtra("DEPARTMENT_NAME", selectedName);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}