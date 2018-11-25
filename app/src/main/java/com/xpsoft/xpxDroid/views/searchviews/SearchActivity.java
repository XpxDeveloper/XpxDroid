package com.xpsoft.xpxDroid.views.searchviews;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.models.eventbus.xpxEvent;
import com.xpsoft.xpxDroid.views.baseFragActivity;


/**
 * Created by XPSoft on 2018/7/22.
 */

public class SearchActivity extends baseFragActivity implements Toolbar.OnMenuItemClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.btn_back_b);
//        toolbar.setNavigationContentDescription("返回");
//        toolbar.setLogo(R.drawable.close_white);

        setSupportActionBar(toolbar);
       toolbar.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });

        toolbar.setOnMenuItemClickListener(this);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       /* TextView textView = new TextView(mContext);
        textView.setText("asdfas");

        Toolbar.LayoutParams params = new Toolbar.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        toolbar.addView(textView, params);*/
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public void XpxEvent(xpxEvent _xpxEvent) {

    }

    @Override
    public void handleMsg(Message msg) {

    }
}
