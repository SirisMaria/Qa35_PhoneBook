

import manager.DataProviderContact;
import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;



public class AddNewContactTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("vova11@gmail.com").withPassword("Vova123$"));
        }
    }


    @Test (invocationCount = 3)
    public void addNewContact(){

        Random random = new Random();
        int i = random.nextInt(1000)+1000;

        Contact contact = Contact.builder()
                .name("Vera"+i)
                .lastName("Brezhneva")
                .phone("0526143000"+i)
                .email("verkab"+i+"@gmail.com")
                .Address("Barak 6,Tel Aviv")
                .description("Mother").build();
        logger.info("Test start with data --->" +contact.toString());

        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().save();
        Assert.assertTrue(app.helperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.helperContact().isContactAddedByPhone(contact.getPhone()));



    }

    @Test
    public void addNewContactSuccessRequiredFields(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;

        Contact contact = Contact.builder()
                .name("Vesta"+i)
                .lastName("Brezhneva")
                .phone("0526143000"+i)
                .email("verkab"+i+"@gmail.com")
                .Address("Barak 6,Tel Aviv")
                .description("Mother").build();


        app.helperContact().openContactForm();
        app.helperContact().fillContactRequiredForm(contact);
        app.helperContact().save();
        Assert.assertTrue(app.helperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.helperContact().isContactAddedByPhone(contact.getPhone()));

    }
    @Test(dataProvider = "contactValidData",dataProviderClass = DataProviderContact.class)
    public void addNewContactSuccessRequiredFieldsDP(Contact contact){

        logger.info("Test start with data --->" + contact.toString());

        app.helperContact().openContactForm();
        app.helperContact().fillContactRequiredForm(contact);
        app.helperContact().save();
        Assert.assertTrue(app.helperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.helperContact().isContactAddedByPhone(contact.getPhone()));

    }
    public void addNewContactWrongName(){


        Contact contact = Contact.builder()
                .lastName("Brezhneva")
                .phone("052614303000")
                .email("verkab@gmail.com")
                .Address("Barak 6,Tel Aviv")
                .build();


        app.helperContact().openContactForm();
        app.helperContact().fillContactRequiredForm(contact);
        app.helperContact().save();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());


    }

    @AfterMethod
    public void postCondition() {
        app.getHelperUser().clickSignOut();
    }

}