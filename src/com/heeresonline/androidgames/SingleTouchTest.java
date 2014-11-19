package com.heeresonline.androidgames;

import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class SingleTouchTest extends Activity implements OnTouchListener {
  private PointF point;
  private TextView text;
  
  /* (non-Javadoc)
   * @see android.app.Activity#onCreate(android.os.Bundle)
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    super.onCreate(savedInstanceState);
    
    text = new TextView(this);
    text.setOnTouchListener(this);
    setContentView(text);
  }

  @Override
  public boolean onTouch(View view, MotionEvent event) {
    switch(event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        point = new PointF(event.getX(), event.getY());
        break;
      case MotionEvent.ACTION_CANCEL:
      case MotionEvent.ACTION_UP:
      case MotionEvent.ACTION_POINTER_UP:
        point = null;
        break;
      case MotionEvent.ACTION_MOVE:
        point.x = event.getX();
        point.y = event.getY();
        break;
      
      default:
        return(false);
    }
    
    updateTextView();
    return(true);
  }
  
  protected void updateTextView() {
    if (point != null) {
      text.setText(String.format("x:%3.1f, y:%3.1f", point.x, point.y));
    }
  }
}
