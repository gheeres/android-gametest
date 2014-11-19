package com.heeresonline.androidgames;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

  private final String[] activities = new String[] {
    "SingleTouchTest", "MultiTouchTest", "AccelerometerTest", "FullScreenTest",
    "RenderViewTest", "ShapeTest", "SurfaceViewTest"
  };
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setListAdapter(new ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_1, activities));
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  /* (non-Javadoc)
   * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
   */
  @Override
  protected void onListItemClick(ListView list, View view, int position, long id) {
    super.onListItemClick(list, view, position, id);
    
    String activity = activities[position];
    try {
      Intent intent = new Intent(this, Class.forName(String.format("com.heeresonline.androidgames.%s", activity)));
      startActivity(intent);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
}
