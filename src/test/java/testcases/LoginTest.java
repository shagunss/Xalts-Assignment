package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.TestDataStorage;

import java.util.Random;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData")
    public void testLogin(String email, String password, boolean shouldPass) {
        LoginPage loginPage = new LoginPage(driver);

        if (email.equals("random")) {
            email = TestDataStorage.getLastGeneratedEmail();
            if (email == null) {
                System.out.println("No email found. Generating a new test email...");
                email = generateRandomEmail();
                TestDataStorage.setLastGeneratedEmail(email);
            }
        }
        loginPage.clickSignInButton();
        loginPage.clickSignInLink();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        if (shouldPass) {
            Assert.assertTrue(loginPage.isLoginSuccess(), "Login should be successful, but it failed.");
        } else {
            Assert.assertTrue(loginPage.isLoginFailed(), "Login should fail, but it passed.");
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][]{
            {"random", "Password@123", true},  // Successful login with signed-up email
            {"invaliduser@example.com", "Password@123", false},  // Unregistered user
            {"random", "WrongPassword", false},  // Wrong password
            {"", "Password@123", false},  // Empty email
            {"random", "", false},  // Empty password
            {"invalidemail", "Password123", false}  // Invalid email format
        };
    }

    private String generateRandomEmail() {
        Random random = new Random();
        int randomNumber = random.nextInt(100000);
        return "user" + randomNumber + "@example.com";
    }
}
