package com.kanilturgut.phonemarketapp.backend;

import com.kanilturgut.phonemarketapp.backend.pojo.LoginPojo;
import com.kanilturgut.phonemarketapp.backend.pojo.RegisterPojo;
import com.kanilturgut.phonemarketapp.backend.pojo.SettingsPojo;
import com.kanilturgut.phonemarketapp.backend.pojo.UserSettingsPojo;
import com.kanilturgut.phonemarketapp.model.Product;
import com.kanilturgut.phonemarketapp.model.User;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Author   : kanilturgut
 * Date     : 21/10/15
 * Time     : 18:18
 */
public interface Endpoints {

    @POST("/register")
    void register(@Body RegisterPojo registerPojo, Callback<User> callback);

    @POST("/login")
    void login(@Body LoginPojo loginPojo, Callback<User> callback);

    @GET("/product/promoted")
    void getPromotedProduct(Callback<List<Product>> callback);

    @GET("/product")
    void getAllProducts(Callback<List<Product>> callback);

    @POST("/user")
    void getUserSettings(@Body SettingsPojo pojo, Callback<User> callback);

    @POST("/user/edit")
    void changeUserSettings(@Body UserSettingsPojo pojo, Callback<User> callback);
}
