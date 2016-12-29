package com.cst.growthprocess.recyclerViewDemo.callback;

import com.cst.growthprocess.recyclerViewDemo.bean.Notes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Copyright2012-2016  CST.All Rights Reserved
 *
 * Comments：
 *
 * @author chenb
 *
 *         Time: 2016/12/5 0005
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public interface BaiDuCallBack {

    //base url = "http://apis.baidu.com/qunartravel/";

    @GET("travellist/travellist")
    Call<Notes> getNotesList(@Header("apikey") String apikey, @Query("query") String query, @Query("id") int id);
}
