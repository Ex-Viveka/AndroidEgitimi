package com.kanilturgut.phonemarketapp.backend;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Author   : kanilturgut
 * Date     : 21/10/15
 * Time     : 18:10
 */
public class Rest {

    private static Rest instance;
    private OkHttpClient mOkHttpClient;

    private Rest() {
        mOkHttpClient = new OkHttpClient();
    }

    public static Rest getInstance() {

        if (instance == null)
            instance = new Rest();

        return instance;
    }

    public <S> S createService(Class<S> serviceClass) {

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint("http://bilisimmucitleri.herokuapp.com")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(mOkHttpClient));

        RestAdapter adapter = builder.build();

        return adapter.create(serviceClass);
    }
}
