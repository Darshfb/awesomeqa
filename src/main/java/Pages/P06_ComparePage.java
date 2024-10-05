package Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class P06_ComparePage
{
    public P06_ComparePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//table)[@class='table table-bordered']/tbody[5]/tr/td[2]/a")
    WebElement firstCompareElement;

    @FindBy(xpath = "(//table)[@class='table table-bordered']/tbody[3]/tr/td/a")
    WebElement secondCompareElement;

    public void removeCompareItemFromCompareListPage()
    {
        firstCompareElement.click();
        secondCompareElement.click();
    }

}
