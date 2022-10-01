

import models.Contact;
import models.User;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewContactTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("vova11@gmail.com").withPassword("Vova123$"));
        }
    }


    @Test
    public void addNewContact(){

        Contact contact = Contact.builder()
                .name("Vera")
                .lastName("Brezhneva")
                .phone("0526143000")
                .email("verkab@gmail.com")
                .Address("Barak 6,Tel Aviv")
                .description("Mother")
                .build();

        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().save();

    }
    @AfterMethod
    public void postCondition() {
        app.getHelperUser().clickSignOut();
    }

}