package com.example.xox_ua.practice_07_030618;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class DescriptionActivity extends MainActivity {
    public ImageView ivImgDescr;
    public TextView tvTitleDescr;
    public TextView tvAuthorDescr;
    public TextView tvDescr;
    public RatingBar ratingBarDescr;
    public Button btnBack;
    public Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        ivImgDescr = (ImageView) findViewById(R.id.ivImgDescr);
        tvTitleDescr = (TextView) findViewById(R.id.tvTitleDescr);
        tvAuthorDescr = (TextView) findViewById(R.id.tvAuthorDescr);
        tvDescr = (TextView) findViewById(R.id.tvDescr);
        ratingBarDescr = (RatingBar) findViewById(R.id.ratingBarDescr);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnShare = (Button) findViewById(R.id.btnShare);
        // добавляем прокручивание для данной текстовой области
        tvDescr.setMovementMethod(new ScrollingMovementMethod());

        if (getIntent().getBooleanExtra("Notification", false)) {
            // получаем интент
            Intent intent = getIntent();
            String descrT = intent.getStringExtra("getTitle");
            String descrA = intent.getStringExtra("getAuthor");
            int descrR = intent.getIntExtra("getRating", 0);
            int descrD = intent.getIntExtra("getDescr", 0);
            // выводим полученные данные в соотвествующих областях
            tvTitleDescr.setText(descrT);
            tvAuthorDescr.setText(descrA);
            ratingBarDescr.setRating(descrR);
            tvDescr.setText(descrD);
        } else {
            //интент не получен
            Toast.makeText(getApplicationContext(), R.string.toast3, Toast.LENGTH_SHORT).show();
        }

        // кнопка назад
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // кнопка поделиться
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
    }
}
