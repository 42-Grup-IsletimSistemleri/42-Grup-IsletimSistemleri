package cihaz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Dispatcher {
    Kuyruk RealTime  =new Kuyruk();
    Kuyruk UserJob   =new Kuyruk();
    Kuyruk Priority1 =new Kuyruk();
    Kuyruk Priority2 =new Kuyruk();
    Kuyruk Priority3 =new Kuyruk();
	int timer = 0;//gercek zamani kullanmak yerine kendi olusturdugumuz integer bir degeri kullaniyoruz
	Scanner sc = new Scanner(System.in);
	String text = sc.nextLine();
	File dosya = new File(text);
	
	DosyaOkuma d = new DosyaOkuma();//dosya okuma sinifindan nesne olusturulur.
	
	public void Gorevlendirici() throws IOException{
	d.dosyaOku(dosya);// olusturulan nesnenin dosya okuma metodu cagirilir.
		while(true){//tum proseslerin çalismasi tamamlanana kadar dongu calisir.
			String durum = " ";
			
            while (d.JobDispatch.doluMu() && d.JobDispatch.First().varisZamani==timer) {//JobDispatch kuyrugundaki eleman sayisi kadar dongu doner. Variz zamani timere esit olanlari onceligine göre ilgili kuyruga ekler ve kuyruktan siler.
                if(d.JobDispatch.First().oncelik==0){
                	RealTime.Add(d.JobDispatch.First());
					d.JobDispatch.Delete();
                }
                else {
                    UserJob.Add(d.JobDispatch.First());
					d.JobDispatch.Delete();
                }
            }
            
            while (UserJob.doluMu() && UserJob.First().varisZamani==timer) {//UserJob kuyrugundaki prosesleri onceligine göre ilgili kuyruga ekler ve kuyruktan siler
                if(UserJob.First().oncelik==1){
                	Priority1.Add(UserJob.First());
                	UserJob.Delete();
                }else if(UserJob.First().oncelik==2){
                	Priority2.Add(UserJob.First());
                	UserJob.Delete();
                }else {
                	Priority3.Add(UserJob.First());
                	UserJob.Delete();
                }
            }

			if(RealTime.doluMu() && RealTime.First().varisZamani<=timer){ //RealTime kuyrugunda proses var ise ilk önce bu proses calistirilir.
					System.out.print(RealTime.First().renk + timer + ". sn");
					Scanner scanner = new Scanner(RealTime.First().proses.getInputStream());
					while (scanner.hasNextLine()) {
						System.out.println(scanner.nextLine());
					}
					RealTime.First().sure--;
					if(RealTime.First().sure == 0) durum = "sonlandi";
					else durum = "yurutuluyor";
					scanner.close();
					List<String> params2 = java.util.Arrays.asList( "java", "-jar","program.jar",Integer.toString(RealTime.First().varisZamani),Integer.toString(RealTime.First().id),Integer.toString(RealTime.First().oncelik),Integer.toString(RealTime.First().sure),durum);
					ProcessBuilder builder2 = new ProcessBuilder(params2);
					builder2.redirectError();
					RealTime.First().proses = builder2.start();
					System.out.print("\u001B[0m");
				if(RealTime.First().sure == -1) {
					RealTime.Delete();
					continue;
				}
			}
			else if(Priority1.doluMu() && Priority1.First().varisZamani<=timer){ //Priority1 kuyrugunda proses varsa ve RealTime kuyrugu bos ise bu kuyruktaki prosesler kuantum degerine gore (1) islenir ve onceligi bir dusurulerek alt kuyruga eklenir.
				if(Priority1.First().sure==-1) break;//prosesin calisma suresi -1 oldugunda proses sonladirilir.

				System.out.print(Priority1.First().renk +timer + ". sn");
				Scanner scanner = new Scanner(Priority1.First().proses.getInputStream());//calisan proses okunur.
				while (scanner.hasNextLine()) {
					System.out.println(scanner.nextLine());
				}
				if(Priority1.First().sure!=0) {//proses askiya alinir ise ekrana yazdirilir.
					System.out.println((timer+1) + ". sn Proses Askýda      (id:"+String.format("%04d",Priority1.First().id)+" Oncelik: "+Priority1.First().oncelik+" Kalan Sure: "+Priority1.First().sure+")") ;
				}
				scanner.close();
				Priority1.First().sure--; //calistirilan prosesin calisma suresi bir azaltilir.
				if(Priority1.First().sure == 0) durum = "sonlandi";
				else durum = "yurutuluyor";
				List<String> params2 = java.util.Arrays.asList( "java", "-jar","program.jar",Integer.toString(Priority1.First().varisZamani),Integer.toString(Priority1.First().id),Integer.toString(Priority1.First().oncelik+1),Integer.toString(Priority1.First().sure),durum);
				ProcessBuilder builder2 = new ProcessBuilder(params2);
				builder2.redirectError();
				Priority1.First().proses = builder2.start();
				Priority1.First().oncelik++;
				Priority2.Add(Priority1.First());
				Priority1.Delete();
				System.out.print("\u001B[0m");//consolun rengini resetler.
			}
			else if(Priority2.doluMu() && Priority2.First().varisZamani<=timer){ //Priority2 kuyrugunda proses varsa ve priority1 kuyrugu bos ise bu kuyruktaki prosesler kuantum degerine gore (1) islenir ve onceligi bir dusurulerek alt kuyruga eklenir.
				if(Priority2.First().sure==-1) break;//prosesin calisma suresi -1 oldugunda proses sonladirilir.

				System.out.print(Priority2.First().renk + timer + ". sn");
				Scanner scanner = new Scanner(Priority2.First().proses.getInputStream());//calisan proses okunur.
				while (scanner.hasNextLine()) {
					System.out.println(scanner.nextLine());
				}
				if(Priority2.First().sure!=0) {//proses askiya alinir ise ekrana yazdirilir.
					System.out.println((timer+1) + ". sn Proses Askýda      (id:"+String.format("%04d",Priority2.First().id)+" Oncelik: "+Priority2.First().oncelik+" Kalan Sure: "+Priority2.First().sure+")") ;
				}
				scanner.close();

				Priority2.First().sure--;//calistirilan prosesin calisma suresi bir azaltilir.
				if(Priority2.First().sure == 0) durum = "sonlandi";
				else durum = "yurutuluyor";
				List<String> params2 = java.util.Arrays.asList( "java", "-jar","program.jar",Integer.toString(Priority2.First().varisZamani),Integer.toString(Priority2.First().id),Integer.toString(Priority2.First().oncelik+1),Integer.toString(Priority2.First().sure),durum);
				ProcessBuilder builder2 = new ProcessBuilder(params2);
				builder2.redirectError();
				Priority2.First().proses = builder2.start();
				Priority2.First().oncelik++;
				Priority3.Add(Priority2.First());
				Priority2.Delete();
				System.out.print("\u001B[0m");//consolun rengini resetler.
			}
			else if(Priority3.doluMu() && Priority3.First().varisZamani<=timer){ //Priority3 kuyrugunda proses varsa ve priority2 kuyrugu bos ise bu kuyruktaki prosesler kuantum degerine gore (1) islenir ve ayni kuyrugun sonuna eklenir.
				if(Priority3.First().sure==-1) {//prosesin calisma suresi -1 oldugunda proses sonladirilir.
					Priority3.Delete();
					continue;
				}
				System.out.print(Priority3.First().renk + timer + ". sn");
				
				Scanner scanner = new Scanner(Priority3.First().proses.getInputStream());
				while (scanner.hasNextLine()) {
					System.out.println(scanner.nextLine());
				}
				if(Priority3.First().sure!=0) {//proses askiya alinir ise ekrana yazdirilir.
					System.out.println((timer+1) + ". sn Proses Askýda      (id:"+String.format("%04d",Priority3.First().id)+" Oncelik: "+Priority3.First().oncelik+" Kalan Sure: "+Priority3.First().sure+")") ;
				}
				scanner.close(); 
				Priority3.First().sure--;//calistirilan prosesin calisma suresi bir azaltilir.
				if(Priority3.First().sure == 0) durum = "sonlandi";
				else durum = "yurutuluyor";
				List<String> params2 = java.util.Arrays.asList( "java", "-jar","program.jar",Integer.toString(Priority3.First().varisZamani),Integer.toString(Priority3.First().id),Integer.toString(Priority3.First().oncelik),Integer.toString(Priority3.First().sure),durum);

				ProcessBuilder builder2 = new ProcessBuilder(params2);
				builder2.redirectError();
				Priority3.First().proses = builder2.start();
				Priority3.Add(Priority3.First());
				Priority3.Delete();
				System.out.print("\u001B[0m");//consolun rengini resetler.
			}
			else {
				System.out.println("\nTum prosesler calistirildi.");
				break;
			}
			timer++;
		}
	}
}
