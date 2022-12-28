package cihaz;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class DosyaOkuma {
	public Kuyruk JobDispatch;
        Kuyruk RealTime  	=new Kuyruk();
        Kuyruk UserJob   	=new Kuyruk();
        Kuyruk Priority1 	=new Kuyruk();
        Kuyruk Priority2 	=new Kuyruk();
        Kuyruk Priority3 	=new Kuyruk();
	int timer=0;

    public void dosyaOku(File dosya) throws IOException {

        JobDispatch	=new Kuyruk();

        try {

            Scanner myReader = new Scanner(dosya);

            int prosessirasi = 0;

            while (myReader.hasNextLine()) {
            	
                String satir = myReader.nextLine();

                String sayilar[] = satir.split(",");

				Proses p = new Proses();
                p.varisZamani = Integer.valueOf(sayilar[0].trim());
                p.oncelik = Integer.valueOf(sayilar[1].trim());
                p.sure = Integer.valueOf(sayilar[2].trim());
                p.id = prosessirasi;

	    		List<String> params = java.util.Arrays.asList( "java", "-jar","program.jar",sayilar[0].trim(),Integer.toString(prosessirasi),sayilar[1].trim(),sayilar[2].trim(),"basladi");
	    		ProcessBuilder builder = new ProcessBuilder(params);
	    		builder.redirectError();
	    		p.proses = builder.start();
                prosessirasi++;
                JobDispatch.Add(p);
	    		//Scanner scanner = new Scanner(JobDispatch.First().proses.getInputStream());
	    		//while (scanner.hasNextLine()) {
	    		//    System.out.println(scanner.nextLine());
	    		//}
	    		
	    		int length = JobDispatch.Length();

	            for (int i = 0; i < length; i++) {
	                if(p.oncelik==0){
	                    RealTime.Add(JobDispatch.First());
						JobDispatch.Delete();
	                }
	                else {
	                    UserJob.Add(JobDispatch.First());
						JobDispatch.Delete();
	                }
	            }

	            int len = UserJob.Length();

	            for (int i = 0; i < len; i++) {
	                if(p.oncelik==1){
	                    Priority1.Add(UserJob.First());
	                    UserJob.Delete();
	                }else if(p.oncelik==2){
	                    Priority2.Add(UserJob.First());
	                    UserJob.Delete();
	                }else {
	                    Priority3.Add(UserJob.First());
	                    UserJob.Delete();
	                }
	            }

            }
            myReader.close();
			while(true){
				if(RealTime.doluMu() && RealTime.First().varisZamani<=timer){ //0 öncelikli
					while (RealTime.First().sure>0){ //çalışma süresi

						Scanner scanner = new Scanner(RealTime.First().proses.getInputStream());
						while (scanner.hasNextLine()) {
							System.out.println("a");
							System.out.println(RealTime.First().sure);
							RealTime.First().sure--;
							System.out.println(scanner.nextLine());
						}
						scanner.close();
						//System.out.println(RealTime.First().sure);
						//RealTime.First().sure--;
						timer++;
					}
					RealTime.Delete();
				}

				else if(Priority1.doluMu() &&Priority1.First().varisZamani<=timer){ //1 öncelikli
					Scanner scanner = new Scanner(Priority1.First().proses.getInputStream());
					while (scanner.hasNextLine()) {
						System.out.println(scanner.nextLine());
					}
					Priority1.Delete();
					timer++;
				}
				else if(Priority2.doluMu() && Priority2.First().varisZamani<=timer){ //2 öncelikli
					Scanner scanner = new Scanner(Priority2.First().proses.getInputStream());
					while (scanner.hasNextLine()) {
						System.out.println(scanner.nextLine());
					}
					Priority2.Delete();
					timer++;
				}
				else if(Priority3.doluMu() && Priority3.First().varisZamani<=timer){ //3 öncelikli
					Scanner scanner = new Scanner(Priority3.First().proses.getInputStream());
					while (scanner.hasNextLine()) {
						System.out.println(scanner.nextLine());
					}
					Priority3.Delete();
					timer++;
				}


			}








        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}