package com.example.slidemenumain;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by Konstantin on 22.12.2014.
 */
public class ContentFragment extends Fragment implements ScreenShotable {
    public static final String CLOSE = "Close";
    public static final String BUILDING = "Building";
    public static final String BOOK = "Book";
    public static final String PAINT = "Paint";
    public static final String CASE = "Case";
    public static final String SHOP = "Shop";
    public static final String PARTY = "Party";
    public static final String MOVIE = "Movie";

    public static String itemName = BUILDING;
    private View containerView;
    protected ImageView mImageView, mImageView2;
    protected int res;
    private Bitmap bitmap;

    
    public static ContentFragment newInstance(int resId, String itemNameIn) {
    	itemName = itemNameIn;
    	ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), resId);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getArguments().getInt(Integer.class.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mImageView = (ImageView) rootView.findViewById(R.id.image_content);
        mImageView.setClickable(true);
        mImageView.setFocusable(true);
        mImageView.setImageResource(res);
        
     // set maximum scroll amount (based on center of image)
        int maxX = (int)((mImageView.getWidth() / 2) - (2000 / 2));
        int maxY = (int)((mImageView.getHeight()/ 2) - (2000 / 2));

        // set scroll limits
        final int maxLeft = (maxX * -1);
        final int maxRight = maxX;
        final int maxTop = (maxY * -1);
        final int maxBottom = maxY;

//        // set touchlistener
//        mImageView.setOnTouchListener(new View.OnTouchListener()
//        {
//            float downX, downY;
//            int totalX, totalY;
//            int scrollByX, scrollByY;
//            public boolean onTouch(View view, MotionEvent event)
//            {
//                float currentX, currentY;
//                switch (event.getAction())
//                {
//                    case MotionEvent.ACTION_DOWN:
//                        downX = event.getX();
//                        downY = event.getY();
//                        break;
//
//                    case MotionEvent.ACTION_MOVE:
//                        currentX = event.getX();
//                        currentY = event.getY();
//                        scrollByX = (int)(downX - currentX);
//                        scrollByY = (int)(downY - currentY);
//
//                        // scrolling to left side of image (pic moving to the right)
//                        if (currentX > downX)
//                        {
//                            if (totalX == maxLeft)
//                            {
//                                scrollByX = 0;
//                            }
//                            if (totalX > maxLeft)
//                            {
//                                totalX = totalX + scrollByX;
//                            }
//                            if (totalX < maxLeft)
//                            {
//                                scrollByX = maxLeft - (totalX - scrollByX);
//                                totalX = maxLeft;
//                            }
//                        }
//
//                        // scrolling to right side of image (pic moving to the left)
//                        if (currentX < downX)
//                        {
//                            if (totalX == maxRight)
//                            {
//                                scrollByX = 0;
//                            }
//                            if (totalX < maxRight)
//                            {
//                                totalX = totalX + scrollByX;
//                            }
//                            if (totalX > maxRight)
//                            {
//                                scrollByX = maxRight - (totalX - scrollByX);
//                                totalX = maxRight;
//                            }
//                        }
//
//                        // scrolling to top of image (pic moving to the bottom)
//                        if (currentY > downY)
//                        {
//                            if (totalY == maxTop)
//                            {
//                                scrollByY = 0;
//                            }
//                            if (totalY > maxTop)
//                            {
//                                totalY = totalY + scrollByY;
//                            }
//                            if (totalY < maxTop)
//                            {
//                                scrollByY = maxTop - (totalY - scrollByY);
//                                totalY = maxTop;
//                            }
//                        }
//
//                        // scrolling to bottom of image (pic moving to the top)
//                        if (currentY < downY)
//                        {
//                            if (totalY == maxBottom)
//                            {
//                                scrollByY = 0;
//                            }
//                            if (totalY < maxBottom)
//                            {
//                                totalY = totalY + scrollByY;
//                            }
//                            if (totalY > maxBottom)
//                            {
//                                scrollByY = maxBottom - (totalY - scrollByY);
//                                totalY = maxBottom;
//                            }
//                        }
//
//                        mImageView.scrollBy(scrollByX, scrollByY);
//                        downX = currentX;
//                        downY = currentY;
//                        break;
//
//                }
//
//                return true;
//            }
//        });
        
       

        mImageView.setOnTouchListener(new View.OnTouchListener() {
        	float mx , my;
            public boolean onTouch(View arg0, MotionEvent event) {

                float curX, curY;

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        mx = event.getX();
                        my = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        curX = event.getX();
                        curY = event.getY();
                        // version which could switch anywhere
//                        mImageView.scrollBy((int) (mx - curX), (int) (my - curY));
                        mImageView.scrollBy(0, (int) (my - curY));
                        mx = curX;
                        my = curY;
                        break;
                    case MotionEvent.ACTION_UP:
                        curX = event.getX();
                        curY = event.getY();
                        mImageView.scrollBy(0, (int) (my - curY));
                        break;
                }

                return true;
            }
        });

        
//        mImageView2 = (ImageView) rootView.findViewById(R.id.image_content2);
//        mImageView2.setClickable(true);
//        mImageView2.setFocusable(true);
//        mImageView2.setImageResource(R.drawable.beauty_icon);
//        
        Button btn = (Button)rootView.findViewById(R.id.btn);
        btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				gogoro();
				Toast.makeText(getActivity() , Integer.toString(res) ,Toast.LENGTH_LONG).show();
			    
				
			}
        	
        });
       
        return rootView;
    }
    
    
    public void gogoro(){
    	Intent intent = new Intent(getActivity(), CheckOutActivity.class);
    	switch (itemName) {
        case ContentFragment.BUILDING:
        	intent.putExtra("ProductSN", "291");
        	intent.putExtra("Price", 590);
        	break;
        case ContentFragment.BOOK:
        	intent.putExtra("ProductSN", "292");
        	intent.putExtra("Price", 290);
        	break;
        case ContentFragment.PAINT:
        	intent.putExtra("ProductSN", "293");
        	intent.putExtra("Price", 190);
        	break;
        case ContentFragment.CASE:
        	intent.putExtra("ProductSN", "123");
        	intent.putExtra("Price", 250);
        	break;
        case ContentFragment.SHOP:
        	intent.putExtra("ProductSN", "295");
        	intent.putExtra("Price", 250);
        	break;
        case ContentFragment.PARTY:
        	intent.putExtra("ProductSN", "297");
        	intent.putExtra("Price", 180);
        	break;
        case ContentFragment.MOVIE:
        	intent.putExtra("ProductSN", "294");
        	intent.putExtra("Price", 180);
        	break;
            
    }
    	
    	startActivity(intent);
    }
    

    @Override
    public void takeScreenShot() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
                ContentFragment.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

