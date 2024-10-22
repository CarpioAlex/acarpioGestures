## Public repository

CarpioAlex/acarpioGestures (github.com)

![Captura de pantalla 2024-10-16 141104](https://github.com/user-attachments/assets/95dd739e-19de-4765-bb25-a394ecd62d33)

![Captura de pantalla 2024-10-17 111936](https://github.com/user-attachments/assets/81ca887c-7966-4992-b8da-9955cea72668)

[Uploading acarpio_gestures._final.pdf…]()


## Creating the layout to limit where can you touch on the screen

To limit where the gestures will be listened to, I created two rectangles, one for
clicks, and one for flings.

## Setting up the listeners and gesture detectors

To manage each rectangle individually, I created two GestureDetectorCompat’s
one for each rectangle, and a listener for each one too.

Declaring them:


Instantiating:

The classes that implement the gesture detectors are in a separated .java class to
make reading the code easier.

Note that the first context is the activity’s context, the second parameter is the
GestureDetector class, that has two parameters in its constructor, context to be
able to use a toast inside the class, and a view’s ID that I use later.

## Adding a listener to the views

Since I don’t want to register clicks on the whole layout, and only on those two, I
fetch the ID’s and then assign a listener to each one.


The listeners will propagate the click event to their respective callback methods.

## Fling class

The FlingDetector class, handles what happens when a fling swipes UP, DOWN,
RIGHT, or LEFT on the screen.

(I adapted the code from StackOverflow)

```
fernandohur. StackOverflow. s.f.
https://stackoverflow.com/questions/13095494/how-to-detect-
```
# swipe-direction-between-left-right-and-up-down

It uses the following diagram to implement in which quadrant the swipe ends.

First it gets the angle:


Then it checks if the angle is within range, and determines what the direction of the
swipe is:

And using the direction enum it handles what to do when the swipe is done:

In this case, it logs it, displays a Toast and then moves a little ball.


## SmallRectangle touches

This class handles the touches (just to try the different callbacks) on the smaller
rectangle.

Nothing fancy, it registers a single tap and a double tap.


## Screenshots

Double tap:


Swipes:


## Adding drag and drop

I added two new views, a circle that will be the movable object, and a drop zone,
that will be the target where the circle has to get dropped.

A new listener is set for the movable view.

The drop zone is saved as a field of the class and later set in the main activity using
its setter. (Could also be inside the constructor and passed as a parameter, just
testing different ways). And initial on down positions are initialized.


**onTouch method:**

**android: move a view on touch move (ACTION_MOVE) - Stack Overflow**

**Android move view on touch event - Stack Overflow**

I’m capturing getActionMasked (same as getAction but with less things saved)

When the finger first touches the view, the coordinates are saved.

When moving, it gets the new coordinates and calculates the final position of the
view, no need to use ACTION_UP, it stays in the last position where it was moved.

To simulate a target drop zone (like a square in a chess board) I used ACTION_UP to
check if when you lift the finger you are in bounds of the drop zone, if you are, the
movable view gets centered inside.


First it checks if it’s in bounds of the drop zone, if it is, it gets the coordinates of the
drop zone inside the parent view, and then it gets the target view’s coordinates, to
calculate the center.


**Checking if the view is in bounds**

To check if the dropped objet is inside the targer view, I get the coordinates of both
and compare them.

Function adapted from: android - Check if dragged view is within the bounds of
another view - Stack Overflow


