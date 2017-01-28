package com.example.andres.movies_test.util;

import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;
import android.widget.RatingBar;

import com.example.andres.movies_test.R;

/**
 * Created by andres on 28/01/17.
 */

public class Function {

    /**
     * Change star ratng color
     * @param ratingBar
     */
    public static void changeRatingColor(RatingBar ratingBar){
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(ratingBar.getContext(),
                R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(ContextCompat.getColor(ratingBar.getContext(),
                R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(ContextCompat.getColor(ratingBar.getContext(),
                R.color.yellow), PorterDuff.Mode.SRC_ATOP);
    }
}
