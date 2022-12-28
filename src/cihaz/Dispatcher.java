package cihaz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Dispatcher {
	int timer=0;
	DosyaOkuma d;

	Dispatcher(){
		d=new DosyaOkuma();
	}
	public void Gorevlendirici(){
			while(timer<24){
				if(d.RealTime.First().varisZamani<=timer){ //0 öncelikli
					Scanner scanner = new Scanner(d.RealTime.First().proses.getInputStream());
					while (scanner.hasNextLine()) {
					    System.out.println(scanner.nextLine());
					}
				}
				else if(d.Priority1.First().varisZamani<=timer){ //1 öncelikli
					Scanner scanner = new Scanner(d.Priority1.First().proses.getInputStream());
					while (scanner.hasNextLine()) {
						System.out.println(scanner.nextLine());
					}
				}
				else if(d.Priority2.First().varisZamani<=timer){ //2 öncelikli
					Scanner scanner = new Scanner(d.Priority2.First().proses.getInputStream());
					while (scanner.hasNextLine()) {
						System.out.println(scanner.nextLine());
					}
				}
				else if(d.Priority3.First().varisZamani<=timer){ //3 öncelikli
					Scanner scanner = new Scanner(d.Priority3.First().proses.getInputStream());
					while (scanner.hasNextLine()) {
						System.out.println(scanner.nextLine());
					}
				}
				timer++;
			}




	}






}
