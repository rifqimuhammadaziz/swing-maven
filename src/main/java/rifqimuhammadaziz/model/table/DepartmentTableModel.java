package rifqimuhammadaziz.model.table;

import rifqimuhammadaziz.model.Department;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DepartmentTableModel extends AbstractTableModel {

    private List<Department> departments;
    private final String[] COLUMNS = {"ID", "DEPARTMENT"};

    public DepartmentTableModel(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public int getRowCount() {
        return departments.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> departments.get(rowIndex).getId();
            case 1 -> departments.get(rowIndex).getName();
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