package Images;

import javafx.scene.image.Image;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageContainer {
    private final Map<String, Image> imageMap = new HashMap<>();
    private final ClassLoader classLoader;
    private final String path;

    public ImageContainer(ClassLoader classLoader, String path) {
        this.classLoader = classLoader;
        this.path = path;
    }

    public synchronized Image get(String name) throws IOException {
        if (!imageMap.containsKey(name)) {
            imageMap.put(name, Images.load(classLoader, path + "/" + name));
        }
        return imageMap.get(name);
    }
}
