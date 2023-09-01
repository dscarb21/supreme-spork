package com.david.gridsim;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the ImageView and buttons
        final ImageView backgroundImageView = findViewById(R.id.backgroundImageView);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        // Get screen dimensions
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = (int)(size.x * 1.5);
        int height = (int)(size.y * 1.5);
        int dimension = Math.max(width, height);

        // Make ImageView square and larger than the screen
        ViewGroup.LayoutParams layoutParams = backgroundImageView.getLayoutParams();
        layoutParams.width = dimension;
        layoutParams.height = dimension;
        backgroundImageView.setLayoutParams(layoutParams);

        // Create rotation animation
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(30000); // Duration in milliseconds
        rotate.setRepeatCount(Animation.INFINITE); // Repeat indefinitely
        rotate.setInterpolator(new LinearInterpolator());

        // Start the animation on the ImageView
        backgroundImageView.startAnimation(rotate);

        // Set onClick listeners for the buttons
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
