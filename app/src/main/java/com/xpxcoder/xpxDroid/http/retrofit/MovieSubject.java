package com.xpxcoder.xpxDroid.http.retrofit;


import java.util.List;

/**
 * Created by XPSoft on 2018/4/14.
 */

public class MovieSubject {
    public int count;
    public int start;
    public int total;
    public List<Movie> subjects;
    public String title;

    public static class Movie {
        public Rate rating;
        public String title;
        public String collect_count;
        public String original_title;
        public String subtype;
        public String year;
        public MovieImage images;

        public static class Rate {
            public int max;
            public float average;
            public String stars;
            public int min;
        }

        public static class MovieImage {
            public String small;
            public String large;
            public String medium;
        }
    }
}
