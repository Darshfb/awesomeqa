package testcases;

import Pages.P01_HomePage;
import Pages.P05_CartPage;
import Pages.P06_ComparePage;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Tc06_CompareList extends TestBase
{
    P06_ComparePage comparePage;
    P01_HomePage homePage;

    @Test(description = "RemoveCompare list items", dependsOnGroups = {"step3"})
    public void removeItemsFromCompareList(){
        homePage = new P01_HomePage(driver);
        comparePage = new P06_ComparePage(driver);
        comparePage.removeCompareItemFromCompareListPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.checkThatItemsAreAddedToCompareList(0));
        softAssert.assertAll();
    }
}
