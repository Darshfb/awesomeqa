package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static util.Utility.selectRandomElement;

public class P02_RegisterPage {
    WebDriver customDriver;

    public P02_RegisterPage(WebDriver driver) {
        customDriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "input-firstname")
    WebElement firstName;

    @FindBy(id = "input-lastname")
    WebElement lastName;

    @FindBy(id = "input-email")
    WebElement email;

    @FindBy(id = "input-telephone")
    WebElement phone;

    @FindBy(id = "input-password")
    WebElement password;

    @FindBy(id = "input-confirm")
    WebElement confirmPassword;

    @FindBy(xpath = "//*[@id='content']/form/fieldset[3]/div/div/label")
    List<WebElement> checkBoxes;

    @FindBy(name = "agree")
    WebElement agreement;

    @FindBy(xpath = "(//input)[@type='submit']")
    WebElement loginButton;

    @FindBy(xpath = "(//div)[@class='text-danger']")
    WebElement passwordValidation;

    @FindBy(xpath = "(//h1)[text()='Register Account']")
    WebElement stillInRegisterAccount;

    @FindBy(xpath = "(//div)[@class='alert alert-danger alert-dismissible']")
    WebElement policyMessage;


    By continueButton = By.xpath("//*[@id='common-success']/div/div/div/div/a");

    @FindBy(xpath = "(//a)[text()='My Account'][2]")
    WebElement myAccountTextButton;

    public void register(String firstName, String lastName, String email, String phone, String password, Boolean invalidScenario, Boolean isAgree) {
        this.firstName.clear();
        this.lastName.clear();
        this.email.clear();
        this.phone.clear();
        this.password.clear();
        if (this.agreement.isSelected())
            this.agreement.click();
        this.confirmPassword.clear();
        selectRandomElement(checkBoxes).click();
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.email.sendKeys(email);
        this.phone.sendKeys(phone);
        this.password.sendKeys(password);
        confirmPassword.sendKeys(password);
        if (isAgree)
            agreement.click();
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(customDriver, Duration.ofSeconds(10));
        if (!invalidScenario)
            wait.until(ExpectedConditions.visibilityOfElementLocated(continueButton)).click();
    }

    public Boolean checkPasswordErrorMessage(String text) {
        switch (text) {
            case ("password"):
                return passwordValidation.getText().contains("Password must be between 4 and 20 characters!");
            case "email":
                return stillInRegisterAccount.getText().contains("Register Account");
            case "policy":
                return policyMessage.getText().contains("Warning: You must agree to the Privacy Policy!");
            default:
                return false;
        }
    }

    public Boolean getMyAccountText()
    {
        return myAccountTextButton.getText().equals("My Account");
    }


}
