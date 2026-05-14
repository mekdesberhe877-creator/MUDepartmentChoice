package com.example.mudepartmentchoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private TextView q1, q2;
    private RadioGroup rg1, rg2;
    private Button next;
    private ProgressBar progressBar;
    private View cardQ2;

    private int page = 0;
    private int is = 0, cs = 0, bus = 0, eng = 0;

    private final String[] qs = {
            "Do you like solving problems?",
            "Do you enjoy coding?",
            "Are you good at math?",
            "Do you like business?",
            "Do you like building things?",
            "Do you enjoy computers?",
            "Do you like leading people?",
            "Do you enjoy data?",
            "Do you prefer teamwork?",
            "Do you like science?"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        q1 = findViewById(R.id.q1);
        q2 = findViewById(R.id.q2);
        rg1 = findViewById(R.id.rg1);
        rg2 = findViewById(R.id.rg2);
        next = findViewById(R.id.next);
        progressBar = findViewById(R.id.quizProgress);
        cardQ2 = findViewById(R.id.cardQ2);

        showPage();

        next.setOnClickListener(v -> {
            if (rg1.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Please answer the first question", Toast.LENGTH_SHORT).show();
                return;
            }

            int idx1 = page * 2;
            int idx2 = idx1 + 1;

            score(idx1, rg1.getCheckedRadioButtonId() == R.id.yes1);

            if (idx2 < qs.length) {
                if (rg2.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(this, "Please answer the second question", Toast.LENGTH_SHORT).show();
                    return;
                }
                score(idx2, rg2.getCheckedRadioButtonId() == R.id.yes2);
            }

            page++;
            if (page * 2 < qs.length) {
                showPage();
            } else {
                go();
            }
        });
    }

    private void showPage() {
        int idx1 = page * 2;
        int idx2 = idx1 + 1;

        q1.setText(qs[idx1]);
        rg1.clearCheck();

        if (idx2 < qs.length) {
            cardQ2.setVisibility(View.VISIBLE);
            q2.setText(qs[idx2]);
            rg2.clearCheck();
        } else {
            cardQ2.setVisibility(View.GONE);
        }

        int progress = (int) (((double) (page * 2) / qs.length) * 100);
        progressBar.setProgress(progress);
    }

    private void score(int index, boolean isYes) {
        if (!isYes) return;

        switch (index) {
            case 0: is += 2; cs += 2; break;
            case 1: cs += 3; break;
            case 2: is += 2; cs += 2; break;
            case 3: bus += 3; break;
            case 4: eng += 3; break;
            case 5: cs += 2; break;
            case 6: bus += 3; break;
            case 7: is += 2; break;
            case 8: bus += 1; break;
            case 9: eng += 1; break;
        }
    }

    private void go() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("is", is);
        intent.putExtra("cs", cs);
        intent.putExtra("bus", bus);
        intent.putExtra("eng", eng);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}