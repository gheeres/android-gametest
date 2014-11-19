package com.heeresonline.androidgames;

import com.heeresonline.androidgames.RenderViewTest.RenderView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

public class SurfaceViewTest extends Activity {
  private RenderView view;
  
  /* (non-Javadoc)
   * @see android.app.Activity#onCreate(android.os.Bundle)
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, 
                         WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    super.onCreate(savedInstanceState);

    view = new RenderView(this);
    setContentView(view);
  }

  /* (non-Javadoc)
   * @see android.app.Activity#onResume()
   */
  @Override
  protected void onResume() {
    super.onResume();
    view.resume();
  }

  /* (non-Javadoc)
   * @see android.app.Activity#onPause()
   */
  @Override
  protected void onPause() {
    super.onPause();
    view.pause();
  }

  class RenderView extends SurfaceView implements Runnable {
    private Context context;
    private SurfaceHolder holder;
    private Thread thread;
    private volatile boolean isRunning = false;
    
    public RenderView(Context context) {
      super(context);
      this.context = context;
      
      holder = getHolder();
    }

    /* (non-Javadoc)
     * @see android.view.SurfaceView#draw(android.graphics.Canvas)
     */
    @Override
    public void draw(Canvas canvas) {
      // TODO Auto-generated method stub
      super.draw(canvas);
    }

    @Override
    public void run() {
      while (isRunning) {
        if (! holder.getSurface().isValid()) continue;
        
        Canvas canvas = holder.lockCanvas();
        canvas.drawRGB(0, 0, 0);
        holder.unlockCanvasAndPost(canvas);
      }
    }
    
    public void resume() {
      isRunning = true;
      thread = new Thread(this);
      thread.start();
    }
    
    public void pause() {
      isRunning = false;
      while (true) {
        try {
          thread.join();         
        }
        catch(InterruptedException e) {
        }
      }
    }
  }
}
