package myServer.controller;


import myServer.httpRequest.HttpMethod;
import myServer.httpRequest.HttpRequestEntity;
import myServer.httpResponse.HttpResponseEntity;

/**
 * Created by jyami on 2020/06/13
 */
public class AbstractController implements Controller{

    protected void doPost(HttpRequestEntity request, HttpResponseEntity response){
    };

    protected void doGet(HttpRequestEntity request, HttpResponseEntity response){
    };

    @Override
    public void service(HttpRequestEntity request, HttpResponseEntity response) {
        HttpMethod method = request.getStatusLine().getMethod();

        if(method == HttpMethod.POST){
            doPost(request, response);
        }else{
            doGet(request, response);
        }
    }

}
