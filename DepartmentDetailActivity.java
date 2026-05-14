package com.example.mudepartmentchoice;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DepartmentDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_detail);

        String deptName = getIntent().getStringExtra("DEPARTMENT_NAME");

        TextView titleView = findViewById(R.id.detailTitle);
        TextView descriptionView = findViewById(R.id.detailDescription);
        ImageView backButton = findViewById(R.id.backButton);

        titleView.setText(deptName);

        if (deptName != null) {
            try (DBHelper dbHelper = new DBHelper(this);
                 SQLiteDatabase db = dbHelper.getReadableDatabase();
                 Cursor cursor = db.rawQuery("SELECT description FROM departments WHERE name=?", new String[]{deptName})) {
                if (cursor.moveToFirst()) {
                    descriptionView.setText(cursor.getString(0));
                }
            }
        }

        backButton.setOnClickListener(v -> finish());
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}