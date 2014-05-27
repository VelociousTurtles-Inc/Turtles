package Images;

import javafx.scene.image.Image;
import junit.framework.TestCase;

import java.io.IOException;
import java.rmi.RemoteException;

public class ImagesTest extends TestCase {

    public void testLoad() throws IOException {
        Image image = Images.load(this.getClass().getClassLoader(), "Images/TestResources/TestImage.png");
        assertEquals(170.0, image.getWidth());
        image = Images.load(this.getClass().getClassLoader(), "Images/TestResources/TestImage.png", 200, 100, false, false);
        assertEquals(200.0, image.getWidth());
        image = Images.load(this.getClass().getClassLoader(), "Images/TestResources/TestImage.png", 200, 100, true, false);
        assertEquals(100.0, image.getWidth());
    }
}