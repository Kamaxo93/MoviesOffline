package com.camacho.moviesoffline.data.remote;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl originalUrl = originalRequest.url();
        HttpUrl url = originalUrl.newBuilder().addQueryParameter("api_key", ApiConstant.API_KEY)
                .build();

        Request request = originalRequest.newBuilder().url(url).build();

        return chain.proceed(request);
    }
}
