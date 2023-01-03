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
						for(int i=RealTime.First().sure;i>=0;i--){
							Scanner scanner = new Scanner(RealTime.First().proses.getInputStream());
							while (scanner.hasNextLine()) {
								System.out.println(scanner.nextLine());
							}
							RealTime.First().sure--;
							scanner.close();
							List<String> params2 = java.util.Arrays.asList( "java", "-jar","program.jar",Integer.toString(RealTime.First().varisZamani),Integer.toString(RealTime.First().id),Integer.toString(RealTime.First().oncelik),Integer.toString(RealTime.First().sure),"yurutuluyor");
							ProcessBuilder builder2 = new ProcessBuilder(params2);
							builder2.redirectError();
							RealTime.First().proses = builder2.start();

							timer++;
						}
					RealTime.Delete();
				}
				else if(Priority1.doluMu() &&Priority1.First().varisZamani<=timer){ //1 öncelikli
					if(Priority1.First().sure==-1) break;
					Scanner scanner = new Scanner(Priority1.First().proses.getInputStream());
					while (scanner.hasNextLine()) {
						System.out.println(scanner.nextLine());
					}
					Priority1.First().sure--;
					scanner.close();
					List<String> params2 = java.util.Arrays.asList( "java", "-jar","program.jar",Integer.toString(Priority1.First().varisZamani),Integer.toString(Priority1.First().id),Integer.toString(Priority1.First().oncelik+1),Integer.toString(Priority1.First().sure),"yurutuluyor");
					ProcessBuilder builder2 = new ProcessBuilder(params2);
					builder2.redirectError();
					Priority1.First().proses = builder2.start();
					Priority2.Add(Priority1.First());
					Priority1.Delete();
					timer++;
				}
				else if(Priority2.doluMu() && Priority2.First().varisZamani<=timer){ //2 öncelikli
					if(Priority2.First().sure==-1) break;
					Scanner scanner = new Scanner(Priority2.First().proses.getInputStream());
					while (scanner.hasNextLine()) {
						System.out.println(scanner.nextLine());
					}
					Priority2.First().sure--;
					scanner.close();

					List<String> params2 = java.util.Arrays.asList( "java", "-jar","program.jar",Integer.toString(Priority2.First().varisZamani),Integer.toString(Priority2.First().id),Integer.toString(Priority2.First().oncelik+1),Integer.toString(Priority2.First().sure),"yurutuluyor");
					ProcessBuilder builder2 = new ProcessBuilder(params2);
					builder2.redirectError();
					Priority2.First().proses = builder2.start();
					Priority3.Add(Priority2.First());
					Priority2.Delete();
					timer++;
				}
				else if(Priority3.doluMu() && Priority3.First().varisZamani<=timer){ //3 öncelikli

					if(Priority3.First().sure==-1) break;
					//System.out.println("b");
					Scanner scanner = new Scanner(Priority3.First().proses.getInputStream());
					while (scanner.hasNextLine()) {
						System.out.println(scanner.nextLine());
					}
					Priority3.First().sure--;
					scanner.close();
					List<String> params2 = java.util.Arrays.asList( "java", "-jar","program.jar",Integer.toString(Priority3.First().varisZamani),Integer.toString(Priority3.First().id),Integer.toString(Priority3.First().oncelik),Integer.toString(Priority3.First().sure),"yurutuluyor");

					ProcessBuilder builder2 = new ProcessBuilder(params2);
					builder2.redirectError();
					Priority3.First().proses = builder2.start();
					Priority3.Add(Priority3.First());
					Priority3.Delete();
					timer++;
				}
				//System.out.println("a");
			}

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}