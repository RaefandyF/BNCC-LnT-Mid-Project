import java.util.Scanner;
import java.util.Vector;
import java.util.jar.Attributes.Name;
import java.util.Random;

public class Main {
	static Scanner scan = new Scanner(System.in);
	static Random random = new Random();
	static Vector<Position> listPosition = new Vector<>();
	static Vector<Position> listPosition2 = new Vector<>();
	static Vector<Position> listPosition3 = new Vector<>();
	static Vector<Position> allPosition = new Vector<>();
	static Vector<Position> updatePosition = new Vector<>();
	private static String jabatan,jabatan1,jabatan2,jabatan3,kode,kode1,kode2,kode3,nama,
						  nama1,nama2,nama3,kelamin1,kelamin2,kelamin3,updateNama,updateKelamin,updateJabatan,updateKode;
	private static int gaji1,gaji2,gaji3,nomor;
	private static double updateGaji;
	
	public void dataKaryawan() {
		do {
		int min = 48;
		int max = 57;
		int range = max-min+1;
		int min2 = 65 ;
		int max2 = 90;
		int range2 = max2-min2+1;
		
		char x1 = (char)(Math.random()*range + min);
		char x2 = (char)(Math.random()*range + min);
		char x3 = (char)(Math.random()*range + min);
		char x4 = (char)(Math.random()*range + min);
		char m1 = (char)(Math.random()*range2 + min2);
		char m2 = (char)(Math.random()*range2 + min2);
		kode = m1+""+m2+"-"+x1+x2+x3+x4;
		} while (listPosition.contains(kode) || listPosition2.contains(kode) || listPosition3.contains(kode));
		String kelamin;
		do {
		System.out.print("Input nama karyawan: ");
		nama = scan.nextLine();
		String upperCase = " ";
		Scanner scaner = new Scanner(nama);
		if (-1 == nama.indexOf(" ")) 
			nama = nama.substring(0, 1).toUpperCase() + nama.substring(1);
		else {
			while (scaner.hasNext()) {
			String word = scaner.next();
			upperCase += Character.toUpperCase(word.charAt(0)) + word.substring(1) + " ";		
			}
			nama = upperCase.trim();
			}
		} while(nama.length() < 3);
		do {
		System.out.print("Input jenis kelamin [Laki-Laki/Perempuan]: ");
		kelamin = scan.nextLine();
		} while(!kelamin.equals("Laki-Laki") && !kelamin.equals("Perempuan"));
		do {
		System.out.print("Input jabatan [Manager/Supervisor/Admin]: ");
		jabatan = scan.nextLine();
		} while(!jabatan.equals("Manager") && !jabatan.equals("Supervisor") && !jabatan.equals("Admin"));
		if (jabatan.equals("Manager")) {
			nama1 = nama;
			kelamin1 = kelamin;
			jabatan1 = jabatan;
			gaji1 = 8000000;
			kode1 = kode;
			System.out.println("Berhasil menambahkan karyawan dengan id " + kode1);
		}
		else if (jabatan.equals("Supervisor")) {
			nama2 = nama;
			kelamin2 = kelamin;
			jabatan2 = jabatan;
			gaji2 = 6000000;
			kode2 = kode;
			System.out.println("Berhasil menambahkan karyawan dengan id " + kode2);
			System.out.println("");
		}
		else if (jabatan.equals("Admin")) {
			nama3 = nama;
			kelamin3 = kelamin;
			jabatan3 = jabatan;
			gaji3 = 4000000;
			kode3 = kode;
			System.out.println("Berhasil menambahkan karyawan dengan id " + kode3);
		}
		
		Position Manager = new Position(jabatan1, gaji1, kode1, nama1,kelamin1);
		Position Supervisor = new Position(jabatan2, gaji2, kode2,nama2,kelamin2);
		Position Admin = new Position(jabatan3, gaji3, kode3,nama3,kelamin3); 
		
		//System.out.println(listPosition.size());
		if (jabatan.equals("Manager")) {
			listPosition.add(Manager);
		}
		else if (jabatan.equals("Supervisor")) {
		listPosition2.add(Supervisor);
		}
		else if (jabatan.equals("Admin")) {
		listPosition3.add(Admin);
		}
		penambahanGaji();
		fillData();
		System.out.println("\nENTER to return\n");		
	}
	
