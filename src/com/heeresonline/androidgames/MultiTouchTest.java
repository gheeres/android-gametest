package com.heeresonline.androidgames;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MultiTouchTest extends Activity implements OnTouchListener {
  private PointF[] points = new PointF[10];
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
    int action = event.getAction() & MotionEvent.ACTION_MASK;
    int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
    int pointerId = event.getPointerId(pointerIndex);
    
    switch(action) {
      case MotionEvent.ACTION_DOWN:
      case MotionEvent.ACTION_POINTER_DOWN:
        points[pointerId] = new PointF(event.getX(pointerIndex), event.getY(pointerIndex));
        break;
      case MotionEvent.ACTION_CANCEL:
      case MotionEvent.ACTION_UP:
      case MotionEvent.ACTION_POINTER_UP:
        points[pointerId] = null;
        break;
      case MotionEvent.ACTION_MOVE:
        for(int index = 0, events = event.getPointerCount(); index < events; index++) {
          pointerId = event.getPointerId(index);
          points[pointerId].x = event.getX(index);
          points[pointerId].y = event.getY(index);
        }
        break;
      
      default:
        return(false);
    }
    
    updateTextView();
    return(true);
  }
  
  protected void updateTextView() {
    StringBuilder output = new StringBuilder();
    for(int index = 0, count = points.length; index < count; index++) {
      if (points[index] != null) {
        output.append(String.format("[%d] x:%3.1f, y:%3.1f", index, points[index].x, points[index].y));
      }
      else output.append(String.format("[%d]", index));
      output.append("\n");
    }
    text.setText(output.toString());
  }
}
