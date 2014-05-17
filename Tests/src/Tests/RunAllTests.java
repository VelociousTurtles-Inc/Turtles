package Tests;

import Images.ImagesTest;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by larhard on 07.05.14.
 */
public class RunAllTests extends TestSuite {

    /**
     * to add new TestCase simply suite.addTestSuite(TestCase.class);
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite();
        suite.addTestSuite(Images.ImagesTest.class);
        return suite;
    }
}
