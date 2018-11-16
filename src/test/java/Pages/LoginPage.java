package Pages;
/**
 * created by yash.zanwar on 14/11/18
 */

import FrameWork.AppAction;
import FrameWork.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginPage extends AppAction {

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }

    /*********************************************** Locators ************************************************************************/

    Locator signIn() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tv_already_have_account_sign_in')]"), "Sign In Button");
    }

    Locator mobileNumber() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'et_phone_number')]"), "Mobile Number Text Box");
    }

    Locator passwordTextBox(String index) {
        return new Locator(By.xpath("//android.widget.LinearLayout[contains(@resource-id,'met_password')]//android.widget.EditText[@index='" + index + "']"), "Password Text Box");
    }

    Locator loginButton() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tv_verifyuser_sign_in')]"), "Login Button");
    }

    /************************************************** Methods ********************************************************************/

    public void loginWithMobileNumber() throws InterruptedException {
        click(signIn());
        EnterValue(mobileNumber(),"9538967218");
        EnterValue(passwordTextBox("0"),"5");
        EnterValue(passwordTextBox("1"),"9");
        EnterValue(passwordTextBox("2"),"5");
        EnterValue(passwordTextBox("3"),"5");
        click(loginButton());
        waitUntilElementDisappear(mobileNumber(),10);
    }


}
