package manager;

import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperContact extends HelperBase{


    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm() {

        click(By.cssSelector("[href='/add']"));



    }

    public void fillContactForm(Contact contact) {
        type(By.xpath("//input[@placeholder='Name']"),contact.getName());
        type(By.xpath("//input[@placeholder='Last Name']"),contact.getLastName());
        type(By.xpath("//input[@placeholder='Phone']"),contact.getPhone());
        type(By.xpath("//input[@placeholder='email']"),contact.getEmail());
        type(By.xpath("//input[@placeholder='Address']"),contact.getAddress());
        type(By.xpath("//input[@placeholder='description']"),contact.getDescription());

    }

    public void save() {
        WebElement saveButton = wd.findElement(By.xpath("//div[@class='add_form__2rsm2']//button"));
        saveButton.click();
    }


}
