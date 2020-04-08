package com.xpxcoder.xpxDroid.views;

import androidx.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Message;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import com.xpxcoder.xpxDroid.R;
import com.xpxcoder.xpxDroid.adapter.recyclerview.MovieAdapter;
import com.xpxcoder.xpxDroid.databinding.ActivityRetrofitBinding;
import com.xpxcoder.xpxDroid.http.retrofit.Fault;
import com.xpxcoder.xpxDroid.http.retrofit.MovieSubject;
import com.xpxcoder.xpxDroid.http.retrofit.loader.MovieLoader;
import com.xpxcoder.xpxDroid.models.eventbus.xpxEvent;
import com.xpxcoder.xpxDroid.widget.AppTitleBar;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by XPSoft on 2018/4/14.
 */

public class RetrofitActivity extends baseFragActivity {

    private MovieLoader mMovieLoader= new MovieLoader();
    private ActivityRetrofitBinding mBinding;
    private MovieAdapter mMovieAdapter;

    @Override
    public void handleMsg(Message msg) {

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_retrofit);
        mBinding.appTitleBar.setTitle("retrofit").setOnTitleBarClickListener(new AppTitleBar.OnTitleBarClickListener() {
            @Override
            public void onBack() {
                finish();
            }
        });
        initView();
    }
    private void initView() {

        mBinding.recyclerView.addItemDecoration(new MovieDecoration());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.recyclerView.setLayoutManager(manager);
        mMovieAdapter = new MovieAdapter();
        mBinding.recyclerView.setAdapter(mMovieAdapter);
        getMovieList();

    }

    /**
     * 获取电影列表
     */
    private void getMovieList(){
        mMovieLoader.getMovie(0,10).subscribe(new Action1<List<MovieSubject.Movie>>() {
            @Override
            public void call(List<MovieSubject.Movie> movies) {
                mMovieAdapter.setMovies(movies);
                mMovieAdapter.notifyDataSetChanged();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e("TAG","error message:"+throwable.getMessage());
                if(throwable instanceof Fault){
                    Fault fault = (Fault) throwable;
                    if(fault.getErrorCode() == 404){
                        //错误处理
                    }else if(fault.getErrorCode() == 500){
                        //错误处理
                    }else if(fault.getErrorCode() == 501){
                        //错误处理
                    }
                }
            }
        });

    }



    public static class MovieDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0,0,0,20);
        }
    }

    @Override
    public void XpxEvent(xpxEvent _xpxEvent) {

    }
}
