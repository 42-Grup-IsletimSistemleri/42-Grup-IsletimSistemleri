package cihaz;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class Main {

	public static void main(String[] args) throws IOException {
		
		File dosya = new File("giris.txt");
		
		DosyaOkuma d = new DosyaOkuma();
		d.dosyaOku(dosya);
		
		/*
		Renk r = new Renk();
		System.out.println("\u001B[31m" + "Hello World in red!");
		*/
		
		
	}

}

		