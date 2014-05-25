package Images;

import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by larhard on 26.05.14.
 */
public class ImageContainer {
    private Map<String, Image> imageMap = new HashMap<>();
    private ClassLoader classLoader;
    private String path;

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
