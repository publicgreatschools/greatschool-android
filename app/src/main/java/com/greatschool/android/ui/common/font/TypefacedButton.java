package com.greatschool.android.ui.common.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * TypefacedButton implements a Button that can use different fonts within the app.
 */
public class TypefacedButton extends Button {
    public TypefacedButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Typeface.createFromAsset doesn't work in the layout editor. Skipping...
        if (isInEditMode()) {
            return;
        }

        Typeface typeface = TypefaceFactory.getInstance().createFrom(context, attrs);
        setTypeface(typeface);
    }
}
