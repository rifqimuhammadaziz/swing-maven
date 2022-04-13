package rifqimuhammadaziz.seeder;

import com.github.javafaker.Faker;
import org.junit.Test;
import rifqimuhammadaziz.model.User;
import rifqimuhammadaziz.service.UserDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserSeeder {

    public UserDaoImpl userDao;

    @Test
    public void DatabaseSeeder() throws SQLException, ClassNotFoundException {
        User user = new User();
        userDao = new UserDaoImpl();

        for (int i = 0; i < 100; i++) {
            Faker faker = new Faker();
            String username = faker.name().username();
            String name = faker.name().fullName();
            String address = faker.address().streetAddress();
            String phonenumber = faker.phoneNumber().cellPhone();

            Random r1 = new Random();
            List<String> listGender = new ArrayList();
            listGender.add("MALE");
            listGender.add("FEMALE");
            int randomGender = r1.nextInt(listGender.size());
            String gender = listGender.get(randomGender);

            Random r2 = new Random();
            List<String> listStatus = new ArrayList();
            listStatus.add("ACTIVE");
            listStatus.add("NON-ACTIVE");
            int randomitem = r2.nextInt(listStatus.size());
            String status = listStatus.get(randomitem);

            user.setUsername(username);
            user.setPassword(username);
            user.setFullName(name);
            user.setGender(gender);
            user.setAddress(address);
            user.setPhoneNumber(phonenumber);
            user.setStatus(status);
            System.out.println(user);
            userDao.addData(user);
        }
    }
}
