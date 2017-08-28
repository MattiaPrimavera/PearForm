package com.mprimavera.pearform.tools;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;

public class DrawableTools {
    public static Drawable getDrawable(Context context, int drawableId){
        Resources res = context.getResources();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return res.getDrawable(drawableId, context.getTheme());
        }else{
            return res.getDrawable(drawableId);
        }
    }
}
