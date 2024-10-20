package testcases;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.time.Duration;

import static drivers.DriverFactory.getNewInstance;
import static drivers.DriverHolder.getDriver;
import static drivers.DriverHolder.setDriver;

public class TestBase
{
    static WebDriver driver;

    // Java class include before and after methods
    @Parameters(value = "browserName")
    @BeforeTest
    public void setupWebDriver(String browserName)
    {
if(browserName == null){
    browserName = "safari";
}
        setDriver(getNewInstance(browserName));
        driver = getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://awesomeqa.com/ui/");
    }


    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }

}
