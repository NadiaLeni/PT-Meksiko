package ptMeksiko;

import java.util.Scanner;
import java.util.Vector;
import java.util.Random;

import Object.Admin;
import Object.Karyawan;
import Object.Manager;
import Object.Supervisor;

public class main {
	
	Scanner sc = new Scanner(System.in);
	
	Vector<Karyawan> karyawan = new Vector<Karyawan>();

	public main() {
		// TODO Auto-generated constructor stub
		int choose;
		do {
			try {
				System.out.println(
						"1. Insert Data\n" + "2. View Data\n" + "3. Update Data\n" + "4. Delete Data\n" + "5. Exit\n");
				System.out.print("Choose >> ");
				choose = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				// TODO: handle exception
				choose = -1;
				sc.nextLine();
			}
			
			switch(choose) {
			case 1:
				add();
				System.out.println("ENTER to return");
				sc.nextLine();
				break;
			case 2:
				read();
				System.out.println("ENTER to return");
				sc.nextLine();
				break;
			case 3:
				update();
				System.out.println("ENTER to return");
				sc.nextLine();
				break;
			case 4:
				delete();
				System.out.println("ENTER to return");
				sc.nextLine();
				break;
			case 5:
				choose = 5;
				break;
			default:
				System.out.println("Tolong input angka sesuai menu.");
				sc.nextLine();
			}
		} while (choose != 5);
		
		System.out.println("Thank you...");
		
	}
	
	public void add() {
		String kode, nama, gender, jabatan;
		int gaji;
		
		String alphabet = "";
		char letter = 65; // ASCII Value 65 = Capital A
		for(int x=0; x<26; x++) {
			alphabet += letter++;
		}
		
		Random r = new Random();
		String s_random = "";
		
		for(int i=0; i<2; i++) {
			int number = r.nextInt(alphabet.length());
			s_random += alphabet.charAt(number);
		}
		
		int n_random1 = r.nextInt(9);
		int n_random2 = r.nextInt(9);
		int n_random3 = r.nextInt(9);
		int n_random4 = r.nextInt(9);

		kode = s_random + "-" + n_random1 + n_random2 + n_random3 + n_random4;
		
		do {
			System.out.print("Input nama karyawan [>= 3]: ");
			nama = sc.nextLine();
		} while (nama.length() < 3);
		
		do {
			System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
			gender = sc.nextLine();
		} while (!gender.equals("Laki-laki") && !gender.equals("Perempuan"));
		
		do {
			System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			jabatan = sc.nextLine();
		} while (!jabatan.equals("Manager") && !jabatan.equals("Supervisor") && !jabatan.equals("Admin"));
		
		if(jabatan.equals("Manager")) {
			gaji = 8000000;
			Manager m = new Manager(kode, nama, gender, jabatan, gaji);
			karyawan.add(m);
		}
		else if(jabatan.equals("Supervisor")) {
			gaji = 6000000;
			Supervisor s = new Supervisor(kode, nama, gender, jabatan, gaji);
			karyawan.add(s);
		}
		else {
			gaji = 4000000;
			Admin a = new Admin(kode, nama, gender, jabatan, gaji);
			karyawan.add(a);
		}
		
		for(Karyawan karyawan : karyawan) {
			if(karyawan instanceof Manager) {
				Manager e = (Manager) karyawan;
				System.out.printf("|%4d|%17s|%30s|%15s|%14s|%13d|\n", i++, e.getKode(), e.getNama(), e.getGender(), e.getJabatan(), e.getGaji());
			}
			else if(karyawan instanceof Supervisor) {
				Supervisor e = (Supervisor) karyawan;
				System.out.printf("|%4d|%17s|%30s|%15s|%14s|%13d|\n", i++, e.getKode(), e.getNama(), e.getGender(), e.getJabatan(), e.getGaji());
			}
			else {
				Admin e = (Admin) karyawan;
				System.out.printf("|%4d|%17s|%30s|%15s|%14s|%13d|\n", i++, e.getKode(), e.getNama(), e.getGender(), e.getJabatan(), e.getGaji());
			}
		}
		
		System.out.println("Berhasil menambahkan karyawan dengan id " + kode);
		
	}
	
