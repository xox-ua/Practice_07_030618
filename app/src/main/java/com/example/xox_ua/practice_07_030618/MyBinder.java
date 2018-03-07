package com.example.xox_ua.practice_07_030618;

import android.view.View;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;

public class MyBinder implements SimpleAdapter.ViewBinder {
    @Override
    public boolean setViewValue(View view, Object data, String textRepresentation) {
        if(view.getId() == R.id.ratingBar){
            int val = (int) data;
            RatingBar ratingBar = (RatingBar) view;
            ratingBar.setRating(val);
            return true;
        }
        return false;
    }
}
