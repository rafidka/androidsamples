package com.rafidka.viewflipper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
  private ViewFlipper viewFlipper;
  private float lastX;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

    // Hint: Sets the animation that will be used for scaling
    // views in and out.
    viewFlipper.setInAnimation(this, R.anim.scale_in);
    viewFlipper.setOutAnimation(this, R.anim.scale_out);

    // Hint: Flip the views on click.
    viewFlipper.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        flipView();
      }
    });
  }

  public void flipView() {
    if (viewFlipper.getDisplayedChild() == 0) {
      // Hint: If we are already displaying the first child,
      // show the next child.
      viewFlipper.showNext();
    } else {
      // Hint: If we are already displaying the second child,
      // show the previous child.
      viewFlipper.showPrevious();
    }
  }
}