	public void fillData() {
	allPosition.removeAllElements();
	if (!listPosition.isEmpty()) {
		for (int i = 0; i < listPosition.size(); i++) {
			allPosition.add(listPosition.get(i));
		}
	}
	if (!listPosition2.isEmpty()) {
		for (int i = 0; i < listPosition2.size(); i++) {
			allPosition.add(listPosition2.get(i));
		}
	}
	if (!listPosition3.isEmpty()) {
		for (int i = 0; i < listPosition3.size(); i++) {
			allPosition.add(listPosition3.get(i));
		}
	}
	}
	public void viewData(String spasi) {	
			fillData();
			sortAscending();
			System.out.println("==============================================================================================");
			System.out.printf("|%-2s |%-14s |%-27s |%-13s |%-12s|%-15s|\n","No","Kode Karyawan","Nama Karyawan","Jenis Kelamin","Jabatan","Gaji Karyawan");
			System.out.println("==============================================================================================");
			for (int i = 0; i < allPosition.size(); i++) {
				System.out.printf("|%-2s |%-14s |%-27s |%-13s |%-12s|%-15.0f|\n"
								,(i+1),allPosition.get(i).getKode(),allPosition.get(i).getNama(),allPosition.get(i).getKelamin(),allPosition.get(i).getJabatan(),allPosition.get(i).getGaji()); 
			}
			System.out.println("==============================================================================================");
			System.out.print(spasi);

	}

		
	public void sortAscending() {	
        for (int i = 0; i < allPosition.size(); i++) {
            for (int j = 0; j < allPosition.size()-1; j++) {
                if (allPosition.get(j).getNama().compareTo(allPosition.get(j+1).getNama()) > 0) {
                    Position temp = allPosition.get(j);
                    allPosition.set(j, allPosition.get(j+1));
                    allPosition.set(j+1,  temp);
                }
            }
        }
		}
	
	public void updateData() {
		viewData("");
		String verif;
		do {
		System.out.print("Input nomor urutan karyawan yang ingin diupdate: ");
		nomor = scan.nextInt();scan.nextLine();
		} while (nomor < 1);
		System.out.print("Input Kode Karyawan: ");
		updateKode = scan.nextLine();
		if (updateKode.equals("0"))
			updateKode = allPosition.get(nomor-1).getKode();
		do {
		System.out.print("Input nama karyawan: ");
		updateNama = scan.nextLine();
		if (updateNama.equals("0"))
			updateNama = allPosition.get(nomor-1).getNama();
		String upperCase = " ";
		Scanner scaner = new Scanner(updateNama);
		if (-1 == updateNama.indexOf(" ")) 
			updateNama = updateNama.substring(0, 1).toUpperCase() + updateNama.substring(1);
		else {
			while (scaner.hasNext()) {
			String word = scaner.next();
			upperCase += Character.toUpperCase(word.charAt(0)) + word.substring(1) + " ";		
			}
			updateNama = upperCase.trim();
			}
		} while(updateNama.length() < 3);
		do {
		System.out.print("Input jenis kelamin [Laki-Laki/Perempuan]: ");
		updateKelamin = scan.nextLine();
		if (updateKelamin.equals("0"))
			updateKelamin = allPosition.get(nomor-1).getKelamin();
		} while(!updateKelamin.equals("Laki-Laki") && !updateKelamin.equals("Perempuan"));
		do {
		System.out.print("Input jabatan [Manager/Supervisor/Admin]: ");
		updateJabatan = scan.nextLine();
		verif = updateJabatan;
		if (updateJabatan.equals("0"))
			updateJabatan = allPosition.get(nomor-1).getJabatan();
		} while(!updateJabatan.equals("Manager") && !updateJabatan.equals("Supervisor") && !updateJabatan.equals("Admin"));
		System.out.print("Input Gaji Karyawan: ");
		updateGaji = scan.nextDouble();scan.nextLine();
		if (updateGaji == 0)
			updateGaji = allPosition.get(nomor-1).getGaji();
		Position update = new Position(updateJabatan, updateGaji, updateKode, updateNama, updateKelamin);
		updatePosition.add(update);
		updates(listPosition,listPosition2,listPosition3,"Manager","Supervisor","Admin");
		updates(listPosition2,listPosition,listPosition3,"Supervisor","Manager","Admin");
		updates(listPosition3,listPosition,listPosition2,"Admin","Manager","Supervisor");
	
		if (!verif.equals("0")) {
			updatePenambahanGaji();
		}
				System.out.println("Berhasil mengupdate karyawan dengan id " + allPosition.get(nomor-1).getKode());
				System.out.println("ENTER to return\n");		
	}
	
