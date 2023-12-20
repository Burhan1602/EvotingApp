class Pemilih {
    public String namaPemilih;
    public String nikPemilih;

    public Pemilih(String namaPemilih, String nikPemilih, String statusPemilih) {
        this.namaPemilih = namaPemilih;
        this.nikPemilih = nikPemilih;
    }

    public void tampil() {
        System.out.println("Nama Pemilih: " + namaPemilih);
        System.out.println("NIK Pemilih: " + nikPemilih);
    }

    public void editPemilih(String namaPemilih, String nikPemilih, String statusPemilih) {
        this.namaPemilih = namaPemilih;
        this.nikPemilih = nikPemilih;
    }

    public boolean cekValidasi() {
        // Implementasi logika validasi pemilih
        return true;
    }

    protected String getIdPemilih() {
        return null;
    }
}