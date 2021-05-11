
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

public class ViewAnimator {


    public static void hide(View from, View to,long duration) {
        float startRadius = (float) (Math.max(from.getWidth(), from.getHeight()));
        int revealX = (int) (to.getX() + to.getWidth() / 2);
        int revealY = (int) (to.getY() + to.getHeight() / 2);
        float endRadius = to.getHeight()/2f;
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                from, revealX, revealY, startRadius, endRadius);

        circularReveal.setDuration(duration);
        circularReveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                from.setVisibility(View.INVISIBLE);
            }
        });

        circularReveal.start();
    }

    public static void revealView(View from, View to,long duration) {
        float startRadius = from.getHeight()/2f;
        float finalRadius = (float) (Math.max(from.getWidth(), to.getHeight()));
        int revealX = (int) (from.getX() + from.getWidth() / 2);
        int revealY = (int) (from.getY() + from.getHeight() / 2);
        // create the animator for this view (the start radius is zero)
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(to, revealX, revealY, startRadius, finalRadius);
        circularReveal.setDuration(duration);
        circularReveal.setInterpolator(new LinearInterpolator());

        // make the view visible and start the animation
        to.setVisibility(View.VISIBLE);
        circularReveal.start();

    }
    public static void toHeight(View view,int toHeight,long duration){
        ValueAnimator anim = ValueAnimator.ofInt(view.getMeasuredHeight(), toHeight);
        anim.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = val;
            view.setLayoutParams(layoutParams);
        });
        anim.setDuration(duration);
        anim.start();
    }

    public static void toWidth(View view,int toWidth,long duration){
        ValueAnimator anim = ValueAnimator.ofInt(view.getMeasuredWidth(), toWidth);
        anim.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = val;
            view.setLayoutParams(layoutParams);
        });
        anim.setDuration(duration);
        anim.start();
    }

}
