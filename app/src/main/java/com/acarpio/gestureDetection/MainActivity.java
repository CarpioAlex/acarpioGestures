package com.acarpio.gestureDetection;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Gesture detector
    private GestureDetectorCompat mDetector1;
    private GestureDetectorCompat mDetector2;

    // Debug constants
    private static final String DEBUG_TAG = "Gestures";


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        // Edges
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the gesture detector
        // (this (context), new GestureDetector, app context for the toast)
        mDetector1 = new GestureDetectorCompat(this, new FlingDetector(this, findViewById(R.id.pelota)));
        mDetector2 = new GestureDetectorCompat(this, new SmallRectangleDetector(this));

        // Getting the view of my "sweapable area"

        View areaGestos = findViewById(R.id.swipableFrame);
        View areaTouch = findViewById(R.id.touchableArea);



        // Big rectangle listener
        areaGestos.setOnTouchListener(new View.OnTouchListener(){


            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.swipableFrame) {
                    mDetector1.onTouchEvent(event);
                    return true;
                }
                return false;
                }

        });

        // Small rectangle listener

        areaTouch.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.touchableArea) {
                    mDetector2.onTouchEvent(event);
                    return true;
                }
                return false;
                }
        });


        ImageView imagenAMover = findViewById(R.id.Circle);
        View dropZone = findViewById(R.id.dropContainer);


        MoveView listenerMover = new MoveView();
        listenerMover.setDropZone(dropZone);
        imagenAMover.setOnTouchListener(listenerMover);






        // onCreate ENDING
    }







}