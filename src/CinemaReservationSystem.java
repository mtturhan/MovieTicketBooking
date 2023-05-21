import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CinemaReservationSystem {
    private JFrame mainFrame;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton loginButton;
    private JComboBox<String> movieComboBox;
    private JButton selectMovieButton;
    private JFrame seatSelectionFrame;
    private JCheckBox[] seatCheckBoxes;
    private JButton confirmButton;
    private JLabel confirmationLabel;
    private Map<String, String> userCredentials;
    private String selectedMovie;
    private boolean[] selectedSeats;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CinemaReservationSystem().createAndShowGUI();
            }
        });
    }

    public CinemaReservationSystem() {
        userCredentials = new HashMap<>();
        selectedSeats = new boolean[10];
    }

    public void createAndShowGUI() {
        // Ana form
        mainFrame = new JFrame("Cinema Reservation System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridLayout(4, 1));

        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new FlowLayout());
        registerPanel.add(new JLabel("Name:"));
        nameField = new JTextField(20);
        registerPanel.add(nameField);
        mainFrame.add(registerPanel);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new FlowLayout());
        passwordPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        passwordPanel.add(passwordField);
        mainFrame.add(passwordPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        buttonPanel.add(registerButton);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });
        buttonPanel.add(loginButton);
        mainFrame.add(buttonPanel);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void registerUser() {
        String name = nameField.getText();
        String password = new String(passwordField.getPassword());

        if (name.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Please enter name and password.");
            return;
        }

        if (userCredentials.containsKey(name)) {
            JOptionPane.showMessageDialog(mainFrame, "User already exists with this name.");
            return;
        }

        userCredentials.put(name, password);

        JOptionPane.showMessageDialog(mainFrame, "Registration successful!");

        nameField.setText("");
        passwordField.setText("");
    }

    public void loginUser() {
        String name = nameField.getText();
        String password = new String(passwordField.getPassword());

        if (name.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Please enter name and password.");
            return;
        }

        if (!userCredentials.containsKey(name) || !userCredentials.get(name).equals(password)) {
            JOptionPane.showMessageDialog(mainFrame, "Invalid name or password.");
            return;
        }

        // Giriş başarılı olduğunda, film seçimi formunu göster
        showMovieSelectionForm();
    }

    public void showMovieSelectionForm() {
        mainFrame.setVisible(false);

        JFrame movieSelectionFrame = new JFrame("Movie Selection");
        movieSelectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);movieSelectionFrame.setLayout(new FlowLayout());

        JPanel moviePanel = new JPanel();
        moviePanel.setLayout(new FlowLayout());
        moviePanel.add(new JLabel("Select a movie:"));
        String[] movies = {"John Wick 4", "Fast And Furious X", "Transformers", "Avatar", "Avengers:End Game", "Batman v Superman"};
        movieComboBox = new JComboBox<>(movies);
        moviePanel.add(movieComboBox);
        movieSelectionFrame.add(moviePanel);

        selectMovieButton = new JButton("Select");
        selectMovieButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedMovie = (String) movieComboBox.getSelectedItem();
                movieSelectionFrame.dispose();
                showSeatSelectionForm();
            }
        });
        movieSelectionFrame.add(selectMovieButton);

        movieSelectionFrame.pack();
        movieSelectionFrame.setVisible(true);
    }

    public void showSeatSelectionForm() {
        seatSelectionFrame = new JFrame("Seat Selection");
        seatSelectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        seatSelectionFrame.setLayout(new FlowLayout());

        JPanel seatPanel = new JPanel();
        seatPanel.setLayout(new FlowLayout());
        seatPanel.add(new JLabel("Select seats:"));
        seatCheckBoxes = new JCheckBox[25];
        for (int i = 0; i < seatCheckBoxes.length; i++) {
            seatCheckBoxes[i] = new JCheckBox(Integer.toString(i + 1));
            seatPanel.add(seatCheckBoxes[i]);
        }
        seatSelectionFrame.add(seatPanel);

        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveReservation();
            }
        });
        seatSelectionFrame.add(confirmButton);

        seatSelectionFrame.pack();
        seatSelectionFrame.setVisible(true);
    }

    public void saveReservation() {
        // Seçilen koltukları kaydet
        for (int i = 0; i < seatCheckBoxes.length; i++) {
            selectedSeats[i] = seatCheckBoxes[i].isSelected();
        }

        // Rezervasyonu kaydetme işlemlerini burada gerçekleştirin

        JOptionPane.showMessageDialog(seatSelectionFrame, "Reservation saved!");

        seatSelectionFrame.dispose();
        mainFrame.dispose();
    }
}