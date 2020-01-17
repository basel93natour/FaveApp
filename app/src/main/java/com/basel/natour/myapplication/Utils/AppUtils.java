package com.basel.natour.myapplication.Utils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class AppUtils {

    public static OkHttpClient.Builder getOkHttpClient()
    {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …
        // add logging as last interceptor
        return  httpClient.addInterceptor(logging);  // <-- this is the important line!

    }
}
