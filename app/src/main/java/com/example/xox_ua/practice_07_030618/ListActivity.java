package com.example.xox_ua.practice_07_030618;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    public String[] bookTitles = { "Андорра", "Австрия", "Бельгия", "Кипр", "Чехия", "Германия", "Дания",
            "Испания", "Эстония", "Финляндия", "Франция", "Великобритания", "Греция", "Хорватия",
            "Венгрия", "Ирландия", "Италия", "Литва", "Люксембург", "Латвия", "Монако",
            "Мальта", "Нидерланды", "Польша", "Португалия", "Румыния", "Сан-Марино", "Словакия",
            "Словения", "Украина", "Ватикан" };
    public String[] bookAuthors = { "Андорра-ла-Велья ", "Вена", "Брюссель", "Никосия", "Прага", "Берлин", "Копенгаген",
            "Мадрид", "Таллин", "Хельсинки", "Париж", "Лондон", "Афины", "Загреб",
            "Будапешт", "Дублин", "Рим", "Вильнюс", "Люксембург", "Рига", "Монако",
            "Валлетта", "Амстердам", "Варшава", "Лиссабон", "Бухарест", "Сан-Марино", "Братислава",
            "Любляна", "Киев", "Ватикан" };
    // имена атрибутов для Map
    final String bTITLE = "title";
    final String bAUTHOR = "author";
    final String bRATING = "rate";
    public Button btnAdd;
    public ArrayList<Map<String, Object>> dataAL;
    public SimpleAdapter sAdapter;
    public Map<String, Object> m;
    public ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // определяем список
        lv = (ListView) findViewById(R.id.lv);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        // создаём массив случайных чисел для рейтинга
        Random random = new Random();
        final int bookRating[] = new int[bookTitles.length];
        for (int i = 0; i < bookTitles.length; i++) {
            bookRating[i] = random.nextInt(5);
        }

        // упаковываем данные в понятную для адаптера структуру
        dataAL = new ArrayList<Map<String, Object>>(bookTitles.length);
        for (int i = 1; i < bookTitles.length; i++) {
            m = new HashMap<String, Object>();
            m.put(bTITLE, bookTitles[i]);
            m.put(bAUTHOR, bookAuthors[i]);
            m.put(bRATING, bookRating[i]);
            dataAL.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { bTITLE, bAUTHOR, bRATING };
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.tvTitle, R.id.tvAuthor, R.id.ratingBar };

        // создаем адаптер
        sAdapter = new SimpleAdapter(this, dataAL, R.layout.list_item, from, to);
        // хреновина для передачи цифр в рейтинг
        sAdapter.setViewBinder(new MyBinder());
        // присваиваем списку адаптер
        lv.setAdapter(sAdapter);

// !!!!!!!!!!!!!! НЕ РАБОТАЕТ
        // короткое нажатие на строку
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            //@Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String mess = "Выбрана позиция: " + position;
//                Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_SHORT).show();
//
//                TextView txtView1 = (TextView) parent.findViewById(R.id.tvTitle);
//                String getT = txtView1.getText().toString();
//                TextView txtView2 = (TextView) parent.findViewById(R.id.tvAuthor);
//                String getA = txtView2.getText().toString();
//                RatingBar ratingBar = (RatingBar) parent.findViewById(R.id.ratingBar);
//                int getR = (int) ratingBar.getRating();
//
//                Intent intent = new Intent(ListActivity.this, DescriptionActivity.class);
//                intent.putExtra("getTitle", getT);
//                intent.putExtra("getAuthor", getA);
//                intent.putExtra("getRating", getR);
//                intent.putExtra("getDescr", R.string.lorem);
//                intent.putExtra("Notification", true);
//                startActivity(intent);
//            }
//        });

        // продолжительное нажатие на строку
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // удаляем выбранную позицию
                dataAL.remove(position);
                // уведомляем, что данные изменились
                sAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), R.string.toast4, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        // добавляем новую строку в list
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivityForResult(intent, 1975);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1975:
                if (resultCode == RESULT_OK) {
                    // получаем из intent
                    String newT = data.getStringExtra("AddTitle");
                    String newA = data.getStringExtra("AddAuthor");
                    int newR = data.getIntExtra("AddRating", 0);
                    // создаем новый Map
                    m = new HashMap<String, Object>();
                    m.put(bTITLE, newT + " - NEW!");
                    m.put(bAUTHOR, newA);
                    m.put(bRATING, newR);
                    // добавляем его в коллекцию
                    dataAL.add(m);
                    // уведомляем, что данные изменились
                    sAdapter.notifyDataSetChanged();
                    // после добавления нового пункта проматываем в самый конец ListView
                    lv.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
                }else {
                    Toast.makeText(getApplicationContext(), R.string.toast3, Toast.LENGTH_SHORT).show();
                }

        }
    }

    // короткое нажатие на строку ======= настоящие герои всегда идут другим путём :)))
    public void сlickHandler(View v)
    {
        // определяем нажатую строку
        LinearLayout parentRow = (LinearLayout)v.getParent();
        // берем данные из ячеек строки
        TextView txtView1 = (TextView) parentRow.findViewById(R.id.tvTitle);
        String getT = txtView1.getText().toString();
        TextView txtView2 = (TextView) parentRow.findViewById(R.id.tvAuthor);
        String getA = txtView2.getText().toString();
        RatingBar ratingBar = (RatingBar) parentRow.findViewById(R.id.ratingBar);
        int getR = (int) ratingBar.getRating();

        Intent intent = new Intent(ListActivity.this, DescriptionActivity.class);
        intent.putExtra("getTitle", getT);
        intent.putExtra("getAuthor", getA);
        intent.putExtra("getRating", getR);
        intent.putExtra("getDescr", R.string.lorem);
        intent.putExtra("Notification", true);
        startActivity(intent);
    }

}
