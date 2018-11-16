import FrameWork.FrameWork;
import org.testng.TestNG;

/**
 * created by yash.zanwar on 14/11/18
 */

public class TestRunner {
    public static void main(String[] args) {
        String TestcaseIds;
        if (System.getProperty("TestGroup") != null) {
            TestcaseIds = System.getProperty("TestGroup");
        } else {
            TestcaseIds = "sanity";
        }
        TestNG testng = new TestNG();
        testng.setThreadCount(1);
        testng.setDataProviderThreadCount(1);
        testng.setParallel("methods");
        String TestGroup = System.getProperty("TestGroup");

        if (TestGroup != null) {
            testng.setGroups(TestGroup);
        } else {

            testng.setGroups(TestcaseIds);
        }
        testng.setVerbose(12);
        testng.setTestClasses(new Class[]{suite.class, FrameWork.class});
        testng.run();

    }

}
