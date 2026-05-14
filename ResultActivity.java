package com.example.mudepartmentchoice;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {

    private Map<String, String> reasons = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        reasons.put("Information Systems", "You have a natural affinity for working with data and using math to solve organizational problems.");
        reasons.put("Computer Science", "You showed a strong interest in programming, mathematical logic, and technical problem-solving.");
        reasons.put("Business", "Your answers indicate leadership potential, an interest in commerce, and strong teamwork skills.");
        reasons.put("Engineering", "You enjoy the practical application of science and the process of building and designing systems.");

        TextView t = findViewById(R.id.result);
        Button otherOptions = findViewById(R.id.viewOtherOptions);
        Button history = findViewById(R.id.viewHistory);
        Button backHome = findViewById(R.id.backHome);

        int isScore = getIntent().getIntExtra("is", 0);
        int csScore = getIntent().getIntExtra("cs", 0);
        int busScore = getIntent().getIntExtra("bus", 0);
        int engScore = getIntent().getIntExtra("eng", 0);

        int total = isScore + csScore + busScore + engScore;
        if (total == 0) total = 1;

        List<DeptScore> scores = new ArrayList<>();
        scores.add(new DeptScore("Information Systems", isScore));
        scores.add(new DeptScore("Computer Science", csScore));
        scores.add(new DeptScore("Business", busScore));
        scores.add(new DeptScore("Engineering", engScore));

        Collections.sort(scores, (a, b) -> b.score - a.score);

        // Show BEST match and its reason
        String bestName = scores.get(0).name;
        String bestReason = reasons.get(bestName);
        
        StringBuilder mainResult = new StringBuilder();
        mainResult.append(bestName).append(" (").append(calculatePercent(scores.get(0).score, total)).append("%)\n\n");
        mainResult.append("Why this is for you:\n").append(bestReason);
        
        t.setText(mainResult.toString());
        t.setTextSize(18);

        saveResult(bestName);

        // Prepare the list for the "Other Options" screen
        ArrayList<SuitableOption> otherSuitableOptions = new ArrayList<>();
        for (int i = 1; i < scores.size(); i++) {
            if (scores.get(i).score > 0) {
                String name = scores.get(i).name;
                String percent = calculatePercent(scores.get(i).score, total) + "%";
                String reason = reasons.get(name);
                otherSuitableOptions.add(new SuitableOption(name + " (" + percent + ")", reason));
            }
        }

        otherOptions.setOnClickListener(v -> {
            Intent intent = new Intent(this, OtherOptionsActivity.class);
            intent.putExtra("other_options", otherSuitableOptions);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        history.setOnClickListener(v -> {
            startActivity(new Intent(this, HistoryActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        backHome.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    private void saveResult(String best) {
        try (DBHelper dbHelper = new DBHelper(this);
             SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put("best", best);
            db.insert("result", null, values);
        } catch (Exception e) {
            Log.e("ResultActivity", "Error saving result", e);
        }
    }

    private int calculatePercent(int score, int total) {
        return (int) (((double) score / total) * 100);
    }

    private static class DeptScore {
        String name;
        int score;
        DeptScore(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }
}