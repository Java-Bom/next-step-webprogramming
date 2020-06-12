package myServer;

import myServer.httpRequest.HttpRequestEntity;
import myServer.httpRequest.RequestStatusLine;

/**
 * Created by jyami on 2020/04/30
 */
public class ControllerMapper {

    private static final String CONTROLLER = "Controller";
    private static final String CONTROLLER_PACKAGE = "myServer.controller.";

    public static Class<?> executeController(final HttpRequestEntity httpRequestEntity){
        RequestStatusLine statusLine = httpRequestEntity.getStatusLine();
        String controllerName = mappingControllerName(statusLine.getPath());
        try {
            return Class.forName(controllerName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    private static String mappingControllerName(String path){
        String[] split = path.split("/");
        String s = split[1];
        return CONTROLLER_PACKAGE + s.substring(0,1).toUpperCase() + s.substring(1) + CONTROLLER;
    }

}
