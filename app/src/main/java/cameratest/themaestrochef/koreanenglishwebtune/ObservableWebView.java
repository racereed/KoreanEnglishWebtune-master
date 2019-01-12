package cameratest.themaestrochef.koreanenglishwebtune;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.widget.Toast;

public class ObservableWebView extends WebView
{ private OnScrollChangedCallback mOnScrollChangedCallback;

    public ObservableWebView(final Context context)
    {
        super(context);

    }

    public ObservableWebView(final Context context, final AttributeSet attrs)
    {
        super(context, attrs);
    }

    public ObservableWebView(final Context context, final AttributeSet attrs, final int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(final int l, final int t, final int oldl, final int oldt)
    {
        super.onScrollChanged(l, t, oldl, oldt);
        if(mOnScrollChangedCallback != null) mOnScrollChangedCallback.onScroll(l, t);
    }

    public OnScrollChangedCallback getOnScrollChangedCallback()
    {
        return mOnScrollChangedCallback;
    }

    public void setOnScrollChangedCallback(final OnScrollChangedCallback onScrollChangedCallback)
    {
        mOnScrollChangedCallback = onScrollChangedCallback;
    }

    /**
     * Impliment in the activity/fragment/view that you want to listen to the webview
     */
    public static interface OnScrollChangedCallback
    {
        public void onScroll(int l, int t);
    }
    GestureDetector.SimpleOnGestureListener gestListener= new GestureDetector.SimpleOnGestureListener() {

        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            if (event1.getRawX() > event2.getRawX()) {
              show_toast("swipe left");
            //} else {
               show_toast("swipe right");
            }
            //you can trace any touch events here
            return true;
        }

        public boolean onDown(MotionEvent event) {
            Log.d("TAG","onDown: ");

            // don't return false here or else none of the other
            // gestures will work
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("TAG", "onSingleTapConfirmed: ");
            return true;
        }

        public void onLongPress(MotionEvent e) {
            Log.i("TAG", "onLongPress: ");
        }

        public boolean onDoubleTap(MotionEvent e) {
            Log.i("TAG", "onDoubleTap: ");
            return true;
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            Log.i("TAG", "onScroll: ");
            return true;
        }


        };
    void show_toast(final String text) {
        Toast t = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        t.show();
    }



    };






