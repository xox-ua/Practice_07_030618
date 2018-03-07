package com.example.xox_ua.practice_07_030618;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class AddActivity extends AppCompatActivity {
    public EditText etTitle;
    public EditText etAuthor;
    public EditText etDescr;
    public RatingBar mRatingBar;
    public Button btnBack;
    public Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etTitle = (EditText) findViewById(R.id.etAddTitle);
        etAuthor = (EditText) findViewById(R.id.etAddAuthor);
        etDescr = (EditText) findViewById(R.id.etAddDescription);
        mRatingBar = (RatingBar) findViewById(R.id.addRating);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnSave = (Button) findViewById(R.id.btnSave);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // считываем введённые данные
                String dataT = etTitle.getText().toString();
                String dataA = etAuthor.getText().toString();
                String dataD = etDescr.getText().toString();
                int dataR = (int) mRatingBar.getRating();
                // отдаём через intent
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                intent.putExtra("AddTitle", dataT);
                intent.putExtra("AddAuthor", dataA);
                intent.putExtra("AddRating", dataR);
                intent.putExtra("AddDescr", dataD);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
