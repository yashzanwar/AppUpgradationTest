import FrameWork.FrameWork;
import Pages.HomePage;
import Pages.LoginPage;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;

public class suite {

    public String getPath() {
        String path;
        if (System.getProperty("LatestApk.apk") != null) {
            path = new File("LatestApk.apk").getAbsolutePath();
        }else {
            path = "/Users/yash.zanwar/Desktop/phonepeapp-developers-stage-debug.apk";
        }
        return path;
    }

    @Test(groups = "sanity")
    public void skhedjfmsdkjf() throws MalformedURLException {
        FrameWork frameWork = new FrameWork();
        String DeviceName = frameWork.getAttachedDevices();
        AppiumDriverLocalService appiumDriverLocalService = frameWork.getAppiumDriverLocalService();
        AndroidDriver driver = frameWork.getAndroidDriver(appiumDriverLocalService, DeviceName);
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginWithMobileNumber();
            HomePage homePage = new HomePage(driver);
            homePage.navigateSendMoney();
            frameWork.installApp(getPath());
            Activity activity = new Activity("com.phonepe.app.debug", "com.phonepe.app.ui.activity.Navigator_MainActivity");
            driver.startActivity(activity);
            homePage.navigateSendMoney();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            driver.quit();
            appiumDriverLocalService.stop();
        }
    }

}
