package com.heeresonline.androidgames;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class AccelerometerTest extends Activity implements SensorEventListener {
  private TextView text;
  
  /* (non-Javadoc)
   * @see android.app.Activity#onCreate(android.os.Bundle)
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    text = new TextView(this);
    setContentView(text);
    
    SensorManager manager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
    List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ACCELEROMETER);
    if (sensors.size() > 0) {
      if (manager.registerListener(this,  sensors.get(0), SensorManager.SENSOR_DELAY_GAME)) {
        
      }
      else text.setText("Failed to register accelerometer.");
    }
    else text.setText("No accelerometer available.");
  }

  @Override
  public void onSensorChanged(SensorEvent event) {
    text.setText(String.format("x:%2.6f, y:%2.6f, z:%2.6f", event.values[0], event.values[1], event.values[2]));
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) {
  }
}
