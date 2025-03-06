package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SignUpPage;
import pages.LoginPage;
import pages.OnboardingPage;
import utils.TestDataStorage;

import java.util.Random;

public class FullFlowTest extends BaseTest {

    @Test
    public void testSignUpLoginAndOnboardingFlow() {
        SignUpPage signUpPage = new SignUpPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        OnboardingPage onboardingPage = new OnboardingPage(driver);

        // Step 1: Generate a Random Email for Signup
        String email = generateRandomEmail();
        String password = "Password@123";  // Use a strong password

        // Store the email for login test
        TestDataStorage.setLastGeneratedEmail(email);

        // Step 2: Perform SignUp
        System.out.println("Signing up with email: " + email);
        signUpPage.clickGetStarted();
        signUpPage.enterEmail(email);
        signUpPage.enterPassword(password);
        signUpPage.enterConfirmPassword(password);
        signUpPage.clickSignUp();
        
        Assert.assertTrue(signUpPage.isSignUpSuccess(), "Signup failed. Cannot proceed to login.");
        System.out.println("Signup successful!");
        
        signUpPage.clickSignOut();

        // Step 3: Perform Login
        System.out.println("Logging in with the newly created account...");
        loginPage.clickSignInButton();
        loginPage.clickSignInLink();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isLoginSuccess(), "Login failed. Cannot proceed to onboarding.");
        System.out.println("Login successful!");

        // Step 4: Perform Onboarding
        System.out.println("Navigating to Onboarding...");
        onboardingPage.getStarted();
        onboardingPage.clickOnboardNodes();
        onboardingPage.enterNodeDetails("NodeID-12", "192.168.1.1");
        onboardingPage.enterWalletDetails("0x88fa61d2faA13aad8Fbd5B030372B4A159BbbDFb");

        // Check if submit button is clickable before clicking
        if (onboardingPage.isSubmitButtonClickable()) {
            onboardingPage.submitOnboarding();
            System.out.println("Onboarding request submitted successfully.");
            signUpPage.clickSignOut();
            System.out.println("Signing Out...");
            driver.quit(); // Close the browser only if onboarding is successful
        } else {
            System.out.println("Onboarding submission button is not clickable. Keeping the browser open for debugging.");
        }
        
    }
    

    private String generateRandomEmail() {
        Random random = new Random();
        int randomNumber = random.nextInt(100000);
        return "user" + randomNumber + "@example.com";
    }
}
