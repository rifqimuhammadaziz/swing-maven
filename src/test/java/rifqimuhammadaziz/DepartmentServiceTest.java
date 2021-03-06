package rifqimuhammadaziz;

import org.junit.Test;
import rifqimuhammadaziz.model.Department;
import rifqimuhammadaziz.service.DepartmentDaoImpl;
import rifqimuhammadaziz.service.StudentDaoImpl;
import rifqimuhammadaziz.service.UserDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentServiceTest {

    public DepartmentDaoImpl departmentDao;
    List<Department> departments = new ArrayList<>();
    Department department = new Department();

    @Test
    public void testAddData() throws SQLException, ClassNotFoundException {
        Department department = new Department();
        departmentDao = new DepartmentDaoImpl();

        department.setName("Fakultas Ilmu Komputer");

        System.out.println(department);
        departmentDao.addData(department);
    }

    @Test
    public void testGetAll() throws SQLException, ClassNotFoundException {
        departmentDao = new DepartmentDaoImpl();
        Iterable<Department> departments = departmentDao.getAll();

        for (Department department : departments) {
            System.out.println(department);
        }
    }

    @Test
    public void testFindById() throws SQLException, ClassNotFoundException {
        departmentDao = new DepartmentDaoImpl();
        Integer id = 1;
        department = departmentDao.findById(id);
        System.out.println(department);
    }

    @Test
    public void testFindByName() throws SQLException, ClassNotFoundException {
        departmentDao = new DepartmentDaoImpl();
        String name = "Fakultas Ilmu Komputer";
        departments = departmentDao.findByName(name);
        System.out.println(departments);
    }

    @Test
    public void testSearchByName() throws SQLException, ClassNotFoundException {
        departmentDao = new DepartmentDaoImpl();
        String name = "Fakultas";
        departments = departmentDao.searchByName(name);
        System.out.println(departments);
    }
}
