/*
 * Class       : SlideAnimationUtil
 * Description : Useful method for animate view
 * Created by  : vv0z <i.beshkar@gmail.com> 2016/12/05
 */

package ir.yana.banki.Controllers;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;

public class SlideAnimationUtil {

    /**
     * Animates a view so that it slides in from the left of it's container.
     */
    public static void slideInFromLeft(Context context, View view) {
        runSimpleAnimation(context, view, R.anim.slide_from_left);
    }

    /**
     * Animates a view so that it slides from its current position, out of view to the left.
     */
    public static void slideOutToLeft(Context context, View view) {
        runSimpleAnimation(context, view, R.anim.slide_to_left);
    }

    /**
     * Animates a view so that it slides in the from the right of it's container.
     */
    public static void slideInFromRight(Context context, View view) {
        runSimpleAnimation(context, view, R.anim.slide_from_right);
    }

    /**
     * Animates a view so that it slides from its current position, out of view to the right.
     */
    public static void slideOutToRight(Context context, View view) {
        runSimpleAnimation(context, view, R.anim.slide_to_right);
    }

    /**
     * Runs a simple animation on a View with no extra parameters.
     */
    private static void runSimpleAnimation(Context context, View view, int animationId) {
        view.startAnimation(AnimationUtils.loadAnimation(context, animationId));
    }

}
