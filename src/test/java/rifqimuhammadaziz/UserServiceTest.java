package rifqimuhammadaziz;

import com.github.javafaker.Faker;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import rifqimuhammadaziz.model.User;
import rifqimuhammadaziz.service.UserServiceImpl;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class UserServiceTest {

    public UserServiceImpl userService;
    private List<User> users;

    @Test
    public void testAddData() throws SQLException, ClassNotFoundException {
        User user = new User();
        userService = new UserServiceImpl();

        user.setUsername("xenosty2");
        user.setPassword("xenosty1");
        user.setFullName("Rifqi Muhammad Aziz");
        user.setGender("MALE");
        user.setAddress("Tegal");
        user.setPhoneNumber("081902401999");
        user.setStatus("ACTIVE");
        System.out.println(user);

        int savedUser = userService.addData(user);

        Assertions
    }

    @Test
    public void testDatabaseSeeder() throws SQLException, ClassNotFoundException {
        User user = new User();
        userService = new UserServiceImpl();

        for (int i = 0; i < 100; i++) {
            Faker faker = new Faker();
            String username = faker.name().username();
            String name = faker.name().fullName();
            String address = faker.address().streetAddress();
            String phonenumber = faker.phoneNumber().cellPhone();

            user.setUsername(username);
            user.setPassword(username);
            user.setFullName(name);
            user.setGender("MALE");
            user.setAddress(address);
            user.setPhoneNumber(phonenumber);
            user.setStatus("ACTIVE");
            System.out.println(user);
            userService.addData(user);
        }


    }
}
