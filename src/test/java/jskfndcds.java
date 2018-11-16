import org.testng.Reporter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by yash.zanwar on 16/11/18
 */
public class jskfndcds {

    public static void main(String[] args) {

        try {
            Process process = Runtime.getRuntime().exec("adb shell pm list packages | grep com.phonepe.app.debug");
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
}
