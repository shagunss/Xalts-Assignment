package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

	
	@FindBy(xpath = "//button[contains(text(), 'Get Started')]")
	private WebElement signInButton;

	@FindBy(xpath = "//button[contains(text(), 'sign in')]")
	private WebElement signInLink;

	@FindBy(xpath = "//input[@type = 'text']")
	private WebElement emailField;

	@FindBy(xpath = "//input[@type = 'password']")
	private WebElement passwordField;

	@FindBy(xpath = "//button[normalize-space() = 'Sign In']")
	private WebElement loginButton;

	@FindBy(xpath = "//button[contains(text(), 'Sign Out')]")
	private WebElement loginSuccess;

	@FindBy(xpath = "//div[contains(@class, 'alert')]")
	private WebElement loginFailed;

	
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void clickSignInButton() {
		click(signInButton);
	}

	public void clickSignInLink() {
		click(signInLink);
	}

	public void enterEmail(String email) {
		enterText(emailField, email);
	}

	public void enterPassword(String password) {
		enterText(passwordField, password);
	}

	public void clickLogin() {
		click(loginButton);
	}

	public boolean isLoginSuccess() {
		return loginSuccess.isDisplayed();
	}

	public boolean isLoginFailed() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			System.out.println("Login failed alert: " + alertText);
			alert.accept();
			return true;
		} catch (Exception e) {
			return loginFailed.isDisplayed();
		}
	}
}
