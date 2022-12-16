package cihaz;

import java.io.File;

import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		
		File dosya = new File(args[0]);
		
		DosyaOkuma lexical = new DosyaOkuma();
		lexical.dosyaOku(dosya);

	}

}

		