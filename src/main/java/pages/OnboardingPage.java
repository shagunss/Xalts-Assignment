package pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OnboardingPage extends BasePage {

	// Locators
	@FindBy(xpath = "//button[contains(text(), 'Get Started')]")
	private WebElement getStartedButton;

	@FindBy(xpath = "//h2[contains(text(), 'Onboard')]")
	private WebElement onboardNodesButton;

	@FindBy(xpath = "(//input[@type='text'])[1]")
	private WebElement nodeIdField;

	@FindBy(xpath = "(//input[@type='text'])[2]")
	private WebElement publicIpField;

	@FindBy(xpath = "//button[contains(text(), 'Add Node')]")
	private WebElement addNodeButton;

	@FindBy(xpath = "//button[contains(text(), 'Next')]")
	private WebElement nextOne;

	@FindBy(xpath = "(//input[@type='text'])[1]")
	private WebElement walletAddressField;

	@FindBy(xpath = "//button[contains(text(), 'Add Wallet')]")
	private WebElement addWalletButton;

	@FindBy(xpath = "//button[contains(text(), 'Next')]")
	private WebElement nextTwo;

	@FindBy(xpath = "//button[contains(text(), 'Submit')]")
	private WebElement submitOnboardingButton;

	// Constructor
	public OnboardingPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// get started
	public void getStarted() {
		click(getStartedButton);
	}

	// Actions
	public void clickOnboardNodes() {
		click(onboardNodesButton);
	}

	public void enterNodeDetails(String nodeId, String publicIp) {
		enterText(nodeIdField, nodeId);
		enterText(publicIpField, publicIp);
		click(addNodeButton);
		click(nextOne);
	}

	public void enterWalletDetails(String walletAddress) {
		enterText(walletAddressField, walletAddress);
		click(addWalletButton);
		click(nextOne);
	}

	public void submitOnboarding() {
		click(submitOnboardingButton);
	}

	public boolean isSubmitButtonClickable() {
		try {
			return submitOnboardingButton.isDisplayed() && submitOnboardingButton.isEnabled();
		} catch (NoSuchElementException e) {
			return false; // If the button is not found, return false
		}
	}

}
