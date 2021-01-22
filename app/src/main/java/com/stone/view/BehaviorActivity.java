package com.stone.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.google.android.material.appbar.AppBarLayout;

/**
 * @author nnr
 */
public class BehaviorActivity extends AppCompatActivity {
    AppBarLayout appBarLayout;
    NestedScrollView scrollView;
    MapView mapView;

    private CoordinatorLayout.LayoutParams layoutParams;
    private AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior);
        appBarLayout = findViewById(R.id.appBar);
        scrollView = findViewById(R.id.nestScrollView);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        layoutParams = (CoordinatorLayout.LayoutParams) scrollView.getLayoutParams();
        layoutParams.setMargins(30, 0, 30, 0);
        scrollView.setLayoutParams(layoutParams);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                float a = (float) 30 / appBarLayout.getTotalScrollRange();
                int side = (int) Math.rint(a * i + 30);
                layoutParams.setMargins(side, 0, side, 0);
                scrollView.setLayoutParams(layoutParams);
                if (Math.abs(i) > 0) {
                    float alpha = (float) Math.abs(i) / appBarLayout.getTotalScrollRange();
                    appBarLayout.setAlpha(alpha);
                    scrollView.getBackground().mutate().setAlpha(Math.round(alpha * 255));
                } else {
                    appBarLayout.setAlpha(0);
                    scrollView.getBackground().mutate().setAlpha(0);
                }
            }
        });
        ((Toolbar)findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
