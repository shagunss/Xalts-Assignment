package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.SignUpPage;
import utils.TestDataStorage;

import java.util.Random;

public class SignUpTest extends BaseTest {

    @Test(dataProvider = "signupData")
    public void testSignUp(String email, String password, String confirmPassword, boolean shouldPass) {
        SignUpPage signUpPage = new SignUpPage(driver);

        // Generate a random email and store it for login test
        if (email.equals("random")) {
            email = generateRandomEmail();
            TestDataStorage.setLastGeneratedEmail(email);  // Store email for later use
        }

        signUpPage.clickGetStarted();
        signUpPage.enterEmail(email);
        signUpPage.enterPassword(password);
        signUpPage.enterConfirmPassword(confirmPassword);
        signUpPage.clickSignUp();

        if (shouldPass) {
            Assert.assertTrue(signUpPage.isSignUpSuccess(), "Signup should be successful, but it failed.");
        } else {
            Assert.assertTrue(signUpPage.isSignUpFailed(), "Signup should fail, but it passed.");
        }
    }

    @DataProvider(name = "signupData")
    public Object[][] getSignupData() {
        return new Object[][] {
            {"random", "Password@123", "Password@123", true},
            {"invalidemail", "Password123", "Password123", false},
            {"user@example.com", "pass", "pass", false},
            {"user@example.com", "Password123", "WrongPassword", false}
        };
    }

    private String generateRandomEmail() {
        Random random = new Random();
        int randomNumber = random.nextInt(100000);
        return "user" + randomNumber + "@example.com";
    }
}
