package com.rock.uicontroll.callback;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;


import com.rock.uicontroll.UiType;
import com.rock.uicontroll.listener.KeyboardListener;

import java.util.List;

/**
 * Created by timeDoMain.
 * User: scy
 * Date: 3/1/21  11:39 AM
 */
public class RootViewDeferringInsetsCallback extends WindowInsetsAnimationCompat.Callback implements OnApplyWindowInsetsListener {
    private final KeyboardListener keyboardListener;

    /**
     * Creates a new {@link WindowInsetsAnimationCompat} callback with the given
     * {@link #getDispatchMode() dispatch mode}.
     *
     * @param dispatchMode The dispatch mode for this callback. See {@link #getDispatchMode()}.
     */
    public RootViewDeferringInsetsCallback(int dispatchMode, KeyboardListener keyboardListener) {
        super(dispatchMode);
        this.keyboardListener = keyboardListener;
    }

    private View view;
    private WindowInsetsCompat lastWindowInsets;

    private boolean deferredInsets = false;


    @Override
    public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
        view = v;
        lastWindowInsets = insets;
        return WindowInsetsCompat.CONSUMED;
    }

    @Override
    public void onPrepare(@NonNull WindowInsetsAnimationCompat animation) {
        if ((animation.getTypeMask() & UiType.KEYBOARY) != 0) {
            deferredInsets = true;
            keyboardListener.onKeyBoardAnimStart();
        }
    }

    @NonNull
    @Override
    public WindowInsetsCompat onProgress(@NonNull WindowInsetsCompat insets, @NonNull List<WindowInsetsAnimationCompat> runningAnimations) {
        if (deferredInsets) {
            // First we get the insets which are potentially deferred
            Insets typesInset = insets.getInsets(UiType.KEYBOARY);
            // Then we get the persistent inset types which are applied as padding during layout
            Insets otherInset = insets.getInsets(UiType.ALL_BARS);

            // Now that we subtract the two insets, to calculate the difference. We also coerce
            // the insets to be >= 0, to make sure we don't use negative insets.
            Insets subtract = Insets.subtract(typesInset, otherInset);
            Insets diff = Insets.max(subtract, Insets.NONE);

            keyboardListener.onKeyBoardHeightChange(diff.bottom);
        }
        return insets;
    }

    @Override
    public void onEnd(@NonNull WindowInsetsAnimationCompat animation) {
        if (deferredInsets && (animation.getTypeMask() & UiType.KEYBOARY) != 0) {
            deferredInsets = false;
            keyboardListener.onKeyBoardAnimEnd();
            if (lastWindowInsets != null) {
                ViewCompat.dispatchApplyWindowInsets(view, lastWindowInsets);
            }
        }
    }
}
