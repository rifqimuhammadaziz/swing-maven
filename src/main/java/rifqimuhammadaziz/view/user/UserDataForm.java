package rifqimuhammadaziz.view.user;

import com.formdev.flatlaf.FlatLightLaf;
import rifqimuhammadaziz.model.User;
import rifqimuhammadaziz.model.table.UserTableModel;
import rifqimuhammadaziz.service.UserDaoImpl;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDataForm extends JFrame{
    public JTable tableUser;
    private JButton btnDelete;
    private JButton btnRefresh;
    private JPanel rootPanel;
    private JButton addButton;
    private JButton searchButton;
    private JTextField txtSearch;

    private UserDaoImpl userDao;
    private List<User> users;
    private UserTableModel userTableModel;
    private User selectedUser;

    public UserDataForm() {
        ImageIcon saveImage = new ImageIcon("view.icon");
        addButton.setIcon(saveImage);

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
                        userTableModel.fireTableDataChanged();
                    }
                } else if (validate == JOptionPane.NO_OPTION) {
                    userTableModel.fireTableDataChanged();
                }
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        // Button Refresh
        btnRefresh.addActionListener(e -> {
            try {
                users.clear();
                users.addAll(userDao.getAll());
                userTableModel = new UserTableModel(users);
                tableUser.setModel(userTableModel);
                userTableModel.fireTableDataChanged();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchUser();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchUser();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchUser();
            }
        });

        // Button Search
        searchButton.addActionListener(e -> {
            String searchUser = (String) JOptionPane.showInputDialog(
                    null,
                    "Search User",
                    "Testing",
                    JOptionPane.PLAIN_MESSAGE
            );
            if (searchUser != null) {
                try {
                    users.clear();
                    users.addAll(userDao.searchByName(searchUser));
                    userTableModel = new UserTableModel(users);
                    tableUser.setModel(userTableModel);
                    tableUser.setAutoCreateRowSorter(true);
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Button Add
        addButton.addActionListener(e -> {
            RegisterForm frame = new RegisterForm();
            frame.setTitle("Register");
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {

//        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); // WINDOWS LOOK AND FEEL
//        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel"); // METAL
//        UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel"); // MOTIF LOOK AND FEEL
        UIManager.setLookAndFeel(new FlatLightLaf());
        UIManager.put( "Button.arc", 999 );
        UIManager.put( "Component.arc", 999 );
        UIManager.put( "ProgressBar.arc", 999 );
        UIManager.put( "TextComponent.arc", 999 );
        UIManager.put( "Component.focusWidth", 1 );
        UIManager.put( "Table.showHorizontalLines", true);

        JFrame frame = new JFrame("User Data Form");
        frame.setContentPane(new UserDataForm().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

    }

    private void searchUser() {
        try {
            users.clear();
            users.addAll(userDao.searchByName(txtSearch.getText()));
            userTableModel = new UserTableModel(users);
            tableUser.setModel(userTableModel);
            tableUser.setAutoCreateRowSorter(true);
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Notfound");
        }
    }
}
