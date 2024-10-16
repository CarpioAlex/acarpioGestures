package com.acarpio.gestureDetection;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class FlingDetector extends GestureDetector.SimpleOnGestureListener {
    private static final String TAG = "Gestures";
    protected Context context;
    View pelota;

    public FlingDetector(Context context, View pelota) {
        this.context = context;
        this.pelota = pelota;

    }



    /**
     * This method sets the angles for each direction using an enumeration return.
     */

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;

        public static Direction fromAngle(double angle) {
            if (inRange(angle, 45, 135)) {
                return Direction.UP;
            } else if (inRange(angle, 225, 315)) {
                return Direction.DOWN;
            } else if (inRange(angle, 135, 225)) {
                return Direction.LEFT;
            } else {
                return Direction.RIGHT;
            }
        }

        /**
         * Auxiliar method that makes sure that a given angle it's withing range, if not, the gesture won't be fired.
         */
        private static boolean inRange(double angle, float start, float end) {
            return (angle >= start) && (angle < end);
        }
    }

    public double getAngle(float x1, float y1, float x2, float y2) {
        double rad = Math.atan2(y1 - y2, x2 - x1) + Math.PI;
        return (rad * 180 / Math.PI + 180) % 360;
    }




    @Override
    public boolean onDown(MotionEvent event) {
        Log.d(TAG, "onDown pressed:  " + event.toString());
        return true;
    }



    // Swipes
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        // Grab two events located on the plane at e1=(x1, y1) and e2=(x2, y2)
        // Let e1 be the initial event
        // e2 can be located at 4 different positions, consider the following diagram
        // (Assume that lines are separated by 90 degrees.)
        //
        //
        //         \ A  /
        //          \  /
        //       D   e1   B
        //          /  \
        //         / C  \
        //
        // So if (x2,y2) falls in region:
        //  A => it's an UP swipe
        //  B => it's a RIGHT swipe
        //  C => it's a DOWN swipe
        //  D => it's a LEFT swipe
        //

        // Getting the positions from the events

        float x1 = e1.getX();
        float y1 = e1.getY();
        float x2 = e2.getX();
        float y2 = e2.getY();

        // Implementing the methods that detect the angle
        double angle = getAngle(x1, y1, x2, y2);
        Direction direction = Direction.fromAngle(angle);


        switch (direction) {
            case UP:
                Log.d(TAG, "onFling: UP");
                Toast.makeText(context, "You swiped UP!", Toast.LENGTH_SHORT).show();
                pelota.animate().translationYBy(-100);
                break;
            case DOWN:
                Log.d(TAG, "onFling: DOWN");
                Toast.makeText(context, "You swiped DOWN!", Toast.LENGTH_SHORT).show();
                pelota.animate().translationYBy(100);
                break;
            case LEFT:
                Log.d(TAG, "onFling: LEFT");
                Toast.makeText(context, "You swiped LEFT!", Toast.LENGTH_SHORT).show();
                break;
            case RIGHT:
                Toast.makeText(context, "You swiped RIGHT!", Toast.LENGTH_SHORT).show();

                break;
        }

        return true;
    }



}