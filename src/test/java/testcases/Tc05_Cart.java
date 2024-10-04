package testcases;

import Pages.P05_CartPage;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tc05_Cart extends TestBase
{
    P05_CartPage cartPage;
    Faker faker;
    @Test(priority = 6)
    public void checkout(){
        cartPage = new P05_CartPage(driver);
         faker = new Faker();
        cartPage.clickCheckoutList(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.company().name(),
                faker.address().fullAddress(),
                faker.address().secondaryAddress(),
                faker.address().city(),
                "32957",
                "Hello world"
        );
//        Assert.assertEquals("Warning: Payment method required!", cartPage.getPaymentErrorMessage());
    }
}
