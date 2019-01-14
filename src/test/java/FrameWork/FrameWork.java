package FrameWork;
/**
 * created by yash.zanwar on 14/11/18
 */

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrameWork {


    public String getPath() {
        String oldApkPath;
        if (System.getProperty("OldApk.apk") != null) {
            oldApkPath = new File("OldApk.apk").getAbsolutePath();
            System.out.println(oldApkPath);
        } else {
            oldApkPath = "/Users/yash.zanwar/Desktop/phonepeapp-developers-stage-universal-debug.apk";
        }
        return oldApkPath;
    }


    public AndroidDriver getAndroidDriver(AppiumDriverLocalService appiumServiceLocal, String DeviceName) throws MalformedURLException {
        AppiumDriverLocalService appiumService = appiumServiceLocal;
        String appiumServiceUrl = appiumService.getUrl().toString();
        System.out.println("Appium Service Address ************************: - " + appiumServiceUrl);
        System.out.println("Appium Service Device *************************: - " + DeviceName);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "HNB3W8Y0");
        capabilities.setCapability(AndroidMobileCapabilityType.NO_SIGN, false);
        capabilities.setCapability("newCommandTimeout", 45000);
        capabilities.setCapability("autoGrantPermissions", "true");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.phonepe.app.debug");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.phonepe.app.ui.activity.Navigator_MainActivity");
        AndroidDriver driver = new AndroidDriver(new URL(appiumServiceUrl), capabilities);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }

    public AppiumDriverLocalService getAppiumDriverLocalService() {
        AppiumDriverLocalService appiumService = AppiumDriverLocalService.buildDefaultService();
        appiumService.start();
        return appiumService;
    }

    public String getAttachedDevices() {

        List DeviceList = new ArrayList<String>();

        try {
            Process process = Runtime.getRuntime().exec("adb devices");
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            Pattern pattern = Pattern.compile("^([a-zA-Z0-9\\-]+)(\\s+)(device)");
            Matcher matcher = null;
            while ((line = in.readLine()) != null) {
                if (line.matches(pattern.pattern())) {
                    matcher = pattern.matcher(line);
                    if (matcher.find())
                        System.out.println(matcher.group(1));
                    DeviceList.add(matcher.group(1));
                }
            }
            return matcher.group(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void unInstallExistingAppIfAny() {
        try {
            Process process = Runtime.getRuntime().exec("adb shell pm list packages | grep com.phonepe.app");
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                if (line.contains("phonepe")) {
                    line = line.split("package:")[1];
                    Runtime.getRuntime().exec("adb uninstall " + line);
                    Reporter.log("Uninstalled " + line, true);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void installApp(String path) {
        try {
            Process process = Runtime.getRuntime().exec("adb install -r " + path);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                if (line.contains("Success")) {
                    Reporter.log("App Installed", true);
                } else {
                    Reporter.log("App Not Installed", true);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        System.out.println(System.getProperty("test"));
        unInstallExistingAppIfAny();
        installApp(getPath());
    }
}