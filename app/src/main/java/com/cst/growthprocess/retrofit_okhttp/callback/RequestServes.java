package com.cst.growthprocess.retrofit_okhttp.callback;

import com.cst.growthprocess.retrofit_okhttp.bean.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

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

public interface RequestServes {

    @GET("retrofit.php")
    Call<List<User>> getUserList();

}
