package com.heeresonline.androidgames;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.View;

public class ShapeTest extends Activity {
  private RenderView view;
  
  class RenderView extends View {
    private Random random;
    private Paint paint;
    
    public RenderView(Context context) {
      super(context);

      paint = new Paint();
      random = new Random();
    }

    /* (non-Javadoc)
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas) {
      int width = canvas.getWidth();
      int height = canvas.getHeight();

      canvas.drawRGB(255, 255, 255);
      
      paint.setColor(Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255)));
      canvas.drawLine(0, 0, width - 1, height - 1, paint);
      
      paint.setStyle(Style.STROKE);
      paint.setColor(Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255)));
      canvas.drawCircle(width / 2,  height / 2, 40, paint);
      
      paint.setStyle(Style.FILL);
      paint.setColor(Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255)));
      canvas.drawRect(100, 100, 200, 200, paint);

      invalidate();
    }
  }
  /* (non-Javadoc)
   * @see android.app.Activity#onCreate(android.os.Bundle)
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    view = new RenderView(this);
    setContentView(view);
  }
}
