
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.Log;
import android.widget.TextView;

import androidx.core.graphics.ColorUtils;
import androidx.palette.graphics.Palette;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Dominant_Color{

    private static final String TAG = Dominant_Color.class.getSimpleName();
    
    public static int getDominantColor(Bitmap bitmap) {
        List<Palette.Swatch> swatchesTemp = Palette.from(bitmap).generate().getSwatches();
        List<Palette.Swatch> swatches = new ArrayList<>(swatchesTemp);
        Collections.sort(swatches, (swatch1, swatch2) -> swatch2.getPopulation() - swatch1.getPopulation());
        if(swatches.size()>0){
            Palette.Swatch swatch = swatches.get(0);
            return ColorUtils.HSLToColor(swatch.getHsl());
        }return Color.WHITE;
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public static void gradientTextView(TextView textView, int startColor, int endColor){
        try{
            TextPaint paint = textView.getPaint();
            float width = paint.measureText("Ready to Redesign");
            Shader textShader = new LinearGradient(0, 0, width, textView.getTextSize(),
                    startColor,endColor, Shader.TileMode.MIRROR);
            textView.getPaint().setShader(textShader);
        }catch (Exception e){
            Log.e(TAG,e.getMessage(),e);
        }
    }


}