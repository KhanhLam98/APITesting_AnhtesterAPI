package helpers;

import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DataFakerHelper {
    private static final Faker faker = new Faker(new Locale("en"));
    public static String randomUsername(){
        String username = faker.name().username();
        return username;
    }

    public static String randomFirstname(){
        String firstname = faker.name().firstName();
        return firstname;
    }

    public static String randomLastname(){
        String lastname = faker.name().lastName();
        return lastname;
    }

    public static String randomnEmail(){
        String email = faker.internet().emailAddress();
        return email;
    }

    public static String randomPassword(){
        String password = faker.internet().password();
        return password;
    }

    public static String randomPhoneNumber(){
        String phone = faker.numerify("0##########");
        return phone;
    }

    public static String randomBookName(){
        String bookname = faker.book().title();
        return bookname;
    }
    public static List<String> randomListUsername(int listElement){
        List<String> usernames = new ArrayList<>();
        for (int i = 0; i <= listElement; i++)
            usernames.add(DataFakerHelper.randomUsername());
        return usernames;
    }
    public static List<String> randomListFisrname(int listElement){
        List<String> firstnames = new ArrayList<>();
        for (int i = 0; i <= listElement; i++)
            firstnames.add(DataFakerHelper.randomFirstname());
        return firstnames;
    }

    public static List<String> randomListLastname(int listElement){
        List<String> lastnames = new ArrayList<>();
        for (int i = 0; i <= listElement; i++)
            lastnames.add(DataFakerHelper.randomLastname());
        return lastnames;
    }

    public static List<String> randomListEmail(int listElement){
        List<String> emails = new ArrayList<>();
        for (int i = 0; i <= listElement; i++)
            emails.add(DataFakerHelper.randomnEmail());
        return emails;
    }

    public static String randomCategory() {
        String categoryName = faker.commerce().department();
        return categoryName;
    }
}
