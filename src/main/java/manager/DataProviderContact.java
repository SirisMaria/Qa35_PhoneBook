package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContact {

    @DataProvider
    public Iterator<Object[]> contactValidData() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/book.csv"));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[]{
                    Contact.builder()
                            .name(split[0])
                            .lastName(split[1])
                            .phone(split[2])
                            .email(split[3])
                            .Address(split[4])
                            .build()
            });

            line = reader.readLine();
            ;

        }
        return list.iterator();

    }}