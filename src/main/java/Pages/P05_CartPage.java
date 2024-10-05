package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class P05_CartPage {
    WebDriver customDriver;
    public P05_CartPage(WebDriver driver)
    {
        customDriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//a)[text()='Checkout']")
    public WebElement checkoutButton;

    @FindBy(id = "input-payment-firstname")
    WebElement firstName;

    @FindBy(id = "input-payment-lastname")
    WebElement lastName;

    @FindBy(id = "input-payment-company")
    WebElement company;

    @FindBy(id = "input-payment-address-1")
    WebElement firstAddress;

    @FindBy(id = "input-payment-address-2")
    WebElement secondAddress;

    @FindBy(id = "input-payment-city")
    WebElement city;

    @FindBy(id = "input-payment-postcode")
    WebElement postcode;

    @FindBy(id = "input-payment-country")
    WebElement country;

    @FindBy(id = "input-payment-zone")
    WebElement zone;

    @FindBy(id = "button-payment-address")
    WebElement continueButton;

    @FindBy(id = "button-shipping-address")
    WebElement continueButton2;

    @FindBy(name = "comment")
    WebElement comment;

    @FindBy(name = "agree")
    WebElement agree;

    @FindBy(xpath = "(//input)[@id='button-shipping-method']")
    WebElement continueToPaymentMethod;

    @FindBy(id = "button-payment-method")
    WebElement continueToPaymentMethod2;

    @FindBy(id = "button-confirm")
    WebElement confirmButton;

    @FindBy(xpath = "(//h1)")
    WebElement successMessage;

    @FindBy(xpath = "(//a)[text()='Continue']")
    WebElement continueToFinishOrderButton;

    public void clickCheckoutList(String fName, String lName, String company, String fAddress, String sAddress, String city, String postCode, String comment) {
        WebDriverWait wait = new WebDriverWait(customDriver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.urlContains("https://awesomeqa.com/ui/index.php?route=checkout/checkout"));
        } catch (TimeoutException e) {
            System.err.println("URL did not change as expected within the timeout.");
        }
        checkoutButton.click();
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        this.company.sendKeys(company);
        this.firstAddress.sendKeys(fAddress);
        this.secondAddress.sendKeys(sAddress);
        this.city.sendKeys(city);
        this.postcode.sendKeys(postCode);
        Select select = new Select(country);
        select.selectByValue("63");
        Select select2 = new Select(zone);
        select2.selectByValue("1009");
        continueButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-shipping-address")));
        continueButton2.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("comment")));
        this.comment.sendKeys(comment);
        continueToPaymentMethod.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("agree")));
        agree.click();
        continueToPaymentMethod2.click();
        confirmButton.click();
        System.out.println("The order if success=>  "+  getSuccessMessage());
        continueToFinishOrderButton.click();
    }

    public String getSuccessMessage(){
        return successMessage.getText();
    }

    @FindBy(xpath = "(//table)[3]/tbody/tr[1]/td[4]/div/span/button[2]")
    WebElement firstItemInCart;

    @FindBy(xpath = "(//table)[3]/tbody/tr")
    List<WebElement> cartItems;

    public void removeItemsFromCart() throws InterruptedException {
        firstItemInCart.click();
        Thread.sleep(1000);
        firstItemInCart.click();
    }

    public Boolean checkCartItemCounts(){
        return cartItems.size() == 2;
    }

    @FindBy(xpath = "(//p)[text()='Your shopping cart is empty!'][2]")
    WebElement emptyCart;

    public Boolean checkCartIsEmpty(){
        return emptyCart.getText().contains("Your shopping cart is empty!");
    }
}
