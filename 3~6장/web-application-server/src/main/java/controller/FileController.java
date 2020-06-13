package controller;

import http.CysHttpRequest;
import http.CysHttpResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileController implements Controller {
    private final String fileName;
    private final String extension;

    public FileController(final String fileName) {
        this.fileName = fileName;
        String[] split = fileName.split("\\.");
        int lastIndex = split.length - 1;
        this.extension = split[lastIndex];
    }

    @Override
    public void service(CysHttpRequest httpRequest, CysHttpResponse httpResponse) throws IOException {
        byte[] body = Files.readAllBytes(new File("./webapp" + fileName).toPath());
        httpResponse.response200Header(body, extension);
    }
}
