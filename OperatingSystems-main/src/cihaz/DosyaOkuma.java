package cihaz;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class DosyaOkuma {
    public Kuyruk JobDispatch	=new Kuyruk();
    public Kuyruk RealTime  	=new Kuyruk();
    Kuyruk UserJob   	=new Kuyruk();
    Kuyruk Priority1 	=new Kuyruk();
    Kuyruk Priority2 	=new Kuyruk();
    Kuyruk Priority3 	=new Kuyruk();
    public int x;

    public void dosyaOku(File dosya) throws IOException {
        try {
            Scanner myReader = new Scanner(dosya);
            int prosessirasi = 0;
            while (myReader.hasNextLine()) {

                String satir = myReader.nextLine();
                String sayilar[] = satir.split(",");


                int varisSuresi =Integer.parseInt(sayilar[0].trim());
                int oncelik =Integer.parseInt(sayilar[1].trim());
                int sure =Integer.parseInt(sayilar[2].trim());
                int id =Integer.parseInt(sayilar[3].trim());

                List<String> params = java.util.Arrays.asList("java", "-jar", "program.jar", Integer.toString(prosessirasi), sayilar[0].trim(), sayilar[1].trim(), sayilar[2].trim(), "basladi");
                ProcessBuilder builder = new ProcessBuilder(params);
                builder.redirectError();

                JobDispatch.Add(builder);
                prosessirasi++;

                int length = JobDispatch.Length();

                for (int i = 0; i < length; i++) {
                    if(oncelik==0){
                        RealTime.Add(JobDispatch.First());
                        JobDispatch.Delete();
                        java.lang.Process process = RealTime.First().start();

                        Scanner scanner = new Scanner(((java.lang.Process) process).getInputStream());
                        while (scanner.hasNextLine()) {
                            System.out.println(scanner.nextLine());
                        }
                        RealTime.Delete();
                    }
                    else {
                        UserJob.Add(JobDispatch.First());
                        JobDispatch.Delete();
                    }
                }

            int len = UserJob.Length();

            for (int i = 0; i < len; i++) {
                if(oncelik==1){
                    Priority1.Add(UserJob.First());
                    UserJob.Delete();
                }else if(oncelik==2){
                    Priority2.Add(UserJob.First());
                    UserJob.Delete();
                }else {
                    Priority3.Add(UserJob.First());
                    UserJob.Delete();
                }
            }
         }
            myReader.close();
        }catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
        }
    }
}