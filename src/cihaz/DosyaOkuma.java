package cihaz;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class DosyaOkuma {
	Kuyruk JobDispatch  =new Kuyruk();
    Kuyruk RealTime  	=new Kuyruk();
    Kuyruk UserJob   	=new Kuyruk();
    Kuyruk Priority1 	=new Kuyruk();
    Kuyruk Priority2 	=new Kuyruk();
    Kuyruk Priority3 	=new Kuyruk();

    public void dosyaOku(File dosya) throws IOException {
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
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}