package rifqimuhammadaziz.view.student;

import rifqimuhammadaziz.model.Department;
import rifqimuhammadaziz.model.table.DepartmentTableModel;
import rifqimuhammadaziz.service.DepartmentDaoImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDataForm extends Container {
    private JTable tableDepartment;
    private JButton btnDelete;
    public JPanel rootPanel;

    private DepartmentDaoImpl departmentDao;
    private List<Department> departments;
    private DepartmentTableModel departmentTableModel;
    private Department selectedDepartment;

    public DepartmentDataForm() {
        departments = new ArrayList<>();
        departmentDao = new DepartmentDaoImpl();

        // Fetch Data
        try {
            departments.addAll(departmentDao.getAll());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Get department data and Add to table
        departmentTableModel = new DepartmentTableModel(departments);
        tableDepartment.setModel(departmentTableModel);
        tableDepartment.setAutoCreateRowSorter(true);

        // Select Row to Delete
        tableDepartment.getSelectionModel().addListSelectionListener(e -> {
            if (!tableDepartment.getSelectionModel().isSelectionEmpty()) {
                int selectedIndex = tableDepartment.convertRowIndexToModel(tableDepartment.getSelectedRow());
                selectedDepartment = departments.get(selectedIndex);
                if (selectedDepartment != null) {
                    btnDelete.setEnabled(true);
                }
            }
        });

        // Select Row to Edit
        tableDepartment.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String editDepartment = (String) JOptionPane.showInputDialog(
                            null,
                            "Edit Department : " + selectedDepartment.getName().toUpperCase(),
                            "Testing",
                            JOptionPane.INFORMATION_MESSAGE, null, null, selectedDepartment.getName()
                    );
                    String first = null;
                    try {
                        first = selectedDepartment.getName();
                        selectedDepartment.setName(editDepartment);
                        if (departmentDao.updateData(selectedDepartment) == 1) {
                            departments.clear();
                            departments.addAll(departmentDao.getAll());
                            departmentTableModel.fireTableDataChanged();
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        // cancel update
                        selectedDepartment.setName(first);
                    }
                }
            }
        });

        // Button Delete
        btnDelete.addActionListener(e -> {
            try {
                int validate = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure to Delete Department : \n" + selectedDepartment.getId() + " - " + selectedDepartment.getName(),
                        "Delete Department",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (validate == JOptionPane.YES_OPTION) {
                    if (departmentDao.deleteData(selectedDepartment) == 1) {
                        departments.clear();
                        departments.addAll(departmentDao.getAll());
                        departmentTableModel.fireTableDataChanged();
                    }
                } else if (validate == JOptionPane.NO_OPTION) {
                    departmentTableModel.fireTableDataChanged();
                }
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }
}
