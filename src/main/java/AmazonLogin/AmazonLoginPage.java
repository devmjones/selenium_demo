package AmazonLogin;

import HelperClasses.CommonUsefulMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by devon.jones on 1/3/17.
 */
public class AmazonLoginPage {
    AmazonLoginPage amazonLoginPage;
    public WebDriver driver;

    @FindBy(css = AmazonLoginUIMap.emailField)
    private WebElement emailField;
    @FindBy(css = AmazonLoginUIMap.passwordField)
    private WebElement passwordField;
    @FindBy(css = AmazonLoginUIMap.signInButton)
    private WebElement signInButton;

    // Errors
    @FindBy(css = AmazonLoginUIMap.emailMissingAlert)
    private WebElement emailMissingAlert;
    @FindBy(css = AmazonLoginUIMap.passwordMissingAlert)
    private WebElement passwordMissingAlert;
    @FindBy(css = AmazonLoginUIMap.errorMsgBox)
    private WebElement errorMsgBox;
    @FindBy(css = AmazonLoginUIMap.errorMsgBoxMsg)
    private WebElement errorMsgBoxMsg;

    public void waitForAmazonToLoad(){
        CommonUsefulMethods.waitUntilElementVisable(driver, emailField);
    }

    public void waitForSignInPageToLoad(){
        CommonUsefulMethods.waitUntilElementVisable(driver, emailField);
    }

    public boolean isSignInPageLoaded(){
        return emailField.isDisplayed() && passwordField.isDisplayed();
    }

    public void enterEmail(String email){
        emailField.sendKeys(email);
    }

    public void enterPassword(String password){
        passwordField.sendKeys(password);
    }

    public void clickSignInButton(){
        signInButton.click();
    }

    public boolean isEmailMissingAlertVisible(){
        return emailMissingAlert.isDisplayed();
    }

    public boolean isPasswordMissingAlertVisible(){
        return passwordMissingAlert.isDisplayed();
    }

    public boolean isErrorBoxVisible(){
        return errorMsgBox.isDisplayed();
    }

    public boolean doesErrorMsgMatchExpected(String expectedMsg){
        return errorMsgBoxMsg.getText().equalsIgnoreCase(expectedMsg);
    }
}
