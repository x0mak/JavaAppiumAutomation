package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception{

        super.setUp();

        driver = initDriver();
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception{
        driver.quit();

        super.tearDown();
    }

    protected void rotateScreenPortrait(){
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape(){
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds){
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }

    private AppiumDriver initDriver() throws Exception {
        String platform = System.getenv("PLATFORM");
        URL url = new URL(AppiumURL);
        switch (platform){
            case PLATFORM_ANDROID:
                return new AndroidDriver(url, getCapabilitiesByPlatformEnv());
            case PLATFORM_IOS:
                return new IOSDriver(url, getCapabilitiesByPlatformEnv());
            default:
                throw new RuntimeException("Неизсветный тип платформы, " + platform);
        }
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception
    {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if(platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/home/lena/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone SE");
            capabilities.setCapability("platformVersion", "11.3");
            capabilities.setCapability("app", "/home/lena/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
        } else {
            throw new Exception("Cannot get run platform from env variable. Platform value - " + platform);
        }

        return capabilities;
    }
}
