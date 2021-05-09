 Declare a Global Variable 
 
    private GestureDetector mDetector;
 
 Initialise detector in onCreate 

    detector = new SimpleGestureListener(this,this);
    
Overrive dispatchTouchEvent of activity to send toch events to detector

    @Override
    public boolean dispatchTouchEvent(MotionEvent me){
        // Call onTouchEvent of SimpleGestureListener class
        this.detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }
