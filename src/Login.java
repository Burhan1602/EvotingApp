import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener{

    JTextField txtUser;
    JPasswordField txtPassword;
    JButton admin, user;
    Login() {

        JLabel username = new JLabel("USERNAME");
        username.setBounds(140, 70, 150, 20);
        username.setFont(new Font("BebasNeue", Font.BOLD, 18));
        username.setForeground(Color.decode("#FFF5E0"));
        add(username);

        txtUser = new JTextField();
        txtUser.setBounds(290, 70, 150, 30);
        add(txtUser);

        JLabel password = new JLabel("PASSWORD");
        password.setBounds(140, 110, 150, 20);
        password.setFont(new Font("BebasNeue", Font.BOLD, 18));
        password.setForeground(Color.decode("#FFF5E0"));
        add(password);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(290, 110, 150, 30);
        add(txtPassword);

        //  Set Button Stylead
        user = new JButton("USER");
        user.setBounds(180, 180, 80,35);
        user.addActionListener(this);
        add(user);

        //  Set Screen Size And Screen Location
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(600, 300);
        setLocation(420, 170);
        setVisible(true);

        //  Set Button Style
        admin = new JButton("ADMIN");
        admin.setBounds(320, 180, 80,35);
        admin.addActionListener(this);
        add(admin);

        //  Set Screen Size And Screen Location
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(600, 300);
        setLocation(420, 170);
        setVisible(true);

        ImageIcon imgPath = new ImageIcon("src/img/LOGIN.png");
        Image imgScale = imgPath.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon img = new ImageIcon(imgScale);
        JLabel image = new JLabel(img);
        image.setBounds(0, 0, 600, 300);
        add(image);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == admin){
            try {
                // Get the username and password from the text fields
                String username = txtUser.getText();
                String password = txtPassword.getText();

                // Run a SQL query
                String sql = "SELECT * FROM login WHERE id_admin = ? AND pass_admin = ?";

                // Use try-with-resources to automatically close the resources after use
                Connection kon = Koneksi.getConnection();
                PreparedStatement statement = kon.prepareStatement(sql);

                statement.setString(1, username);
                statement.setString(2, password);

                ResultSet resultSet = statement.executeQuery();
                // Iterate through the result set
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "Login Berhasil!!");
                    this.dispose(); // Tutup antarmuka Login
                    MainAdmin admin = new MainAdmin();
                    admin.createAndShowGUI(); // Memanggil metode untuk menampilkan MainAdmin
                } else {
                    JOptionPane.showMessageDialog(null, "Username atau Password Salah!!");
                }
                JOptionPane.showMessageDialog(null, "Username atau Password Salah!!");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            this.dispose(); // Tutup antarmuka Login
            Main main = new Main();
            main.createAndShowGUI();
        }

    }

    public static void main(String[] arg){
        new Login();
    }
}