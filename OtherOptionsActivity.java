package com.example.mudepartmentchoice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class OtherOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_options);

        ListView optionsListView = findViewById(R.id.optionsListView);
        Button backHome = findViewById(R.id.backHomeFromOptions);

        ArrayList<SuitableOption> options = (ArrayList<SuitableOption>) getIntent().getSerializableExtra("other_options");
        if (options != null) {
            SuitableOptionsAdapter adapter = new SuitableOptionsAdapter(this, options);
            optionsListView.setAdapter(adapter);
        }

        backHome.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }
}