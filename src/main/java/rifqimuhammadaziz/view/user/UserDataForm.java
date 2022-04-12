package rifqimuhammadaziz.view.user;

import rifqimuhammadaziz.model.User;
import rifqimuhammadaziz.model.table.UserTableModel;
import rifqimuhammadaziz.service.UserDaoImpl;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDataForm extends JFrame{
    public JTable tableUser;
    private JButton btnDelete;
    private JButton btnRefresh;
    private JPanel rootPanel;

    private UserDaoImpl userDao;
    private List<User> users;
    private UserTableModel userTableModel;
    private User selectedUser;

    public UserDataForm() {
        users = new ArrayList<>();
        userDao = new UserDaoImpl();

        // Fetch Data
        try {
            users.addAll(userDao.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Get user data and add to table
        userTableModel = new UserTableModel(users);
        tableUser.setModel(userTableModel);
        tableUser.setAutoCreateRowSorter(true);

        // Select row to delete
        tableUser.getSelectionModel().addListSelectionListener(e -> {
            if (!tableUser.getSelectionModel().isSelectionEmpty()) {
                int selectedIndex = tableUser.convertRowIndexToModel(tableUser.getSelectedRow());
                selectedUser = users.get(selectedIndex);
                if (selectedUser == null) {
                    btnDelete.setEnabled(true);
                }
            }
        });

        // Double click row to edit
        tableUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (e.getClickCount() == 2) {
                    if (!tableUser.getSelectionModel().isSelectionEmpty()) {
                        int selectedIndex = tableUser.convertRowIndexToModel(tableUser.getSelectedRow());
                        selectedUser = users.get(selectedIndex);
                        if (selectedUser != null) {
                            EditForm frame = new EditForm(selectedUser);
                            frame.setTitle("Edit User : " + selectedUser.getUsername());
                            frame.pack();
                            frame.setLocationRelativeTo(null);
                            frame.setVisible(true);
                        }
                    }
                }
            }
        });

        // Select row to delete
        tableUser.getSelectionModel().addListSelectionListener(e -> {
            if (!tableUser.getSelectionModel().isSelectionEmpty()) {
                int selectedIndex = tableUser.convertRowIndexToModel(tableUser.getSelectedRow());
                selectedUser = users.get(selectedIndex);
                if (selectedUser != null) {
                    btnDelete.setEnabled(true);
                }
            }
        });

        // Button Delete
        btnDelete.addActionListener(e -> {
            try {
                int validate = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure to Delete Department : \n" + selectedUser.getId() + " - " + selectedUser.getFullName(),
                        "Delete Department",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (validate == JOptionPane.YES_OPTION) {
                    if (userDao.deleteData(selectedUser) == 1) {
                        users.clear();
                        users.addAll(userDao.getAll());
                    }
                } else if (validate == JOptionPane.NO_OPTION) {
                    userTableModel.fireTableDataChanged();
                }
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        btnRefresh.addActionListener(e -> {
            users.clear();
            try {
                users.addAll(userDao.getAll());
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            userTableModel.fireTableDataChanged();
            JOptionPane.showMessageDialog(null, "Table Refreshed", "Load Data", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public static void main(String[] args) {
//        try {
//             UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); // WINDOWS LOOK AND FEEL
//             UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel"); // METAL
//             UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel"); // MOTIF LOOK AND FEEL
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }
        JFrame frame = new JFrame("User Data Form");
        frame.setContentPane(new UserDataForm().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

    }

}
