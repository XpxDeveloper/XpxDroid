package com.xpsoft.xpxDroid.http.retrofit.loader;

import com.xpsoft.xpxDroid.http.retrofit.MovieSubject;
import com.xpsoft.xpxDroid.http.retrofit.RetrofitServiceManager;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by XPSoft on 2018/4/14.
 */

public class MovieLoader extends ObjectLoader {
    private MovieService mMovieService;
    public MovieLoader(){
        mMovieService = RetrofitServiceManager.getInstance().create(MovieService.class);
    }
    /**
     * 获取电影列表
     * @param start
     * @param count
     * @return
     */
    public Observable<List<MovieSubject.Movie>> getMovie(int start, int count){
        return observe(mMovieService.getTop250(start,count))
                .map(new Func1<MovieSubject, List<MovieSubject.Movie>>() {
                    @Override
                    public List<MovieSubject.Movie> call(MovieSubject movieSubject) {
                        return movieSubject.subjects;
                    }
                });
    }

    public Observable<String> getWeatherList(String cityId, String key){
        return observe(mMovieService.getWeather(cityId,key))
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        //可以处理对应的逻辑后在返回
                        return s;
                    }
                });
    }

    public interface MovieService{
        //获取豆瓣Top250 榜单
        @GET("top250")
        Observable<MovieSubject> getTop250(@Query("start") int start, @Query("count")int count);

        @FormUrlEncoded
        @POST("/x3/weather")
        Observable<String> getWeather(@Field("cityId") String cityId, @Field("key") String key);
    }
}

