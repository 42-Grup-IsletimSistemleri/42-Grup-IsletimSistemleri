package cihaz;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class DosyaOkuma {
    public void dosyaOku(File dosya) {

        Kuyruk JobDispatch = new Kuyruk();
        try {
            Scanner myReader = new Scanner(dosya);


            String  varisZamani="";
            String oncelik="";
            String sure="";

            while (myReader.hasNextLine()) {
                Process process=new Process();

                String satir = myReader.nextLine();

                String sayilar[]=satir.split(",");

                process.varisZamani=Integer.valueOf(sayilar[0].trim());
                process.oncelik=Integer.valueOf(sayilar[1].trim());
                process.sure=Integer.valueOf(sayilar[2].trim());

                JobDispatch.Add(process);
            }
            myReader.close();

            for(int i=0;i< JobDispatch.Length();i++){
                System.out.print(JobDispatch.Delete().varisZamani+" "+JobDispatch.Delete().oncelik +" "+JobDispatch.Delete().sure+" ");
                System.out.println();
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
