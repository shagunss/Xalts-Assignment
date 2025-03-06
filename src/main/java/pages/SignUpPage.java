package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage extends BasePage {

    @FindBy(xpath = "//button[contains(text(), 'Get Started')]")
    private WebElement getStartedButton;

    @FindBy(xpath = "//input[@type = 'text']")
    private WebElement emailField;

    @FindBy(xpath = "(//input[@type = 'password'])[1]")
    private WebElement passwordField;

    @FindBy(xpath = "(//input[@type = 'password'])[2]")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[normalize-space() = 'Sign Up']")
    private WebElement signUpButton;
    
    @FindBy(xpath = "//button[contains(text(), 'Sign Out')]")
    private WebElement signOutButton;

    private By signUpFailedAlert = By.xpath("//div[contains(@class, 'alert')]");
    private By signUpSuccess = By.xpath("//button[contains(text(), 'Sign Out')]");

    public SignUpPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickGetStarted() {
        click(getStartedButton);
    }

    public void enterEmail(String email) {
        enterText(emailField, email);
    }

    public void enterPassword(String password) {
        enterText(passwordField, password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        enterText(confirmPasswordField, confirmPassword);
    }

    public void clickSignUp() {
        click(signUpButton);
    }

    public boolean isSignUpSuccess() {
        return driver.findElements(signUpSuccess).size() > 0;
        
    }
    
    public void clickSignOut() {
    	click(signOutButton);
    }

    public boolean isSignUpFailed() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Signup failed alert: " + alertText);
            alert.accept();
            return true;
        } catch (Exception e) {
            return driver.findElements(signUpFailedAlert).size() > 0;
        }
    }
}
