package com.acarpio.gestureDetection;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

// Class that manages the touches in the smaller rectangle
public class SmallRectangleDetector extends GestureDetector.SimpleOnGestureListener {
    private static final String TAG = "Touch";
    private Context context;

    public SmallRectangleDetector(Context context) {
        this.context = context;
    }

    @Override
    public boolean onDown(MotionEvent event) {
        Log.d(TAG, "onDown pressed, propagating event:  " + event.toString());
        return true;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d(TAG, "onSingleTapUp:  " + event.toString());
        Toast.makeText(context, "You tapped!", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d(TAG, "onDoubleTap:  " + e.toString());
        Toast.makeText(context, "You double tapped!", Toast.LENGTH_SHORT).show();
        return super.onDoubleTap(e);
    }
}