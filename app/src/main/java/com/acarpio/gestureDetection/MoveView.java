package com.acarpio.gestureDetection;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MoveView implements View.OnTouchListener {
    float xDown = 0;
    float yDown = 0;
    private View dropZone;
    @Override public boolean onTouch(View view, MotionEvent event) {

        switch (event.getActionMasked()) {
            // Finger touches the view
            case MotionEvent.ACTION_DOWN:
                xDown = event.getX();
                yDown = event.getY();
                break;
            // The view is moving
            case MotionEvent.ACTION_MOVE:
                float movedX = event.getX();
                float movedY = event.getY();

                float distX = movedX - xDown;
                float distY = movedY - yDown;


                ViewGroup parent = (ViewGroup) view.getParent();
                int parentWidth = parent.getWidth();
                int parentHeight = parent.getHeight();


                int viewWidth = view.getWidth();
                int viewHeight = view.getHeight();


                float newX = view.getX() + distX;
                float newY = view.getY() + distY;


                if (newX < 0) {
                    newX = 0;
                } else if (newX + viewWidth > parentWidth) {
                    newX = parentWidth - viewWidth;
                }

                // Restringir la coordenada Y para que no se salga del contenedor
                if (newY < 0) {
                    newY = 0;
                } else if (newY + viewHeight > parentHeight) {
                    newY = parentHeight - viewHeight;
                }

                // Establecer las nuevas coordenadas de la vista
                view.setX(newX);
                view.setY(newY);
                break;


            case MotionEvent.ACTION_UP:
                // Checking if the view ended where I want.
                if (BoundDetector.isViewInBounds(dropZone, view)) {
                    // Saving the drop zone's location
                    int[] dropZoneLocation = new int[2];
                    dropZone.getLocationInWindow(dropZoneLocation);

                    // Getting the parent's coordinates (the orange rectangle)
                    ViewGroup parenti = (ViewGroup) view.getParent();
                    int[] parentLocation = new int[2];
                    parenti.getLocationInWindow(parentLocation);

                    // Getting the drop zone's location
                    float relativeX = dropZoneLocation[0] - parentLocation[0];
                    float relativeY = dropZoneLocation[1] - parentLocation[1];

                    // Centering the view in the drop zone
                    float centerX = relativeX + (dropZone.getWidth() - view.getWidth()) / 2f;
                    float centerY = relativeY + (dropZone.getHeight() - view.getHeight()) / 2f;

                    // Setting it to the center.
                    view.setX(centerX);
                    view.setY(centerY);

                    Toast.makeText(view.getContext(), "Target OK", Toast.LENGTH_SHORT).show();
                }
                break;





        }



        return true;
    }


    public void setDropZone(View dropZone) {
        this.dropZone = dropZone;
    }



}
