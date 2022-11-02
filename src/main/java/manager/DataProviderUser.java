package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {


    @DataProvider
    public Iterator<Object[]> loginData() {

        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"mashka@gmail.com", "mashkA12345$5"});
        list.add(new Object[]{"dashka@gmail.com", "dashkA12345$5"});
        list.add(new Object[]{"sashka@gmail.com", "sashkA12345$5"});


        return list.iterator();

    }
    @DataProvider
    public Iterator<Object[]> XXX(){
        List<Object[]> list = new ArrayList<>();


        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginDataModel(){

        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{new User().withEmail("mashkagmail.com").withPassword("mashkA12345$5")});
        list.add(new Object[]{new User().withEmail("dashka@gmail.com").withPassword("dashkA12345$5")});
        list.add(new Object[]{new User().withEmail("sashka@gmail.com").withPassword("sashkA12345$5")});




        return list.iterator();
    }
}