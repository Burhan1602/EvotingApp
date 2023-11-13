class Pemilih {
    public String idPemilih;
    public String namaPemilih;
    public String nikPemilih;
    public String statusPemilih;

    public Pemilih(String idPemilih, String namaPemilih, String nikPemilih, String statusPemilih) {
        this.idPemilih = idPemilih;
        this.namaPemilih = namaPemilih;
        this.nikPemilih = nikPemilih;
        this.statusPemilih = statusPemilih;
    }

    public void tampil() {
        System.out.println("ID Pemilih: " + idPemilih);
        System.out.println("Nama Pemilih: " + namaPemilih);
        System.out.println("NIK Pemilih: " + nikPemilih);
        System.out.println("Status Pemilih: " + statusPemilih);
    }

    public void editPemilih(String namaPemilih, String nikPemilih, String statusPemilih) {
        this.namaPemilih = namaPemilih;
        this.nikPemilih = nikPemilih;
        this.statusPemilih = statusPemilih;
    }

    public boolean cekValidasi() {
        // Implementasi logika validasi pemilih
        return true;
    }

    protected String getIdPemilih() {
        return null;
    }
}