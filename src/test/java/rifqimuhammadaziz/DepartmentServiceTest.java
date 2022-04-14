package rifqimuhammadaziz;

import org.junit.Test;
import rifqimuhammadaziz.model.Department;
import rifqimuhammadaziz.service.DepartmentDaoImpl;
import rifqimuhammadaziz.service.UserDaoImpl;

import java.sql.SQLException;

public class DepartmentServiceTest {

    public DepartmentDaoImpl departmentDao;
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
}
