package cihaz;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class DosyaOkuma {
    public void dosyaOku(File dosya) throws IOException {

        Kuyruk JobDispatch	=new Kuyruk();
        Kuyruk RealTime  	=new Kuyruk();
        Kuyruk UserJob   	=new Kuyruk();
        Kuyruk Priority1 	=new Kuyruk();
        Kuyruk Priority2 	=new Kuyruk();
        Kuyruk Priority3 	=new Kuyruk();

        try {
        	Process process;
        	String a[] = new String[] { "java", "-jar","program2.jar",Integer.toString(5)};
        	
        	
        	List<String> params = java.util.Arrays.asList(a);
        			ProcessBuilder builder = new ProcessBuilder(params);
        			process=builder.start();		
        			Scanner scanner = new Scanner(process.getInputStream());
        			while (scanner.hasNextLine()) {
        			    System.out.println(scanner.nextLine());
        			}
        	
        	
        	
        /*	
            Scanner myReader = new Scanner(dosya);

           int prosessirasi = 0;

            while (myReader.hasNextLine()) {
                Process process = new Process();
                Thread t = new Thread(process);
                t.start();
                t.notify();
                
                

                String satir = myReader.nextLine();

                String sayilar[] = satir.split(",");

                process.varisZamani = Integer.valueOf(sayilar[0].trim());
                process.oncelik = Integer.valueOf(sayilar[1].trim());
                process.sure = Integer.valueOf(sayilar[2].trim());
                process.id = prosessirasi;

                JobDispatch.Add(process);

                prosessirasi++;
            }

            myReader.close();

            int length = JobDispatch.Length();
            
            for (int i = 0; i < length; i++) {
                if(JobDispatch.First().oncelik==0){
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
                if(UserJob.First().oncelik==1){
                    Priority1.Add(UserJob.First());
                    System.out.print(Priority1.First().varisZamani + " " + Priority1.First().oncelik + " " + Priority1.First().sure + " " + Priority1.First().id + " ");
                    System.out.println();
                    Priority1.Delete();
                    UserJob.Delete();
                }else if(UserJob.First().oncelik==2){
                    Priority2.Add(UserJob.First());
                    System.out.print(Priority2.First().varisZamani + " " + Priority2.First().oncelik + " " + Priority2.First().sure + " " + Priority2.First().id + " ");
                    System.out.println();
                    Priority2.Delete();
                    UserJob.Delete();
                }else {
                    Priority3.Add(UserJob.First());
                    System.out.print(Priority3.First().varisZamani + " " + Priority3.First().oncelik + " " + Priority3.First().sure + " " + Priority3.First().id + " ");
                    System.out.println();
                    Priority3.Delete();
                    UserJob.Delete();
                }
            }

            for (int i = 0; i < length1; i++) {
                System.out.print(Priority1.First().varisZamani + " " + Priority1.First().oncelik + " " + Priority1.First().sure + " " + Priority1.First().id + " ");
                System.out.println();
                JobDispatch.Delete();
            }*/

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}