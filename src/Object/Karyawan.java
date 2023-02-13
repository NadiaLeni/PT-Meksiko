package Object;

public abstract class Karyawan {
	
	private String kode, nama, gender, jabatan;

	public Karyawan(String kode, String nama, String gender, String jabatan) {
		super();
		this.kode = kode;
		this.nama = nama;
		this.gender = gender;
		this.jabatan = jabatan;
	}
	
	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJabatan() {
		return jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public abstract void info();

}
