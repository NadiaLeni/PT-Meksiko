package ptMeksiko;

import java.util.Scanner;
import java.util.Vector;
import java.util.Random;

import Object.Admin;
import Object.Karyawan;
import Object.Manager;
import Object.Supervisor;

public class Main {
	
	Scanner sc = new Scanner(System.in);


	public class UserInputHandler {
		private Scanner scanner;
	
		public UserInputHandler() {
			scanner = new Scanner(System.in);
		}
	
		public int getMenuChoice() {
			int choice;
			try {
				System.out.println("1. Insert Data\n" +
						"2. View Data\n" +
						"3. Update Data\n" +
						"4. Delete Data\n" +
						"5. Exit\n");
				System.out.print("Choose >> ");
				choice = scanner.nextInt();
				scanner.nextLine();
			} catch (Exception e) {
				choice = -1;
				scanner.nextLine();
			}
			return choice;
		}
	
		public String nextLine() {
			return scanner.nextLine();
		}
	}
	

    private UserInputHandler userInputHandler;
    private EmployeeManager employeeManager;
	Vector<Karyawan> karyawan = new Vector<Karyawan>();
    private EmployeeOperations employeeOperations;

    public Main() {
        userInputHandler = new UserInputHandler();
        employeeManager = new EmployeeManager();
        employeeOperations = new EmployeeOperations(employeeManager);

        int choice;
        do {
            choice = userInputHandler.getMenuChoice();
            switch (choice) {
                case 1:
                    employeeOperations.addEmployee();
                    System.out.println("ENTER to return");
                    userInputHandler.nextLine();
                    break;
                case 2:
                    employeeManager.displayKaryawan();
                    System.out.println("ENTER to return");
                    userInputHandler.nextLine();
                    break;
                case 3:
                    employeeOperations.updateEmployee();
                    System.out.println("ENTER to return");
                    userInputHandler.nextLine();
                    break;
                case 4:
                    employeeOperations.deleteEmployee();
                    System.out.println("ENTER to return");
                    userInputHandler.nextLine();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Tolong input angka sesuai menu.");
                    userInputHandler.nextLine();
            }
        } while (choice != 5);

        System.out.println("Thank you...");
    }


public class EmployeeManager {
    private Vector<Karyawan> karyawan;
    private int employeeCounter;

    public EmployeeManager() {
        karyawan = new Vector<Karyawan>();
        employeeCounter = 0;
    }

    public void addKaryawan(Karyawan karyawan) {
        this.karyawan.add(karyawan);
        employeeCounter++;
    }
    
    public void updateKaryawan(int index, Karyawan karyawan) {
        this.karyawan.set(index, karyawan);
    }

    public void deleteKaryawan(int index) {
        this.karyawan.remove(index);
        employeeCounter--;
    }
    
    public void displayKaryawan() {
        if (!karyawan.isEmpty()) {
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
    }


public class EmployeeOperations {
    private EmployeeManager employeeManager;
	private int i;

    public EmployeeOperations(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    public void addEmployee() {
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
    
    public void updateEmployee() {
        String kode, nama, gender, jabatan;
		int gaji;
		if(!karyawan.isEmpty()) {
			employeeManager.displayKaryawan();
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
    
    public void deleteEmployee() {
		if(!karyawan.isEmpty()) {
			employeeManager.displayKaryawan();
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
    }

    public static void main(String[] args) {
        new Main();
    }

}