package myServer.exception;

/**
 * Created by jyami on 2020/05/30
 */
public class PathNotFoundException extends RuntimeException{
    public PathNotFoundException(String message) {
        super(message);
    }
}
