package cihaz;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Dispatcher {


	File dosya = new File("giris.txt");
	
	DosyaOkuma d = new DosyaOkuma();
	public Dispatcher() throws IOException {
		
		for(int i = 0 ; i<9 ; i++) {
		Scanner scanner = new Scanner(d.RealTime.First().proses.getInputStream());
		while (scanner.hasNextLine()) {
		    System.out.println(scanner.nextLine());
		}
		d.RealTime.Delete();
		}
	}

}
