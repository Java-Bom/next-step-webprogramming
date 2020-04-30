package webserver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResourceExtractor {
    private final String rootPath;
    private final List<String> paths;

    public ResourceExtractor(String rootPath) {
        this.rootPath = rootPath;
        File file = new File(rootPath);
        this.paths = extractFile(file);
    }

    public List<String> getPaths() {
        return paths;
    }

    private List<String> extractFile(File file) {
        List<String> filePath = new ArrayList<>();

        if (file.isDirectory()) {

            if (!hasFile(file)) {
                return new ArrayList<>();
            }

            for (File f : Objects.requireNonNull(file.listFiles())) {
                List<String> extractPaths = extractFile(f);
                filePath.addAll(extractPaths);
            }
        } else {
            filePath.add(splitRootPath(file.getPath()));
        }

        return filePath;
    }

    private boolean hasFile(File file) {
        return file.listFiles() != null;
    }


    private String splitRootPath(String path) {
        return path.split(this.rootPath)[1];
    }
}
