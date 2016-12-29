package com.cst.growthprocess.retrofit_okhttp.callback;

import com.cst.growthprocess.retrofit_okhttp.bean.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Copyright2012-2016  CST.All Rights Reserved
 *
 * Comments：
 *
 * @author chenb
 *
 *         Time: 2016/12/1 0001
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public interface RequestFromBaidu {

    @GET("weatherservice/cityname")
    Call<Weather> getWeatherByCityName(@Query("cityname") String cityname);
}
