package com.example.restclient;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RestClient {
    private final static Logger logger = LoggerFactory.getLogger(RestClient.class);

    public Response invokeGet(String url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            logger.error("Can not execute" + e);
        }
        return response;
    }
}
