import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;

public class MainAdmin {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainAdmin::createAndShowGUI);
    }
    static void createAndShowGUI() {
        JFrame frame = new JFrame("E-Voting App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 1000);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }
    private static void placeComponents(JPanel panel) {
        panel.setLayout(null); // Ubah pengaturan layout

        JLabel namaLabel = new JLabel("NAMA PEMILIH");
        namaLabel.setBounds(150, 180, 180, 30);
        namaLabel.setFont(new Font("BebasNeue", Font.BOLD, 18));
        namaLabel.setForeground(Color.decode("#FFF5E0"));
        panel.add(namaLabel);
        JTextField namaField = new JTextField();
        namaField.setBounds(450, 180, 200, 30);
        panel.add(namaField);

        JLabel NIKLabel = new JLabel("NIK PEMILIH");
        NIKLabel.setBounds(150, 240, 180, 30);
        NIKLabel.setFont(new Font("BebasNeue", Font.BOLD, 18));
        NIKLabel.setForeground(Color.decode("#FFF5E0"));
        panel.add(NIKLabel);
        JTextField NIKField = new JTextField();
        NIKField.setBounds(450, 240, 200, 30);
        panel.add(NIKField);

        JLabel namaPLabel = new JLabel("NAMA PEMILIHAN");
        namaPLabel.setBounds(150, 300, 180, 30);
        namaPLabel.setFont(new Font("BebasNeue", Font.BOLD, 18));
        namaPLabel.setForeground(Color.decode("#FFF5E0"));
        panel.add(namaPLabel);
        String[] namaPemilihan = {"PILPRES", "PILKADA"};
        JComboBox<String> namaPComboBox = new JComboBox<>(namaPemilihan);
        namaPComboBox.setBounds(450, 300, 200, 30);
        panel.add(namaPComboBox);

        JButton voteButton = new JButton("VOTE");
        voteButton.setBounds(100, 420, 120, 30);
        panel.add(voteButton);

        JButton delButton = new JButton("DELETE");
        delButton.setBounds(260, 420, 120, 30);
        panel.add(delButton);

        JButton upButton = new JButton("UPDATE");
        upButton.setBounds(420, 420, 120, 30);
        panel.add(upButton);

        JButton hasilButton = new JButton("HASIL VOTING");
        hasilButton.setBounds(580, 420, 120, 30);
        panel.add(hasilButton);

        JButton anies = new JButton("ANIES-AMIN");
        anies.setBounds(110,890,150,30);
        panel.add(anies);

        JButton prabowo = new JButton("PRABOWO-GIBRAN");
        prabowo.setBounds(330,890,150,30);
        panel.add(prabowo);

        JButton ganjar = new JButton("GANJAR-MAHFUD");
        ganjar.setBounds(552,890,145,30);
        panel.add(ganjar);

        ImageIcon imgPres = new ImageIcon("src/img/anies.jpeg");
        Image imgSl = imgPres.getImage().getScaledInstance(150, 220, Image.SCALE_DEFAULT);
        ImageIcon img1 = new ImageIcon(imgSl);
        JLabel image1 = new JLabel(img1);
        image1.setBounds(110, 650, 150, 220);
        panel.add(image1);

        ImageIcon imgPres2 = new ImageIcon("src/img/prabowo.jpeg");
        Image imgSl2 = imgPres2.getImage().getScaledInstance(150, 220, Image.SCALE_DEFAULT);
        ImageIcon img2 = new ImageIcon(imgSl2);
        JLabel image2 = new JLabel(img2);
        image2.setBounds(330, 650, 150, 220);
        panel.add(image2);

        ImageIcon imgPres3 = new ImageIcon("src/img/ganjar.jpeg");
        Image imgSl3 = imgPres3.getImage().getScaledInstance(150, 220, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(imgSl3);
        JLabel image3 = new JLabel(img3);
        image3.setBounds(550, 650, 150, 220);
        panel.add(image3);

        ImageIcon imgPath = new ImageIcon("src/img/EVOTING.png");
        Image imgScale = imgPath.getImage().getScaledInstance(800, 1100, Image.SCALE_DEFAULT);
        ImageIcon img = new ImageIcon(imgScale);
        JLabel image = new JLabel(img);
        image.setBounds(0, 0, 800, 1100);
        panel.add(image);

        AtomicReference<String> calonNama = new AtomicReference<>();

        anies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection kon =  Koneksi.getConnection()) {
                    String query = "SELECT id_calon FROM calon WHERE nama_calon = 'Anies'";
                    PreparedStatement pstmt = kon.prepareStatement(query);
                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()) {
                        calonNama.set(rs.getString("id_calon"));
                        ganjar.setVisible(false);
                        prabowo.setVisible(false);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        prabowo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection kon =  Koneksi.getConnection()) {
                    String query = "SELECT id_calon FROM calon WHERE nama_calon = 'Prabowo'";
                    PreparedStatement pstmt = kon.prepareStatement(query);
                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()) {
                        calonNama.set(rs.getString("id_calon"));
                        anies.setVisible(false);
                        ganjar.setVisible(false);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        ganjar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection kon =  Koneksi.getConnection()) {
                    String query = "SELECT id_calon FROM calon WHERE nama_calon = 'Ganjar'";
                    PreparedStatement pstmt = kon.prepareStatement(query);
                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()) {
                        calonNama.set(rs.getString("id_calon"));
                        anies.setVisible(false);
                        prabowo.setVisible(false);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        voteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection kon =  Koneksi.getConnection()) {
                    try {
                        String query = "INSERT INTO pemilih (nama_pemilih, NIK_pemilih) VALUES (?, ?)";
                        PreparedStatement preparedStatement = kon.prepareStatement(query);
                        preparedStatement.setString(1, namaField.getText());
                        preparedStatement.setLong(2, Long.parseLong(NIKField.getText()));

                        int rowsInserted = preparedStatement.executeUpdate();
                        if (rowsInserted > 0) {
                            JOptionPane.showMessageDialog(null, "Voting berhasil!");
                            namaField.setText("");
                            NIKField.setText("");
                        }
                    } catch (SQLException ae) {
                        ae.printStackTrace();
                    }

                    try  {
                        String query3 = "INSERT INTO pemilihan (nama_pemilihan, id_calon, tgl_pemilihan) VALUES (?, ?, ?)";
                        PreparedStatement preparedStatement = kon.prepareStatement(query3);
                        preparedStatement.setString(1, (String) namaPComboBox.getSelectedItem());
                        preparedStatement.setString(2, String.valueOf(calonNama));
                        preparedStatement.setObject(3, LocalDateTime.now());

                        int rowsInserted = preparedStatement.executeUpdate();
                        if (rowsInserted > 0) {
                            JOptionPane.showMessageDialog(null, "Voting berhasil!");
                            anies.setVisible(true);
                            ganjar.setVisible(true);
                            prabowo.setVisible(true);
                        }
                    }
                    catch (SQLException x){
                        x.printStackTrace();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        hasilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HasilVoting();
            }
        });
    }
}
