package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.OnboardingPage;
import utils.TestDataStorage;

public class OnboardingTest extends BaseTest {

    @Test(dataProvider = "onboardingData")
    public void testOnboarding(String nodeId, String publicIp, String walletAddress, boolean shouldPass) {
        LoginPage loginPage = new LoginPage(driver);
        OnboardingPage onboardingPage = new OnboardingPage(driver);

        // Retrieve email from SignUpTest
        String email = TestDataStorage.getLastGeneratedEmail();
        String password = "Password@123"; // Ensure this matches the one used in SignUpTest

        Assert.assertNotNull(email, "No email found. Run SignUpTest first to create an account.");

        // Step 1: Perform Login
        loginPage.clickSignInButton();
        loginPage.clickSignInLink();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isLoginSuccess(), "Login failed. Cannot proceed with onboarding.");

        // Step 2: Perform Onboarding
        onboardingPage.getStarted();
        onboardingPage.clickOnboardNodes();
        onboardingPage.enterNodeDetails(nodeId, publicIp);
        onboardingPage.enterWalletDetails(walletAddress);
        
        // Check if the submit button is clickable before clicking
        if (onboardingPage.isSubmitButtonClickable()) {
            onboardingPage.submitOnboarding();
            System.out.println("Onboarding submitted successfully. Closing the browser.");
            driver.quit();  // Close the browser if submission is successful
        } else {
            System.out.println("Onboarding submission button is not clickable. Keeping the browser open for debugging.");
        }

       
    }
    @DataProvider(name = "onboardingData")
    public Object[][] getOnboardingData() {
        return new Object[][]{
            {"NodeID-12", "192.168.1.1", "0x88fa61d2faA13aad8Fbd5B030372B4A159BbbDFb", true},
            {"invalidNode!", "192.168.1.1", "0xValidWallet123", false},
            {"validNode123", "999.999.999.999", "0xValidWallet123", false},
            {"validNode123", "192.168.1.1", "InvalidWallet!", false}
        };
    }
}
