class Pemilihan extends Pemilih {
    public String idPemilihan;
    public String namaPemilihan;
    public String tglPemilihan;

    public Pemilihan(String idPemilih, String namaPemilih, String nikPemilih, String statusPemilih,
                     String idPemilihan, String namaPemilihan, String tglPemilihan) {
        super(idPemilih, namaPemilih, nikPemilih, statusPemilih);
        this.idPemilihan = idPemilihan;
        this.namaPemilihan = namaPemilihan;
        this.tglPemilihan = tglPemilihan;
    }

    public static Hasil[] getHasil() {
        return null;
    }

    @Override
    public void tampil() {
        super.tampil();
        System.out.println("ID Pemilihan: " + idPemilihan);
        System.out.println("Nama Pemilihan: " + namaPemilihan);
        System.out.println("Tanggal Pemilihan: " + tglPemilihan);
    }

    public void pilih(Calon calon) {
        // Implementasi logika pemilihan calon
        System.out.println("Pemilih dengan ID " + super.getIdPemilih() + " memilih Calon dengan ID " + calon.getIdCalon());
    }
}
