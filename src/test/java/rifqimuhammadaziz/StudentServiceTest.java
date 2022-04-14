package rifqimuhammadaziz;

import com.github.javafaker.Faker;
import org.junit.Test;
import rifqimuhammadaziz.model.Department;
import rifqimuhammadaziz.model.Student;
import rifqimuhammadaziz.service.StudentDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentServiceTest {

    private StudentDaoImpl studentDao;
    private List<Student> students;
    Student student = new Student();

    @Test
    public void testAddData() throws SQLException, ClassNotFoundException {
        Student student = new Student();
        Department department = new Department();
        studentDao = new StudentDaoImpl();

        Faker faker = new Faker();
        String first_name = faker.name().firstName();
        String last_name = faker.name().lastName();
        String address = faker.address().streetAddress();

        Random r1 = new Random();
        List<String> randomNumber = new ArrayList();
        randomNumber.add("1");
        randomNumber.add("2");
        int randomGender = r1.nextInt(randomNumber.size());
        String random = randomNumber.get(randomGender);

        student.setFirstName(first_name);
        student.setLastName(last_name);
        student.setAddress(address);
        student.setDepartment(new Department(1, "Testing"));
        System.out.println(student);

        studentDao.addData(student);
    }

    @Test
    public void testGetAll() throws SQLException, ClassNotFoundException {

        Iterable<Student> students = studentDao.getAll();

        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void testGetSingle() throws SQLException, ClassNotFoundException {
        studentDao = new StudentDaoImpl();
        Integer id = 4;
        student = studentDao.findById(id);
        System.out.println(student);
    }
}
