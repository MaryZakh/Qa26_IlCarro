package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {


    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void loginSuccess1() {
        User user = new User().withEmail("margo@gmail.com").withPassword("Mmar123456$");
        logger.info("Test start with test data --->" + " email : 'marga@gmail.com' & password : 'Mmar123456$'");

//
//        user.setEmail("margo@gmail.com");
//        user.setPassword("Mmar123456$");


        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        //Assert --> if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        // app.getHelperUser().clickOkButton();

    }

    @Test
    public void loginSuccess() {
        logger.info("Test start with test data --->" + " email : 'margo@gmail.com' & password : 'Mmar123456$'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("margo@gmail.com", "Mmar123456$");
        app.getHelperUser().submit();
        //Assert --> if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        // app.getHelperUser().clickOkButton();

    }

    @Test
    public void loginSuccessModel() {
        logger.info("Test start with test data --->" + " email : 'margo@gmail.com' & password : 'Mmar123456$'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("margo@gmail.com", "Mmar123456$");
        app.getHelperUser().submit();
        //Assert --> if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickOkButton();

    }

    @Test
    public void loginWrongEmail() {
        logger.info("Test start with test data --->" + " email : 'margogmail.com' & password : 'Mmar123456$'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("margogmail.com", "Mmar123456$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void loginEmptyEmail() {
        logger.info("Test start with test data --->" + " email : ' ' & password : 'Mmar123456$'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(" ", "Mmar123456$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Email is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void loginWrongPassword() {
        logger.info("Test start with test data --->" + " email : ' ' & password : 'Mmar123'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("margo@gmail.com", "Mmar123");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
    }

//    @Test
//    public void loginEmptyPassword() {
//        app.getHelperUser().openLoginForm();
//        app.getHelperUser().fillLoginForm("margo@gmail.com", "");
//        app.getHelperUser().submit();
//        Assert.assertEquals(app.getHelperUser().getErrorText(),"Password is required");
//        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
//    }

    @Test
    public void loginUnregistered() {
        logger.info("Test start with test data --->" + " email: 'luck@gmail.com' & password: 'Lluck123456$'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("luck@gmail.com", "Lluck123456$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");

    }


    @AfterMethod
    public void postCondition() {
        app.getHelperUser().clickOkButton();
    }


}
