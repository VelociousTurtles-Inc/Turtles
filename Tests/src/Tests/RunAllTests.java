package Tests;

import Enums.ColorsTest;
import Images.ImagesTest;
import Utility.RangeTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class RunAllTests extends TestSuite {

    /**
     * to add new TestCase simply suite.addTestSuite(TestCase.class);
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite();
        suite.addTestSuite(ImagesTest.class);
        suite.addTestSuite(ColorsTest.class);
        suite.addTestSuite(RangeTest.class);
        return suite;
    }
}
