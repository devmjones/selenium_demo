import AmazonLogin.AmazonLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by devon.jones on 1/3/17.
 */
public class AmazonLoginTest {

    WebDriver driver ;
    AmazonLoginPage amazonLoginPage;
    private static final String navigationURL = "https://www.amazon.com/ap/signin?_encoding=UTF8&openid.assoc_handle=usflex&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fgp%2Fyourstore%2Fcard%3Fie%3DUTF8%26ref_%3Dcust_rec_intestitial_signin";

    @BeforeMethod
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        amazonLoginPage = new AmazonLoginPage();
        PageFactory.initElements(driver, amazonLoginPage);
        amazonLoginPage.driver = driver;
        driver.get(navigationURL);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        this.driver.quit();
        amazonLoginPage = null;
    }

    @Test
    public void testCanGetToSignIn(){
        amazonLoginPage.waitForAmazonToLoad();
        Assert.assertTrue(amazonLoginPage.isSignInPageLoaded(), "Sign In Page is not loading");
    }

    @Test
    public void testErrorOnEmptyLogin(){
        amazonLoginPage.waitForAmazonToLoad();
        amazonLoginPage.enterEmail("");
        amazonLoginPage.enterPassword("");
        amazonLoginPage.clickSignInButton();
        Assert.assertTrue(amazonLoginPage.isEmailMissingAlertVisible(), "Should be an error message when email field left blank");
        Assert.assertTrue(amazonLoginPage.isPasswordMissingAlertVisible(), "Should be an error message when password field left blank");
    }

    @Test
    public void testWrongPasswordError() throws InterruptedException {
        amazonLoginPage.waitForAmazonToLoad();
        amazonLoginPage.enterEmail("devmjones@gmail.com");
        amazonLoginPage.enterPassword("wrongPassword");
        amazonLoginPage.clickSignInButton();
        Thread.sleep(2000);
        Assert.assertTrue(amazonLoginPage.isErrorBoxVisible(), "Error Box should be displayed");
        Assert.assertTrue(amazonLoginPage.doesErrorMsgMatchExpected("Your password is incorrect"));
    }

}