	public void updates(Vector data, Vector data2, Vector data3, String posisi, String posisi2, String posisi3) {
		Position update = new Position(updateJabatan, updateGaji, updateKode, updateNama, updateKelamin);
	if (allPosition.get(nomor-1).getJabatan().equals(posisi) && updateJabatan.equals(posisi)) {
		data.set(data.indexOf(allPosition.get(nomor-1)),update);
	}
	else if (allPosition.get(nomor-1).getJabatan().equals(posisi) && updateJabatan.equals(posisi2)) {
		data.remove(data.indexOf(allPosition.get(nomor-1)));
		data2.add(update);
	}
	else if (allPosition.get(nomor-1).getJabatan().equals(posisi) && updateJabatan.equals(posisi3)) {
		data.remove(data.indexOf(allPosition.get(nomor-1)));
		data3.add(update);
	}
	}
	
	public void deleteData() {
		viewData("");
		int nomorDelete;
		do {
		System.out.print("Input nomor urutan karyawan yang ingin dihapus: ");
		nomorDelete = scan.nextInt();scan.nextLine();
		} while (nomorDelete < 1 || nomorDelete > allPosition.size());
		if (allPosition.get(nomorDelete-1).getJabatan().equals("Manager"))
			listPosition.remove(listPosition.indexOf(allPosition.get(nomorDelete-1)));
		else if (allPosition.get(nomorDelete-1).getJabatan().equals("Supervisor"))
				listPosition2.remove(listPosition2.indexOf(allPosition.get(nomorDelete-1)));
		else if (allPosition.get(nomorDelete-1).getJabatan().equals("Admin"))
				listPosition3.remove(listPosition3.indexOf(allPosition.get(nomorDelete-1)));
		System.out.println("Karyawan dengan kode " + allPosition.get(nomorDelete-1).getKode() + " berhasil dihapus");
		System.out.println("ENTER to return\n");
	}
	public void penambahanGaji() {
		if (jabatan.equals("Manager")) {
		if (listPosition.size()%3 == 1 && listPosition.size() > 1) {
			System.out.print("Bonus Sebesar 10% telah diberikan kepada karyawan dengan id ");
			for (int i = 0; i < listPosition.size()-1; i++) {
				Position temp = new Position (jabatan1, listPosition.get(i).getGaji()
								* 0.1 + listPosition.get(i).getGaji(), listPosition.get(i).getKode(), 
								listPosition.get(i).getNama(),listPosition.get(i).getKelamin());
				listPosition.set(i, temp);
				System.out.print(listPosition.get(i).getKode());
				if (i < listPosition.size()-2)
					System.out.print(", ");
				}
		}
		}
		else if (jabatan.equals("Supervisor")) {
		if (listPosition2.size()%3 == 1 && listPosition2.size() > 1) {
			System.out.print("Bonus Sebesar 7,5% telah diberikan kepada karyawan dengan id ");
			for (int i = 0; i < listPosition2.size()-1; i++) {
					Position temp = new Position (jabatan2, listPosition2.get(i).getGaji()
									* 0.075 + listPosition2.get(i).getGaji(), listPosition2.get(i).getKode(), 
									listPosition2.get(i).getNama(),listPosition2.get(i).getKelamin());
					listPosition2.set(i, temp);
					System.out.print(listPosition2.get(i).getKode());
					if (i < listPosition2.size()-2)
						System.out.print(", ");
					}
		}
		}
		else if (jabatan.equals("Admin")) {
		if (listPosition3.size()%3 == 1 && listPosition3.size() > 1) {
			System.out.print("Bonus Sebesar 5% telah diberikan kepada karyawan dengan id ");
			for (int i = 0; i < listPosition3.size()-1; i++) {
					Position temp = new Position (jabatan3, listPosition3.get(i).getGaji()
									* 0.05 + listPosition3.get(i).getGaji(), listPosition3.get(i).getKode(), 
									listPosition3.get(i).getNama(),listPosition3.get(i).getKelamin());
					listPosition3.set(i, temp);
					System.out.print(listPosition3.get(i).getKode());
					if (i < listPosition3.size()-2)
						System.out.print(", ");
					}
		}
		}
	
	}
	
