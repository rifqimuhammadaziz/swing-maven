package rifqimuhammadaziz;

import com.github.javafaker.Faker;
import org.junit.Test;
import rifqimuhammadaziz.model.User;
import rifqimuhammadaziz.service.DepartmentDaoImpl;
import rifqimuhammadaziz.service.StudentDaoImpl;
import rifqimuhammadaziz.service.UserDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserServiceTest {

    public UserDaoImpl userService;
    private List<User> users;
    User user = new User();

    @Test
    public void testAddData() throws SQLException, ClassNotFoundException {
        User user = new User();
        userService = new UserDaoImpl();

        Faker faker = new Faker();
        String username = faker.name().username();
        String fullName = faker.name().fullName();
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
        user.setFullName(fullName);
        user.setGender(gender);
        user.setAddress(address);
        user.setPhoneNumber(phonenumber);
        user.setStatus(status);
        System.out.println(user);

        userService.addData(user);
    }

    @Test
    public void testGetAll() throws SQLException, ClassNotFoundException {
        userService = new UserDaoImpl();
        Iterable<User> users = userService.getAll();

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindById() throws SQLException, ClassNotFoundException {
        userService = new UserDaoImpl();
        Integer id = 1;
        user = userService.findById(id);
        System.out.println(user);
    }

    @Test
    public void testFindByName() throws SQLException, ClassNotFoundException {
        userService = new UserDaoImpl();
        String name = "Amira Nolan";
        users = userService.findByName(name);
        System.out.println(users);
    }

    @Test
    public void testSearchByName() throws SQLException, ClassNotFoundException {
        userService = new UserDaoImpl();
        String name = "Amira";
        users = userService.searchByName(name);
        System.out.println(users);
    }
}
