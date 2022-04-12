package rifqimuhammadaziz.model.table;

import rifqimuhammadaziz.model.Student;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {

    private List<Student> students;
    private final String[] COLUMNS = {"ID", "FIRST NAME", "LAST NAME", "DEPARTMENT"};

    public StudentTableModel(List<Student> students) {
        this.students = students;
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> students.get(rowIndex).getId();
            case 1 -> students.get(rowIndex).getFirstName();
            case 2 -> students.get(rowIndex).getLastName();
            case 3 -> students.get(rowIndex).getDepartment().getName();
            default -> "";
        };
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (getValueAt(0, columnIndex) != null) {
            return getValueAt(0, columnIndex).getClass();
        }
        return Object.class;
    }
}
