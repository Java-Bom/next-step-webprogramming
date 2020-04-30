package webserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class RequestUriContainerTest {

    @DisplayName("라인별 요청헤더에서 url를 찾아준다")
    @Test
    void add() {
        //given
        File file = new File("./webapp").getAbsoluteFile();
        List<String> extractFiles = extractFile(file);
        for (String extractFile : extractFiles) {
            System.out.println(extractFile);
        }
    }


    private List<String> extractFile(File file) {
        List<String> filePath = new ArrayList<>();

        if (file.isDirectory()) {

            if (!hasFile(file)) {
                return new ArrayList<>();
            }

            for (File f : file.listFiles()) {
                List<String> extractPaths = extractFile(f);
                filePath.addAll(extractPaths);
            }
        } else {
            filePath.add(file.getPath());
        }

        return filePath;
    }

    private boolean hasFile(File file) {
        return file.listFiles() != null;
    }

}