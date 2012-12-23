package com.tophyr;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.tophyr.coverflow.CoverFlow;
import com.tophyr.coverflow.MutableAdapter;

public class CoverFlowDemo extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cover_flow_demo);
        
        CoverFlow cp = (CoverFlow)findViewById(R.id.coverflow);
        cp.setFocusable(true);
        cp.setFocusableInTouchMode(true);
        cp.requestFocus();
        
        final ArrayList<Drawable> images = new ArrayList<Drawable>();
        images.addAll(Arrays.asList(new Drawable[] {
    		getResources().getDrawable(R.drawable.img0),
    		getResources().getDrawable(R.drawable.img1),
    		getResources().getDrawable(R.drawable.img2),
    		getResources().getDrawable(R.drawable.img3),
    		getResources().getDrawable(R.drawable.img4),
    		getResources().getDrawable(R.drawable.img5),
    		getResources().getDrawable(R.drawable.img6),
    		getResources().getDrawable(R.drawable.img7)
        }));
        
        cp.setAdapter(new MutableAdapter<Drawable>() {
        	@Override
        	public View getView(int position, View convert, ViewGroup parent) {
        		ImageView v;
        		if (convert instanceof ImageView)
        			v = (ImageView)convert;
        		else {
        			v = new ImageView(CoverFlowDemo.this);
        			v.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        			v.setScaleType(ScaleType.FIT_CENTER);
        			v.setBackgroundColor(Color.LTGRAY);
        		}
        		
        		v.setImageDrawable(getItem(position));
        		
        		return v;
        	}

			@Override
			public int getCount() {
				return images.size();
			}

			@Override
			public Drawable getItem(int position) {
				return images.get(position);
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public void onAdd(Drawable added) { }

			@Override
			public void onRemove(int position) {
				images.remove(position);
			}

			@Override
			public void onInsert(Drawable added, int position) {
				images.add(position, added);
			}

			@Override
			public void onReplace(Drawable replacing, int position) { }

			@Override
			public void onSwap(int pos1, int pos2) { }
			
			@Override
			public void onMove(int oldPos, int newPos) {
				Drawable i = getItem(oldPos);
				images.remove(oldPos);
				images.add(newPos, i);
			}
        });
        
        cp.setPosition(3);
    }
}
