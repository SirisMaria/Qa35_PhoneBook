import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class LoginTests extends TestBase {


    @BeforeMethod
    public void precondition() {
        logger.info("Start checking autorization");
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Test was needed in logout");
        }else {
            logger.info("Test wasnt needed in logout");
        }
    }
        @Test (dataProvider = "loginDataModel",dataProviderClass = DataProviderUser.class)
        public void loginSuccessModelDP(String email,String password) {
//        User user = new User();
//        user.setEmail("");
//        user.setPassword("");
            logger.info("Login scenario success was used data email: " +email+" & password: " +password);
            User user = new User().withEmail("mashka@gmail.com").withPassword("mashkA12345$5");

            logger.info("Login scenario success was used data"+user.toString());
            app.getHelperUser().openLoginRegistrationForm();
            app.getHelperUser().fillLoginRegistrationForm(user);
            app.getHelperUser().submitLogin();
            Assert.assertTrue(app.getHelperUser().isLogged());
            logger.info(" Assert check is Sign Out button Present");
        }


    @Test (dataProvider = "loginData", dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email,String password) {

//        String email = "mashka@gmail.com";
//        String password = "mashkA12345$5";

        logger.info("User login with data: email " +email+" & password: " +password);
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("mashka@gmail.com","mashkA12345$5");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info(" Assert check is Sign Out button Present");

    }


      @Test
    public void loginNegativeWrongEmailFormat() {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(new User().withEmail("mashkagmail.com").withPassword("mashkA12345$5"));
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isAlertPresent());
        Assert.assertTrue(app.getHelperUser().isErrorWrongFormat());

    }

    @Test(description = "info", enabled = false)
    public void loginNegativeWrongPasswordFormat() {


    }


}