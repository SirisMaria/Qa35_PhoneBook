import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContact extends TestBase{

    @BeforeMethod
    public void precondition(){
        if (!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("noa@gmail.com").withPassword("Nnoa12345$"));
        }

        app.helperContact().providerOfContacts();


    }
    @Test
    public void removeFirstContact(){


        Assert.assertEquals(app.helperContact().removeOneContact(),1);

    }
    @Test
    public void removeAllContacts(){

        app.helperContact().removeAllContacts();
        Assert.assertTrue(app.helperContact().isNoContactHere());

    }
}