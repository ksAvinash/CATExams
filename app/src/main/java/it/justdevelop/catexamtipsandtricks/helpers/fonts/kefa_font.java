package it.justdevelop.catexamtipsandtricks.helpers.fonts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class kefa_font extends TextView {

    public kefa_font(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public kefa_font(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public kefa_font(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "Kefa-Regular.ttf" );
        setTypeface(tf);
    }

}