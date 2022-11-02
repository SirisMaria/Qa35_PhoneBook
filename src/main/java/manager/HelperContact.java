package manager;

import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class HelperContact extends HelperBase{


    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm() {

        click(By.cssSelector("a[href='/add']"));
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
        WebElement element = wd.findElement(By.cssSelector(".add_form__2rsm2 button"));
        element.sendKeys(Keys.TAB);
        pause(500);
        element.sendKeys(Keys.ENTER);
    }
    public boolean isContactAddedByName(String name) {
        List<WebElement> list = wd.findElements(By.cssSelector("h2"));

        for (WebElement el : list) {
            if (el.getText().equals(name))
                return true;
        }
        return false;
    }
    public boolean isContactAddedByPhone(String phone) {
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));

        for (WebElement el : list) {
            if (el.getText().equals(phone))
                return true;
        }
        return false;
    }
    public void fillContactRequiredForm(Contact contact) {
        type(By.cssSelector("[placeholder='Name']"), contact.getName());
        type(By.cssSelector("[placeholder='Last Name']"), contact.getLastName());
        type(By.cssSelector("[placeholder='Phone']"), contact.getPhone());
        type(By.cssSelector("[placeholder='email']"), contact.getEmail());
        type(By.cssSelector("[placeholder='Address']"), contact.getAddress());
    }
    public boolean isAddPageStillDisplayed() {
        return  wd.findElements(By.cssSelector("a.active[href='/add']")).size()>0;
    }
    public int removeOneContact() {
        int before =countOfContact();

        if(!isCountListEmpty()) {
            removeContact();
        }

        int after =countOfContact();
        return before-after;
    }

    private boolean isCountListEmpty() {
        return wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).isEmpty();
    }

    private void removeContact() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//button[text()='Remove']"));
        pause(500);
    }

    private int countOfContact() {
        return wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
    }

    public void removeAllContacts() {
//        List <WebElement> list = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
//        for (int i = 0; i < list.size(); i++) {
//            click(By.cssSelector(".contact-item_card__2SOIM"));
//            click(By.xpath("//button[text()='Remove']"));
//            pause(500);
//        }

        while (wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size()!=0){
            removeContact();
        }



    }

    public boolean isNoContactHere() {
        return new WebDriverWait(wd, Duration.ofSeconds(5))
                .until(ExpectedConditions
                        .textToBePresentInElement(wd.findElement(By.cssSelector(".contact-page_message__2qafk h1")),"No Contacts here!" ));
    }

    public void providerOfContacts() {

        Random random = new Random();

        // check count of contacts <3 ---> add contact 3
        if(countOfContact()<4){
            //add contact 3
            for (int i = 0; i < 3; i++) {

                int index = random.nextInt(100)+100;
                Contact contact = Contact.builder()
                        .name("Vasya" + index)
                        .lastName("Pupkin")
                        .email("pupik" + index + "gmail.com")
                        .phone("23456677" + index)
                        .Address("Tel Aviv").build();

                openContactForm();
                fillContactRequiredForm(contact);
                save();
                pause(500);
                logger.info("Provider added cotact --> " +contact.toString());
            }
        }
    }
}



