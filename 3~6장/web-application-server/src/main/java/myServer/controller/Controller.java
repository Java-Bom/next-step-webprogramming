package myServer.controller;

import myServer.httpRequest.HttpRequestEntity;
import myServer.httpResponse.HttpResponseEntity;

/**
 * Created by jyami on 2020/06/13
 */
public interface Controller {
    void service(HttpRequestEntity request, HttpResponseEntity response);
}
