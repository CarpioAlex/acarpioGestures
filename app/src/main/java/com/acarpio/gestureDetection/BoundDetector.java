package com.acarpio.gestureDetection;

import android.view.View;

public class BoundDetector {
    public static boolean isViewInBounds(View targetView, View draggedView) {
        // Getting the coordinates of the target view
        int[] targetLocation = new int[2];
        targetView.getLocationOnScreen(targetLocation);
        int targetLeft = targetLocation[0];
        int targetTop = targetLocation[1];
        int targetRight = targetLeft + targetView.getWidth();
        int targetBottom = targetTop + targetView.getHeight();

        // Getting the coordinates of the dragged view
        int[] draggedLocation = new int[2];
        draggedView.getLocationOnScreen(draggedLocation);
        int draggedLeft = draggedLocation[0];
        int draggedTop = draggedLocation[1];
        int draggedRight = draggedLeft + draggedView.getWidth();
        int draggedBottom = draggedTop + draggedView.getHeight();

        // Checking if they are in bounds
        return draggedLeft < targetRight && draggedRight > targetLeft
                && draggedTop < targetBottom && draggedBottom > targetTop;
    }
}
