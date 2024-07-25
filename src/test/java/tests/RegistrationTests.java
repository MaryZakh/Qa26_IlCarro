package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess() {
        int i = new Random().nextInt(1000) + 1000;
        System.out.println(i);
        System.out.println(System.currentTimeMillis());
        int z = (int) ((System.currentTimeMillis() / 1000) % 3600);
        System.out.println(z);

        User user = new User()
                .setName("Lisa")
                .setLastName("Snow")
                .withEmail("snow" + z + "@gmail.com")
                .withPassword("Snow123456$");

        logger.info("Test start with test data --->" + user.toString());

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");
    }


    @Test
    public void registrationEmptyName() {
        User user = new User()
                .setName("")
                .setLastName("Snow")
                .withEmail("snow@gmail.com")
                .withPassword("Snow123456$");
        logger.info("Test start with test data --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Name is required");
    }

    @Test
    public void registrationEmptyLastName() {
        User user = new User()
                .setName("Lisa")
                .setLastName("")
                .withEmail("snow@gmail.com")
                .withPassword("Snow123456$");
        logger.info("Test start with test data --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Last name is required");


    }

    @Test
    public void registrationWrongEmail() {
        User user = new User()
                .setName("Lisa")
                .setLastName("Snow")
                .withEmail("snowgmail.com")
                .withPassword("Snow123456$");
        logger.info("Test start with test data --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertTrue(app.getHelperUser().getErrorText().contains("Wrong email format"));


    }

    @Test
    public void registrationEmptyEmail() {
        User user = new User()
                .setName("Lisa")
                .setLastName("Snow")
                .withEmail("")
                .withPassword("Snow123456$");
        logger.info("Test start with test data --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Email is required");


    }

    @Test
    public void registrationWrongPassword() {
        User user = new User()
                .setName("Lisa")
                .setLastName("Snow")
                .withEmail("snow@gmail.com")
                .withPassword("Snow123");
        logger.info("Test start with test data --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Password must contain minimum 8 symbols\n" +
                "Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");


    }

    @Test
    public void registrationEmptyPassword() {
        User user = new User()
                .setName("Lisa")
                .setLastName("Snow")
                .withEmail("snow@gmail.com")
                .withPassword("");
        logger.info("Test start with test data --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Password is required");


    }

    @Test
    public void registrationWithoutCheckbox() {
        User user = new User()
                .setName("Lisa")
                .setLastName("Snow")
                .withEmail("snow@gmail.com")
                .withPassword("Snow123456$");
        logger.info("Test start with test data --->" + user.toString());

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

    }


    @AfterMethod
    public void postCondition() {
        app.getHelperUser().clickOkButton();
    }


}
