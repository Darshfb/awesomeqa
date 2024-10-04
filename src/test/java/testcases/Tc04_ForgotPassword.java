package testcases;
import Pages.P04_ForgotPasswordPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tc04_ForgotPassword extends TestBase {

    P04_ForgotPasswordPage forgotPasswordPage;

    @Test(priority = 1, description = "Forgot password with valid email")
    public void forgotPasswordWithValidData()
    {
        forgotPasswordPage = new P04_ForgotPasswordPage(driver);
        forgotPasswordPage.forgotPassword("mostafamahmoudaboads@gmail.com");
        Assert.assertTrue(forgotPasswordPage.getForgotPasswordSuccessText(true));
    }

    @Test(priority = 1, description = "Forgot password with invalid email")
    public void forgotPasswordWithInvalidData()
    {
        forgotPasswordPage = new P04_ForgotPasswordPage(driver);
        forgotPasswordPage.forgotPassword("mostafamahmoudads32323@mail.com");
        Assert.assertTrue(forgotPasswordPage.getForgotPasswordSuccessText(false));
    }


}
