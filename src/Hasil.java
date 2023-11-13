class Hasil {
    public String idCalon;
    public String namaCalon;
    public String hasil;

    public Hasil(String idCalon, String namaCalon, String hasil) {
        this.idCalon = idCalon;
        this.namaCalon = namaCalon;
        this.hasil = hasil;
    }

    public void tampil() {
        System.out.println("ID Calon: " + idCalon);
        System.out.println("Nama Calon: " + namaCalon);
        System.out.println("Hasil: " + hasil);
    }

    public void penjumlahan(int jumlahSuara) {
        // Implementasi penjumlahan hasil pemilihan
        this.hasil = String.valueOf(jumlahSuara);
    }

    public String getHasil(){
        return null;
    }

    public String getInfo() {
        return null;
    }
}