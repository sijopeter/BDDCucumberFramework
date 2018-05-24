package rsweb.pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RsHomePage {
    public RsHomePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }
    @FindBy(how = How.ID, using = "searchTerm")
    public WebElement productSearchBox;
    @FindBy(how = How.ID, using = "btnSearch")
    public WebElement btnSearch;
    @FindBy(how = How.ID, using = "results-table")
    public WebElement resultsTable;
    @FindBy(how = How.XPATH, using = "//*[@id=\"price-break-container\"]/div/div[2]/div/div[3]/div/button")
    public WebElement addToBasket;
    @FindBy(how = How.XPATH, using = "//*[@id=\"price-break-container\"]/div/div[2]/div/div[1]/input")
    public WebElement quantitySpinner;
    @FindBy(how = How.XPATH, using = "//*[@id=\"pageHeader\"]/div/div[3]/a/i")
    public WebElement baketicon;
    @FindBy(how = How.ID, using = "shoppingBasketForm:TopCheckoutPunchoutNavigationWidgetActionNAVTOPDIVId")
    public WebElement secureCheckoutBtn;
    @FindBy(how = How.ID, using = "guestCheckoutForm:GuestWidgetAction_emailAddress_decorate:GuestWidgetAction_emailAddress")
    public WebElement emailField;
    @FindBy(how = How.ID, using = "guestCheckoutForm:guestCheckout")
    public WebElement guestCheckout;
    @FindBy(how = How.ID, using = "alreadyRegisteredForm:continue")
    public WebElement guestCheckoutContinue;
    @FindBy(how = How.ID, using = "acsMainInvite")
    public WebElement popup;
    @FindBy(how = How.XPATH, using = "//*[@id=\"acsMainInvite\"]/a")
    public WebElement popupClose;
    @FindBy(how = How.ID, using = "checkoutSecurelyBtn")
    public WebElement proceedToPay;



//    methods=============================================
    public void waitForPopupAndClose(WebDriver driver,int timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("acsMainInvite")));
            driver.findElement(By.xpath("//*[@id=\"acsMainInvite\"]/a")).click();
//            popupClose.click();
        }
        catch (Exception e){

        }
    }
    public void searchProducts(String productNameQnty){
        productSearchBox.sendKeys(productNameQnty.split(":")[0]);
        btnSearch.click();
    }
    public int getSearchResultCount(){
        int cnt=0;
        if(resultsTable.isDisplayed())
        {
            cnt=resultsTable.findElements(By.tagName("tr")).size();
        }
        return cnt;
    }
    public void setQuantity(String qnty){
        quantitySpinner.sendKeys(Keys.chord(Keys.CONTROL,"a"),qnty);
    }
}
