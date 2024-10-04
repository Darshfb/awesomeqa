package Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class P04_ForgotPasswordPage
{
    public P04_ForgotPasswordPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "input-email")
    WebElement emailAddress;

    @FindBy(xpath = "(//input)[@type='submit']")
    WebElement submitButton;

    @FindBy(xpath = "(//div)[25]")
    WebElement forgotPasswordSuccessText;

    public void forgotPassword(String email)
    {
        emailAddress.clear();
        emailAddress.sendKeys(email);
        submitButton.click();
    }

    public Boolean getForgotPasswordSuccessText(Boolean validEmail)
    {
        if (validEmail)
            return forgotPasswordSuccessText.getText().equals("An email with a confirmation link has been sent your email address.");
        else
            return forgotPasswordSuccessText.getText().equals("Warning: The E-Mail Address was not found in our records, please try again!");

    }

}
