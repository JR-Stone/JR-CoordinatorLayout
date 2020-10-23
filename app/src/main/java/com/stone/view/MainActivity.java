package com.stone.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author nnr
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.appBar)
    AppBarLayout appBarLayout;
    @BindView(R.id.nestScrollView)
    NestedScrollView scrollView;
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;

    private CoordinatorLayout.LayoutParams layoutParams;
    private AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
    }

    @OnClick({R.id.jumpUrl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.jumpUrl:
                startActivity(new Intent(this,Main2Activity.class));
                break;
            default:
                break;
        }

    }
}
