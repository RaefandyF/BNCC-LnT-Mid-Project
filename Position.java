
public class Position {

	private String jabatan,kode,nama,kelamin;
	private double gaji;
	
	public Position(String jabatan, double gaji, String kode,String nama, String kelamin) {
		this.setJabatan(jabatan);
		this.setGaji(gaji);
		this.setKode(kode);
		this.setNama(nama);
		this.setKelamin(kelamin);
		
	}

	public String getJabatan() {
		return jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public double getGaji() {
		return gaji;
	}

	public void setGaji(double gaji) {
		this.gaji = gaji;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public String getKelamin() {
		return kelamin;
	}

	public void setKelamin(String kelamin) {
		this.kelamin = kelamin;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
}


