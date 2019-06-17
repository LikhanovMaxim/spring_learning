package com.example.restclient;

import okhttp3.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestClientTest {

    private RestClient restClient;

    @BeforeEach
    void setUp() {
        restClient = new RestClient();
    }

    @Test
    void name() {
        String url = "https://www.google.com/";
        Response response = restClient.invokeGet(url);
        assertEquals(200, response.code());
    }

}
