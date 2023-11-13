import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Membuat GUI
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("E-Voting App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 800);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }
    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel idPemilih = new JLabel("ID PEMILIH");
        idPemilih.setBounds(150, 180, 120, 25);
        idPemilih.setFont(new Font("BebasNeue", Font.BOLD, 14));
        idPemilih.setForeground(Color.decode("#FFF5E0"));
        panel.add(idPemilih);
        JTextField idField = new JTextField();
        idField.setBounds(320, 180, 200, 25);
        panel.add(idField);

        JLabel namaLabel = new JLabel("NAMA PEMILIH");
        namaLabel.setBounds(150, 210, 120, 25);
        namaLabel.setFont(new Font("BebasNeue", Font.BOLD, 14));
        namaLabel.setForeground(Color.decode("#FFF5E0"));
        panel.add(namaLabel);
        JTextField namaField = new JTextField();
        namaField.setBounds(320, 210, 200, 25);
        panel.add(namaField);

        JLabel NIKLabel = new JLabel("NIK PEMILIH");
        NIKLabel.setBounds(150, 240, 120, 25);
        NIKLabel.setFont(new Font("BebasNeue", Font.BOLD, 14));
        NIKLabel.setForeground(Color.decode("#FFF5E0"));
        panel.add(NIKLabel);
        JTextField NIKField = new JTextField();
        NIKField.setBounds(320, 240, 200, 25);
        panel.add(NIKField);

        JLabel statusLabel = new JLabel("STATUS PEMILIH");
        statusLabel.setBounds(150, 270, 120, 25);
        statusLabel.setFont(new Font("BebasNeue", Font.BOLD, 14));
        statusLabel.setForeground(Color.decode("#FFF5E0"));
        panel.add(statusLabel);
        JTextField statusField = new JTextField();
        statusField.setBounds(320, 270, 200, 25);
        panel.add(statusField);

        JLabel idPLabel = new JLabel("ID PEMILIHAN");
        idPLabel.setBounds(150, 300, 120, 25);
        idPLabel.setFont(new Font("BebasNeue", Font.BOLD, 14));
        idPLabel.setForeground(Color.decode("#FFF5E0"));
        panel.add(idPLabel);
        JTextField idPField = new JTextField();
        idPField.setBounds(320, 300, 200, 25);
        panel.add(idPField);

        JLabel namaPLabel = new JLabel("NAMA PEMILIHAN");
        namaPLabel.setBounds(150, 330, 130, 25);
        namaPLabel.setFont(new Font("BebasNeue", Font.BOLD, 14));
        namaPLabel.setForeground(Color.decode("#FFF5E0"));
        panel.add(namaPLabel);
        String[] namaPemilihan = {"PILPRES", "PILKADA"};
        JComboBox<String> namaPComboBox = new JComboBox<>(namaPemilihan);
        namaPComboBox.setBounds(320, 330, 200, 25);
        panel.add(namaPComboBox);

        JLabel namaCapres = new JLabel("NAMA CAPRES");
        namaCapres.setBounds(150, 360, 120, 25);
        namaCapres.setFont(new Font("BebasNeue", Font.BOLD, 14));
        namaCapres.setForeground(Color.decode("#FFF5E0"));
        panel.add(namaCapres);
        JComboBox combocap = new JComboBox();
        try {
            Connection connection = Koneksi.getConnection();
            String query = "SELECT nama_calon FROM calon";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String name = rs.getString("nama_calon");
                combocap.addItem(name);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        combocap.setBounds(320, 360, 200, 25);
        combocap.setBackground(Color.WHITE);
        combocap.setSelectedIndex(-1);
        panel.add(combocap);
        JButton voteButton = new JButton("VOTE");
        voteButton.setBounds(180, 420, 80, 25);
        panel.add(voteButton);

        JButton hasilButton = new JButton("HASIL VOTING");
        hasilButton.setBounds(370, 420, 120, 25);
        panel.add(hasilButton);

        ImageIcon imgPath = new ImageIcon("src/img/EVOTING.png");
        Image imgScale = imgPath.getImage().getScaledInstance(700, 900, Image.SCALE_DEFAULT);
        ImageIcon img = new ImageIcon(imgScale);
        JLabel image = new JLabel(img);
        image.setBounds(0, 0, 700, 900);
        panel.add(image);

        voteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection kon =  Koneksi.getConnection()) {

                    // Select dep_id From ComboBox
                    String selectedCalonName = (String)combocap.getSelectedItem();
                    String sql = "SELECT id_calon FROM calon WHERE nama_calon = ?";
                    PreparedStatement pst = kon.prepareStatement(sql);
                    pst.setString(1, selectedCalonName);
                    ResultSet rs = pst.executeQuery();
                    String CalId = null;
                    if (rs.next()) {
                        CalId = rs.getString("id_calon");
                    }
                    try {
                        String query = "INSERT INTO pemilih (id_pemilih, nama_pemilih, NIK_pemilih, sts_pemilih) VALUES (?, ?, ?, ?)";
                        PreparedStatement preparedStatement = kon.prepareStatement(query);
                        preparedStatement.setInt(1, Integer.parseInt(idField.getText()));
                        preparedStatement.setString(2, namaField.getText());
                        preparedStatement.setLong(3, Long.parseLong(NIKField.getText()));
                        preparedStatement.setString(4, statusField.getText());

                        int rowsInserted = preparedStatement.executeUpdate();
                        if (rowsInserted > 0) {
                            JOptionPane.showMessageDialog(null, "Voting berhasil!");
                            idField.setText("");
                            namaField.setText("");
                            NIKField.setText("");
                            statusField.setText("");
                            idPField.setText("");
                        }
                    } catch (SQLException ae) {
                        ae.printStackTrace();
                    }
                    String query3 = "INSERT INTO pemilihan (id_pemilihan, nama_pemilihan, id_calon, tgl_pemilihan) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement preparedStatement = kon.prepareStatement(query3)) {
                        preparedStatement.setInt(1, Integer.parseInt(idPField.getText()));
                        preparedStatement.setString(2, (String) namaPComboBox.getSelectedItem());
                        preparedStatement.setString(3, CalId);
                        preparedStatement.setObject(4, LocalDateTime.now());

                        int rowsInserted = preparedStatement.executeUpdate();
                        if (rowsInserted > 0) {
                            JOptionPane.showMessageDialog(null, "Voting berhasil!");
                            idPField.setText("");
                        }
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
