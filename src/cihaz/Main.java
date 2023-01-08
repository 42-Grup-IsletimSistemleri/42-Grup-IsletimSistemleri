package cihaz;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class Main {

	public static void main(String[] args) throws IOException {

		System.out.println("Okunacak dosya adini giriniz...(ornek: giris.txt)");
		
		Dispatcher dispatcher = new Dispatcher(); //dispatcher sinifindan nesne olusturur.
		dispatcher.Gorevlendirici(); // bu nesnenin gorevlendirici metodu cagirilir.
	}
}