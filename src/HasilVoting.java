import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HasilVoting extends JFrame implements ActionListener {
    JButton HasilvButton;
    JTable table = new JTable();
    JLabel pemenang;

    HasilVoting(){
        JScrollPane Jsp = new JScrollPane(table);
        Jsp.setBounds(0,80,400,100);
        add(Jsp);

        HasilvButton = new JButton("CLOSE");
        HasilvButton.setBounds(140, 350, 100, 30);
        HasilvButton.addActionListener(this);
        add(HasilvButton);

        JLabel calon = new JLabel("<html><center>YANG AKAN MENJADI PRESIDEN BERDASARKAN<br/>PEROLEHAN SUARA TERBANYAK ADALAH :</center></html>");
        calon.setBounds(65, 200, 300, 40);
        calon.setForeground(Color.WHITE);
        add(calon);
        pemenang = new JLabel();
        try {
            Connection kon = Koneksi.getConnection();
            if (kon == null) {
                System.out.println("Koneksi ke database gagal");
                return;
            }
            String SQL = "SELECT c.nama_calon, COUNT(p.id_calon) AS jumlah_suara \n" +
                    "FROM pemilihan p\n" +
                    "JOIN calon c ON p.id_calon = c.id_calon\n" +
                    "GROUP BY c.id_calon \n" +
                    "ORDER BY jumlah_suara DESC \n" +
                    "LIMIT 1\n";
            PreparedStatement stmt = kon.prepareStatement(SQL);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                pemenang.setText(result.getString("nama_calon"));
            } else {
                System.out.println("Pemilihan belum dilakukan");
            }
        } catch (Exception ax) {
            ax.printStackTrace();
        }
        pemenang.setBounds(150, 250, 100, 40);
        pemenang.setFont(new Font("BebasNeue", Font.BOLD, 14));
        pemenang.setForeground(Color.WHITE);
        add(pemenang);

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(400,500);
        setLocation(450,420);
        setVisible(true);
        hasilVoting();

        ImageIcon imgPath = new ImageIcon("src/img/HASIL EVOTING.png");
        Image imgScale = imgPath.getImage().getScaledInstance(400, 500, Image.SCALE_DEFAULT);
        ImageIcon img = new ImageIcon(imgScale);
        JLabel image = new JLabel(img);
        image.setBounds(0, 0, 400, 500);
        add(image);
    }

    public void hasilVoting() {
        try {
            Connection kon = Koneksi.getConnection();
            if (kon == null) {
                System.out.println("Koneksi ke database gagal");
                return;
            }
            String querySQL = "SELECT c.nama_calon AS Nama, COUNT(p.id_calon) AS \"Jumlah Pemilih\" \n" +
                            "FROM calon c \n" +
                            "JOIN pemilihan p ON c.id_calon = p.id_calon \n" +
                            "GROUP BY c.nama_calon \n" +
                            "ORDER BY COUNT(p.id_calon) DESC\n";
            PreparedStatement stmt = kon.prepareStatement(querySQL);
            ResultSet result = stmt.executeQuery();

            if (table == null) {
                System.out.println("Tabel belum diinisialisasi");
                return;
            }

            table.setModel(DbUtils.resultSetToTableModel(result));
        } catch (Exception ax) {
            ax.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e){
        new Main();
        setVisible(false);
    }

    public static void main(String[] args) {
        new HasilVoting();
    }
}