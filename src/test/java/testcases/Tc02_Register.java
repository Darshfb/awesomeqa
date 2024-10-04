package testcases;
import Pages.P01_HomePage;
import Pages.P02_RegisterPage;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Tc02_Register extends TestBase {

    static String email;
    static String password;
    static String invalidEmail = "mostafamahmoud32323";
    P02_RegisterPage registerPage;
    Faker faker;

    @Test(priority = 1, description = "Register with valid email & password", groups = {"step2"})
    public void registerWithValidEmailAndPassword() {
        P01_HomePage homeLayout = new P01_HomePage(driver);
        homeLayout.enterRegisterPage();
        registerPage = new P02_RegisterPage(driver);
        faker = new Faker();
        registerPage.register(faker.name().firstName(), faker.name().lastName(), email = faker.internet().emailAddress(), faker.phoneNumber().phoneNumber(), password = faker.internet().password(), false, true);
        Assert.assertTrue(registerPage.getMyAccountText());
    }

    @Test(priority = 1, description = "Register with valid email & invalid password")
    public void registerWithValidEmailAndInvalidPassword() {
        registerPage = new P02_RegisterPage(driver);
        faker = new Faker();
        registerPage.register(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), faker.phoneNumber().phoneNumber(), "123", true, true);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(registerPage.checkPasswordErrorMessage("password"));
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Register with inValid email & valid password")
    public void registerWithInvalidEmailAndPassword() {
        registerPage.register(faker.name().firstName(), faker.name().lastName(), invalidEmail, faker.phoneNumber().phoneNumber(), faker.internet().password(), true, true);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(registerPage.checkPasswordErrorMessage("email"));
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Register with inValid email & password")
    public void registerWithInvalidEmailAndInvalidPassword() {
        registerPage.register(faker.name().firstName(), faker.name().lastName(), invalidEmail, faker.phoneNumber().phoneNumber(), "123", true, true);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(registerPage.checkPasswordErrorMessage("email"));
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "Register with valid data without checking on policy agreement")
    public void registerWithValidDataWithoutPolicyAgreement() {
        registerPage.register(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), faker.phoneNumber().phoneNumber(), faker.internet().password(), true, false);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(registerPage.checkPasswordErrorMessage("policy"));
        softAssert.assertAll();
    }
}
