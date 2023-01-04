package cihaz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Dispatcher {
	int timer=0;
	File dosya = new File("giris.txt");
	
	DosyaOkuma d = new DosyaOkuma();
	
	public void Gorevlendirici() throws IOException{
	d.dosyaOku(dosya);
			while(true){
				String durum = " ";
				if(d.RealTime.doluMu() && d.RealTime.First().varisZamani<=timer){ //0 Oncelikli
					for(int i=d.RealTime.First().sure;i>=0;i--){
						System.out.print(d.RealTime.First().renk + timer + ". sn");//"\u001B[0m"
						Scanner scanner = new Scanner(d.RealTime.First().proses.getInputStream());
						while (scanner.hasNextLine()) {
							System.out.println(scanner.nextLine());
						}
						d.RealTime.First().sure--;
						if(d.RealTime.First().sure == 0) durum = "sonlandi";
						else durum = "yurutuluyor";
						scanner.close();
						List<String> params2 = java.util.Arrays.asList( "java", "-jar","program.jar",Integer.toString(d.RealTime.First().varisZamani),Integer.toString(d.RealTime.First().id),Integer.toString(d.RealTime.First().oncelik),Integer.toString(d.RealTime.First().sure),durum);
						ProcessBuilder builder2 = new ProcessBuilder(params2);
						builder2.redirectError();
						d.RealTime.First().proses = builder2.start();

						timer++;
						System.out.print("\u001B[0m");
					}
					d.RealTime.Delete();
				}
				else if(d.Priority1.doluMu() &&d.Priority1.First().varisZamani<=timer){ //1 Oncelikli
					if(d.Priority1.First().sure==-1) break;

					System.out.print(d.Priority1.First().renk +timer + ". sn");
					Scanner scanner = new Scanner(d.Priority1.First().proses.getInputStream());
					while (scanner.hasNextLine()) {
						System.out.println(scanner.nextLine());
					}
					d.Priority1.First().sure--;
					if(d.Priority1.First().sure == 0) durum = "sonlandi";
					else durum = "yurutuluyor";
					scanner.close();
					List<String> params2 = java.util.Arrays.asList( "java", "-jar","program.jar",Integer.toString(d.Priority1.First().varisZamani),Integer.toString(d.Priority1.First().id),Integer.toString(d.Priority1.First().oncelik+1),Integer.toString(d.Priority1.First().sure),durum);
					ProcessBuilder builder2 = new ProcessBuilder(params2);
					builder2.redirectError();
					d.Priority1.First().proses = builder2.start();
					d.Priority2.Add(d.Priority1.First());
					d.Priority1.Delete();
					timer++;
					System.out.print("\u001B[0m");
				}
				else if(d.Priority2.doluMu() && d.Priority2.First().varisZamani<=timer){ //2 Oncelikli
					if(d.Priority2.First().sure==-1) break;

					System.out.print(d.Priority2.First().renk + timer + ". sn");
					Scanner scanner = new Scanner(d.Priority2.First().proses.getInputStream());
					while (scanner.hasNextLine()) {
						System.out.println(scanner.nextLine());
					}
					d.Priority2.First().sure--;
					if(d.Priority2.First().sure == 0) durum = "sonlandi";
					else durum = "yurutuluyor";
					scanner.close();

					List<String> params2 = java.util.Arrays.asList( "java", "-jar","program.jar",Integer.toString(d.Priority2.First().varisZamani),Integer.toString(d.Priority2.First().id),Integer.toString(d.Priority2.First().oncelik+1),Integer.toString(d.Priority2.First().sure),durum);
					ProcessBuilder builder2 = new ProcessBuilder(params2);
					builder2.redirectError();
					d.Priority2.First().proses = builder2.start();
					d.Priority3.Add(d.Priority2.First());
					d.Priority2.Delete();
					timer++;
				}
				else if(d.Priority3.doluMu() && d.Priority3.First().varisZamani<=timer){ //3 Oncelikli

					if(d.Priority3.First().sure==-1) break;
					System.out.print(d.Priority3.First().renk + timer + ". sn");
					
					Scanner scanner = new Scanner(d.Priority3.First().proses.getInputStream());
					while (scanner.hasNextLine()) {
						System.out.println(scanner.nextLine());
					}
					d.Priority3.First().sure--;
					if(d.Priority3.First().sure == 0) durum = "sonlandi";
					else durum = "yurutuluyor";
					scanner.close();
					List<String> params2 = java.util.Arrays.asList( "java", "-jar","program.jar",Integer.toString(d.Priority3.First().varisZamani),Integer.toString(d.Priority3.First().id),Integer.toString(d.Priority3.First().oncelik),Integer.toString(d.Priority3.First().sure),durum);

					ProcessBuilder builder2 = new ProcessBuilder(params2);
					builder2.redirectError();
					d.Priority3.First().proses = builder2.start();
					d.Priority3.Add(d.Priority3.First());
					d.Priority3.Delete();
					timer++;
				}
			}




	}






}
