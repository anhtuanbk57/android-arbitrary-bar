package com.tuanna.arbitrarybar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class ArbitraryProgressBar extends ProgressBar {

    private static final int HORIZONTAL_VALUE = 0;

    private boolean mIsHorizontal;

    public ArbitraryProgressBar(Context context) {
        super(context);
    }

    public ArbitraryProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ArbitraryProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        TypedArray array = getContext().getTheme().obtainStyledAttributes(
                attrs, R.styleable.ArbitraryProgressBar, defStyle, 0
        );
        int orientationValue = array.getInt(
                R.styleable.ArbitraryProgressBar_orientation, HORIZONTAL_VALUE
        );
        mIsHorizontal = orientationValue == HORIZONTAL_VALUE;

        array.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        if (mIsHorizontal) {
            super.onSizeChanged(w, h, oldW, oldH);
        } else {
            // Swap dimensions
            super.onSizeChanged(h, w, oldH, oldW);
        }
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        if (!mIsHorizontal) {
            canvas.rotate(-90);
            canvas.translate(-getHeight(), 0);
        }
        super.onDraw(canvas);
    }
}
