package Object;

public class Admin extends Karyawan{
	
	private int gaji;

	public Admin(String kode, String nama, String gender, String jabatan, int gaji) {
		super(kode, nama, gender, jabatan);
		this.gaji = gaji;
	}

	@Override
	public void info() {
		System.out.printf("%d", this.gaji);
	}

	public int getGaji() {
		return gaji;
	}

	public void setGaji(int gaji) {
		this.gaji = gaji;
	}
	

}
