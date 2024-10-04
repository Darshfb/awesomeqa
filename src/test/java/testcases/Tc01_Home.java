package testcases;

import Pages.P01_HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Tc01_Home extends TestBase {
    P01_HomePage homeLayout;

    @Test(priority = 1, groups = {"step1"})
    public void validateEnteringRegisterPage() {
        homeLayout = new P01_HomePage(driver);
        homeLayout.enterRegisterPage();
    }

    @Test(priority = 1, groups = {"step1"})
    public void validateEnteringLoginPage() {
        homeLayout = new P01_HomePage(driver);
        homeLayout.enterLoginPage();
    }

    @Test(priority = 3)
    public void validateLogout() throws InterruptedException {
        Thread.sleep(2000);

        homeLayout.logout();
    }


    @Test(priority = 1, description = "Searching for items", dependsOnGroups = {"step2"})
    public void searchingForItems()
    {
        String searchText = "MacBook";
        homeLayout = new P01_HomePage(driver);
        homeLayout.search(searchText);
        Assert.assertTrue(homeLayout.searchResult(searchText));
    }

    @Test(priority = 1, description = "Change currency to Euro", dependsOnGroups = {"step2"})
    void changeCurrencyToEuro() throws InterruptedException {
        homeLayout = new P01_HomePage(driver);
        homeLayout.openCurrencyDropMenu(false);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homeLayout.getEuroText());
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Change currency Randomly", dependsOnGroups = {"step2"})
    public void changeCurrencyRandomly()
    {
        homeLayout = new P01_HomePage(driver);
        homeLayout.openCurrencyDropMenu(true);
    }

    @Test(priority = 1, dependsOnGroups = {"step2"})
    public void selectCategories()
    {
        homeLayout.openCategoryDropMenu();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homeLayout.isSelectedCategoryTrue());
        softAssert.assertAll();
    }

    @Test(priority = 1, description = "select From First Three Categories", dependsOnGroups = {"step2"})
    public void selectFromFirstThreeCategories()throws InterruptedException
    {
        homeLayout.selectFromFirstThreeCategories();
    }

    @Test(priority = 1, dependsOnGroups = {"step2"})
    public void addItemToCart() throws InterruptedException {
        homeLayout.addItemTOCartDirectly();
//        homeLayout.addItemToCartFromHome();
    }
    @Test(priority = 1, dependsOnGroups = {"step2"})
    public void addToCartFromItemDetails() {
        homeLayout.addItemTOCartFromItemDetails();
    }

    @Test(priority = 4, dependsOnGroups = {"step2"})
    public void addToWishList() throws InterruptedException {
        homeLayout.addItemToWishListFromHome();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homeLayout.isWishListHasTwoItems());
        softAssert.assertAll();
    }

    @Test(priority = 4, dependsOnGroups = {"step2"})
    public void addToCompareList() throws InterruptedException
    {
        homeLayout.addItemToCompareListFromHome();
//        homeLayout.checkThatItemsAreAddedToCompareList();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homeLayout.checkThatItemsAreAddedToCompareList());
        softAssert.assertAll();

    }

    @Test(priority = 4)
    public void goToCart() {
        homeLayout.navigateToCart();
    }


}
