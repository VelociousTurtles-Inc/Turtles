package Tests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by larhard on 07.05.14.
 */
public class RunAllTests extends TestSuite {
    public static Test suite() {
        final TestSuite suite = new TestSuite();
        //suite.addTestSuite(Test.class);
        return suite;
    }
}
