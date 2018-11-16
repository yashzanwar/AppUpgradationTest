package Pages;
/**
 * created by yash.zanwar on 14/11/18
 */

import FrameWork.AppAction;
import FrameWork.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class HomePage extends AppAction {

    public HomePage(AndroidDriver driver){
        this.driver = driver;
    }

    /*********************************************** Locators ************************************************************************/

    Locator sendMoney(){
        return new Locator(By.xpath("//*[@text='To Contact']"),"Send Money Button");
    }

    Locator collectPopUp(){
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'blocking_collect_item_container')]"),"Collect Pop Up");
    }

    Locator cancelCollect(){
        return new Locator(By.xpath("//android.widget.ImageView[contains(@resource-id,'iv_cancel')]"),"Cancel Collect Request");
    }
    /************************************************** Methods **********************************************************************/

    public void navigateSendMoney(){
        if(waitUntilDisplayed(collectPopUp(),5)){
            click(cancelCollect());
        }
        click(sendMoney());

    }
}
