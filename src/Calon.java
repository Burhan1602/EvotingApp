class Calon {
    public String idCalon;
    public String namaCalon;
    public boolean status;

    public Calon(String idCalon, String namaCalon, boolean status) {
        this.idCalon = idCalon;
        this.namaCalon = namaCalon;
        this.status = status;
    }

    public void tampil() {
        System.out.println("ID Calon: " + idCalon);
        System.out.println("Nama Calon: " + namaCalon);
        System.out.println("Status: " + status);
    }

    public void editCalon(String namaCalon, boolean status) {
        this.namaCalon = namaCalon;
        this.status = status;
    }

    public String getIdCalon() {
        return null;
    }

    public String getNamaCalon() {
        return null;
    }
}