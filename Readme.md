**Automated Testing Framework**

This project automates **Sign-Up, Login, and Onboarding** using **Selenium WebDriver, Java, and TestNG**.

**How to Run Tests**

- **Run Sign-Up Test:**  mvn test -Dtest = testcases.SignUpTest
- **Run Login Test:** mvn test -Dtest = testcases.LoginTest
- **Run Onboarding Test:** mvn test -Dtest = testcases.OnboardingTest
- **Run Full Flow (SignUp → Login → Onboarding):** mvn test -Dtest = testcases.FullFlowTest

**Project Workflow**

1. **Sign-Up** → Registers a new user
1. **Login** → Logs in with the registered user
1. **Onboarding** → Submits node & wallet info.
1. **Full Flow** → Runs all steps in one session

**Troubleshooting**

- Run SignUpTest first to store email for login.
- Run LoginTest after Signup
- Run FullFlowTest file if you want to see the whole flow working fine
- Update ChromeDriver if browser crashes.(optional)
- Comment driver.quit(); to keep the browser open. (optional)

