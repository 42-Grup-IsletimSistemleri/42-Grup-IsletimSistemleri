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
    public void dosyaOku(File dosya) throws IOException {

        JobDispatch	=new Kuyruk();
        
        Proses p = new Proses();

        try {

            Scanner myReader = new Scanner(dosya);

            int prosessirasi = 0;

            while (myReader.hasNextLine()) {
            	
                String satir = myReader.nextLine();

                String sayilar[] = satir.split(",");

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

	            Scanner scanner = new Scanner(Priority1.First().proses.getInputStream());
	    		while (scanner.hasNextLine()) {
	    		    System.out.println(scanner.nextLine());
	    		}
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}