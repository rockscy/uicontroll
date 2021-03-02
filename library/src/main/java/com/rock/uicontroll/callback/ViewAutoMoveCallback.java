package com.rock.uicontroll.callback;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;


import com.rock.uicontroll.UiType;

import java.util.List;

/**
 * Created by timeDoMain.
 * User: scy
 * Date: 3/1/21  11:39 AM
 */
public class ViewAutoMoveCallback extends WindowInsetsAnimationCompat.Callback {


    /**
     * Creates a new {@link WindowInsetsAnimationCompat} callback with the given
     * {@link #getDispatchMode() dispatch mode}.
     *
     * @param dispatchMode The dispatch mode for this callback. See {@link #getDispatchMode()}.
     */

    public ViewAutoMoveCallback(int dispatchMode, View view) {
        super(dispatchMode);
        this.view = view;
    }

    private final View view;


    private boolean deferredInsets = false;


    @Override
    public void onPrepare(@NonNull WindowInsetsAnimationCompat animation) {
        if ((animation.getTypeMask() & UiType.KEYBOARY) != 0) {
            deferredInsets = true;
        }
    }

    @NonNull
    @Override
    public WindowInsetsCompat onProgress(@NonNull WindowInsetsCompat insets, @NonNull List<WindowInsetsAnimationCompat> runningAnimations) {
        if (deferredInsets && view != null) {

            // First we get the insets which are potentially deferred
            Insets typesInset = insets.getInsets(UiType.KEYBOARY);
            // Then we get the persistent inset types which are applied as padding during layout
            Insets otherInset = insets.getInsets(UiType.ALL_BARS);

            // Now that we subtract the two insets, to calculate the difference. We also coerce
            // the insets to be >= 0, to make sure we don't use negative insets.
            Insets subtract = Insets.subtract(typesInset, otherInset);
            Insets diff = Insets.max(subtract, Insets.NONE);


            // The resulting `diff` insets contain the values for us to apply as a translation
            // to the view
            view.setTranslationX((diff.left - diff.right));
            view.setTranslationY(diff.top - diff.bottom);
        }
        return insets;
    }

    @Override
    public void onEnd(@NonNull WindowInsetsAnimationCompat animation) {
        if (deferredInsets && (animation.getTypeMask() & UiType.KEYBOARY) != 0) {
            deferredInsets = false;
        }
    }
}
