package rifqimuhammadaziz.util;

import rifqimuhammadaziz.model.User;

import java.sql.SQLException;
import java.util.List;

public interface DaoService<T> {

    List<T> getAll() throws SQLException, ClassNotFoundException;

    int addData(T t) throws SQLException, ClassNotFoundException;

    int updateData(T t) throws SQLException, ClassNotFoundException;

    int deleteData(T t) throws SQLException, ClassNotFoundException;
}