	public void read() {
		if(!karyawan.isEmpty()) {
			System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
			System.out.println("|No  |Kode Karyawan    |Nama Karyawan                 |Jenis Kelamin  |Jabatan       |Gaji Karyawan|");
			System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
			int i = 1;
			for(Karyawan karyawan : karyawan) {
				if(karyawan instanceof Manager) {
					Manager e = (Manager) karyawan;
					System.out.printf("|%4d|%17s|%30s|%15s|%14s|%13d|\n", i++, e.getKode(), e.getNama(), e.getGender(), e.getJabatan(), e.getGaji());
				}
				else if(karyawan instanceof Supervisor) {
					Supervisor e = (Supervisor) karyawan;
					System.out.printf("|%4d|%17s|%30s|%15s|%14s|%13d|\n", i++, e.getKode(), e.getNama(), e.getGender(), e.getJabatan(), e.getGaji());
				}
				else {
					Admin e = (Admin) karyawan;
					System.out.printf("|%4d|%17s|%30s|%15s|%14s|%13d|\n", i++, e.getKode(), e.getNama(), e.getGender(), e.getJabatan(), e.getGaji());
				}
			}
			System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
		}
		else {
			System.out.println("Tidak ada data");
		}
	}
	
	public void update() {
		String kode, nama, gender, jabatan;
		int gaji;
		if(!karyawan.isEmpty()) {
			read();
			int idx;
			do {
				try {
					System.out.print("Input nomor urutan karyawan yang ingin diupdate: ");
					idx = sc.nextInt();
					sc.nextLine();
				} catch(Exception e) {
					idx = -1;
					sc.nextLine();
					System.out.println("Tolong input angka");
					sc.nextLine();
				}
			} while(idx < 1 || idx > karyawan.size());
			
			do {
				System.out.print("Input nama karyawan [>= 3]: ");
				nama = sc.nextLine();
				if(nama.equals("0")) {
					nama = (karyawan.get(idx-1)).getNama();
				}
			} while (nama.length() < 3);
			
			do {
				System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
				gender = sc.nextLine();
				if(gender.equals("0")) {
					gender = (karyawan.get(idx-1)).getGender();
				}
			} while (!gender.equals("Laki-laki") && !gender.equals("Perempuan"));
			
			do {
				System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
				jabatan = sc.nextLine();
				if(jabatan.equals("0")) {
					jabatan = (karyawan.get(idx-1)).getJabatan();
				}
			} while (!jabatan.equals("Manager") && !jabatan.equals("Supervisor") && !jabatan.equals("Admin"));
			
			kode = (karyawan.get(idx-1)).getKode();
			
			if(jabatan.equals("Manager")) {
				gaji = 8000000;
				Manager m = new Manager(kode, nama, gender, jabatan, gaji);
				karyawan.set(idx-1, m);
			}
			else if(jabatan.equals("Supervisor")) {
				gaji = 6000000;
				Supervisor s = new Supervisor(kode, nama, gender, jabatan, gaji);
				karyawan.set(idx-1, s);
			}
			else {
				gaji = 4000000;
				Admin a = new Admin(kode, nama, gender, jabatan, gaji);
				karyawan.set(idx-1, a);
			}
			System.out.println("Berhasil mengupdate karyawan dengan id " + kode);
		}
		else {
			System.out.println("Tidak ada data yang bisa diupdate");
		}
	}
	
	public void delete() {
		if(!karyawan.isEmpty()) {
			read();
			int idx;
			do {
				try {
					System.out.print("Input nomor urutan karyawan yang ingin dihapus: ");
					idx = sc.nextInt();
					sc.nextLine();
				} catch(Exception e) {
					idx = -1;
					sc.nextLine();
					System.out.println("Tolong input angka");
					sc.nextLine();
				}
			} while(idx < 1 || idx > karyawan.size());
			
			String kode = (karyawan.get(idx-1)).getKode();
			
			System.out.println("Karyawan dengan kode " + kode + " berhasil dihapus");
			karyawan.remove(idx - 1);
		}
		else {
			System.out.println("Tidak ada data yang bisa dihapus");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new main();
	}

}
