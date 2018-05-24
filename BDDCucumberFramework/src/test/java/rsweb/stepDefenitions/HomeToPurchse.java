package rsweb.stepDefenitions;
import org.openqa.selenium.By;
import rsweb.pageObjects.PropertyReader;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rsweb.pageObjects.RsHomePage;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.*;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class HomeToPurchse {
    WebDriver driver;
    PropertyReader configFileData=new PropertyReader();

    @Given("^user naviates to the base url$")
    public void user_naviates_to_the_base_url() throws Throwable {
        System.setProperty("webdriver.chrome.driver",configFileData.getChromeDriver());
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(configFileData.getImplicitWait(),TimeUnit.SECONDS);
        driver.get(configFileData.getUrl());

    }

    @When("^appication launches the rs web home page and display product serch field$")
    public void appication_launches_the_rs_web_home_page_and_display_product_serch_field() throws Throwable {
        RsHomePage home=new RsHomePage(driver);
        home.productSearchBox.isDisplayed();
        assertEquals(true,home.productSearchBox.isDisplayed());

    }
    @Then("^user search for any products application should filter the products and display on the page$")
    public void user_search_for_any_products_application_should_filter_the_products_and_display_on_the_page() throws Throwable {
        RsHomePage home=new RsHomePage(driver);
        home.productSearchBox.sendKeys(configFileData.getsearchFilterkey());
        home.btnSearch.click();
        assertTrue("No product found",home.getSearchResultCount()!=0);
        List<WebElement> tableRows = home.resultsTable.findElements(By.tagName("tr"));
//        verify that the every search result contains the search key
        for(WebElement tdElement : tableRows) {
            assertTrue("Search key not found in the filtered result",tdElement.findElements(By.tagName("td")).get(1).getText().contains(configFileData.getsearchFilterkey()));
        }

    }

    @Then("^user searches for a list of products and add to the cart$")
    public void user_searches_for_a_list_of_products_and_add_to_the_cart() throws Throwable {
        RsHomePage home=new RsHomePage(driver);
        for(String product:configFileData.getProductsToOrder().split(",")){
            home.waitForPopupAndClose(driver,2);//
            home.searchProducts(product);//
            home.waitForPopupAndClose(driver,2);//
            assertEquals("Product Not found",true,home.addToBasket.isDisplayed());
            home.setQuantity(product.split(":")[1]);
            home.waitForPopupAndClose(driver,2);
            home.addToBasket.click();

        }

    }

    @Then("^user checkout the cart by providing the email address$")
    public void user_checkout_the_cart_by_providing_the_email_address() throws Throwable {
        RsHomePage home=new RsHomePage(driver);

        home.waitForPopupAndClose(driver,2);
        home.baketicon.click();
        home.waitForPopupAndClose(driver,2);

//
        assertEquals("checkout button not found",true,home.secureCheckoutBtn.isDisplayed());
//

        home.secureCheckoutBtn.click();

        home.waitForPopupAndClose(driver,2);
//
        home.emailField.sendKeys(configFileData.getUserEmil());

        home.waitForPopupAndClose(driver,1);
        home.guestCheckout.click();
        try{
            home.guestCheckoutContinue.click();
        }
        catch(Exception e) {
        }
        assertEquals("Payment page is not loaded",true,home.proceedToPay.isDisplayed());
        }

    @Then("^close the application$")
    public void close_the_application() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(5000);
        driver.close();
    }
}
