package com.example.mudepartmentchoice;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ListView list = findViewById(R.id.list);
        Button clear = findViewById(R.id.clear);

        load(list);

        clear.setOnClickListener(v -> {
            try (DBHelper dbHelper = new DBHelper(this);
                 SQLiteDatabase db = dbHelper.getWritableDatabase()) {
                db.execSQL("DELETE FROM result");
            } catch (Exception e) {
                Log.e("HistoryActivity", "Error clearing history", e);
            }
            load(list);
        });
    }

    private void load(ListView list) {
        ArrayList<HistoryItem> data = new ArrayList<>();

        try (DBHelper dbHelper = new DBHelper(this);
             Cursor c = dbHelper.getReadableDatabase().rawQuery("SELECT id, best FROM result", null)) {
            if (c != null) {
                while (c.moveToNext()) {
                    data.add(new HistoryItem(c.getInt(0), c.getString(1)));
                }
            }
        } catch (Exception e) {
            Log.e("HistoryActivity", "Error loading history", e);
        }

        HistoryAdapter adapter = new HistoryAdapter(this, data, item -> {
            deleteItem(item.getId());
            load(list);
        });
        list.setAdapter(adapter);
    }

    private void deleteItem(int id) {
        try (DBHelper dbHelper = new DBHelper(this);
             SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            db.delete("result", "id=?", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.e("HistoryActivity", "Error deleting item", e);
        }
    }
}