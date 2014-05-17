package Images;

import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by larhard on 17.05.14.
 */
public class Images {

    /**
     * @param classLoader class loader with access to the resource (most often this.getClass().getClassLoader())
     * @throws IOException
     * @throws java.io.FileNotFoundException
     */
    public static Image load(ClassLoader classLoader, String path) throws IOException {
        InputStream stream = null;
        try {
            stream = classLoader.getResource(path).openStream();
            return new Image(stream);
        } catch (NullPointerException e) {
            throw new FileNotFoundException();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    /**
     * @param classLoader class loader with access to the resource (most often this.getClass().getClassLoader())
     * @param requestedWidth the image's bounding box width
     * @param requestedHeight the image's bounding box height
     * @param preserveRatio indicates whether to preserve the aspect ratio of the original image when scaling to fit the image within the specified bounding box
     * @param smooth indicates whether to use a better quality filtering algorithm or a faster one when scaling this image to fit within the specified bounding box
     * @throws IOException
     * @throws java.io.FileNotFoundException
     */
    public static Image load(ClassLoader classLoader, String path,
                             double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth)
            throws IOException {
        InputStream stream = null;
        try {
            stream = classLoader.getResource(path).openStream();
            return new Image(stream, requestedWidth, requestedHeight, preserveRatio, smooth);
        } catch (NullPointerException e) {
            throw new FileNotFoundException();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }
}
