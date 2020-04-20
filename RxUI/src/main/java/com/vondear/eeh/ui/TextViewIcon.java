package com.vondear.eeh.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * @author EraJieZhang
 * @data 2020/4/1
 */
public class TextViewIcon extends androidx.appcompat.widget.AppCompatAutoCompleteTextView {

    public TextViewIcon(Context context) {

        super(context);
    }

    public TextViewIcon(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public TextViewIcon(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
    }
    private void init(Context context){
//        这个放在applactivon中
        Typeface iconfont = Typeface.createFromAsset(context.getAssets(), "iconfont/iconfont.ttf");
        setTypeface(iconfont);
    }
}
