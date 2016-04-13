package com.example.slidemenumain;

import android.R.integer;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.provider.MediaStore.Images.Thumbnails;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextDirectionHeuristic;
import android.view.LayoutInflater;
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
    protected ImageView mImageView;
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
        Button btn = (Button)rootView.findViewById(R.id.btn);
        btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				gogoro();
				Toast.makeText(getActivity() , Integer.toString(res) ,Toast.LENGTH_LONG).show();
			    
				
			}
        	
        });
        mImageView.setClickable(true);
        mImageView.setFocusable(true);
        mImageView.setImageResource(res);
        return rootView;
    }
    
    
    public void gogoro(){
    	Intent intent = new Intent(getActivity(), CheckOutActivity.class);
    	switch (itemName) {
        case ContentFragment.BUILDING:
        	intent.putExtra("ProductSN", "294");
        	intent.putExtra("Price", "123");
        	break;
        case ContentFragment.BOOK:
        	intent.putExtra("ProductSN", "123");
        	intent.putExtra("Price", 100);
        	break;
        case ContentFragment.PAINT:
        	intent.putExtra("ProductSN", "123");
        	intent.putExtra("Price", "123");
        	break;
        case ContentFragment.CASE:
        	intent.putExtra("ProductSN", "123");
        	intent.putExtra("Price", "123");
        	break;
        case ContentFragment.SHOP:
        	intent.putExtra("ProductSN", "123");
        	intent.putExtra("Price", "123");
        	break;
        case ContentFragment.PARTY:
        	intent.putExtra("ProductSN", "123");
        	intent.putExtra("Price", "123");
        	break;
        case ContentFragment.MOVIE:
        	intent.putExtra("ProductSN", "123");
        	intent.putExtra("Price", "123");
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

