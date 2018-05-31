package com.bhm.sdk.demo.http;

import com.bhm.sdk.demo.entity.DoGetEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by bhm on 2018/5/11.
 */

public interface HttpApi {

    @GET("/api/4/news/latest")
    Observable<DoGetEntity> getData(@Header("token") String token,
                                    @Query("type") String type);
}