	public void updatePenambahanGaji() {
		if (updateJabatan.equals("Manager")) {
		if (listPosition.size()%3 == 1 && listPosition.size() > 1) {
			System.out.print("Bonus Sebesar 10% telah diberikan kepada karyawan dengan id ");
			for (int i = 0; i < listPosition.size()-1; i++) {
				Position temp = new Position (jabatan1, listPosition.get(i).getGaji()
								* 0.1 + listPosition.get(i).getGaji(), listPosition.get(i).getKode(), 
								listPosition.get(i).getNama(),listPosition.get(i).getKelamin());
				listPosition.set(i, temp);
				System.out.print(listPosition.get(i).getKode());
				if (i < listPosition.size()-2)
					System.out.print(", ");
				System.out.println("");
				}
		}
		}
		else if (updateJabatan.equals("Supervisor")) {
		if (listPosition2.size()%3 == 1 && listPosition2.size() > 1) {
			System.out.print("Bonus Sebesar 7,5% telah diberikan kepada karyawan dengan id ");
			for (int i = 0; i < listPosition2.size()-1; i++) {
					Position temp = new Position (jabatan2, listPosition2.get(i).getGaji()
									* 0.075 + listPosition2.get(i).getGaji(), listPosition2.get(i).getKode(), 
									listPosition2.get(i).getNama(),listPosition2.get(i).getKelamin());
					listPosition2.set(i, temp);
					System.out.print(listPosition2.get(i).getKode());
					if (i < listPosition2.size()-2)
						System.out.print(", ");
					System.out.println("");
					}
		}
		}
		else if (updateJabatan.equals("Admin")) {
		if (listPosition3.size()%3 == 1 && listPosition3.size() > 1) {
			System.out.print("Bonus Sebesar 5% telah diberikan kepada karyawan dengan id ");
			for (int i = 0; i < listPosition3.size()-1; i++) {
					Position temp = new Position (jabatan3, listPosition3.get(i).getGaji()
									* 0.05 + listPosition3.get(i).getGaji(), listPosition3.get(i).getKode(), 
									listPosition3.get(i).getNama(),listPosition3.get(i).getKelamin());
					listPosition3.set(i, temp);
					System.out.print(listPosition3.get(i).getKode());
					if (i < listPosition3.size()-2)
						System.out.print(", ");
					System.out.println("");
					}
		}
		}
	
	}
	
	public Main() {
		int input;
		do {
			System.out.println("PT Musang");
			System.out.println("=====================");
			System.out.println("1. Insert data karyawan");
			System.out.println("2. View data karyawan");
			System.out.println("3. Update data karyawan");
			System.out.println("4. Delete data karyawan");
			System.out.println("5. Exit");
			System.out.print(">> ");
			input = scan.nextInt();scan.nextLine();
		
		switch (input) {
		case 1:
			dataKaryawan();
			break;

		case 2:
			if (allPosition.isEmpty()) {
				System.out.println("There is no data\n");
				continue;
			}				
			viewData("\n");
			break;
			
		case 3:
			if (allPosition.isEmpty()) {
				System.out.println("There is no data\n");
				continue;
			}		
			updateData();
			break;
		case 4:
			if (allPosition.isEmpty()) {
				System.out.println("There is no data\n");
				continue;
			}
			deleteData();
			break;
		case 5:
			break;
		}
		} while(input != 5);
	}

	public static void main(String[] args) {
		new Main();

	}

}
