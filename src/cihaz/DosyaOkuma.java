package cihaz;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class DosyaOkuma {
	Kuyruk JobDispatch = new Kuyruk();

    public void dosyaOku(File dosya) throws IOException {
        try {

            Scanner myReader = new Scanner(dosya);

            int prosessirasi = 0;

            while (myReader.hasNextLine()) {//giris.txt dosyasi okunur. Satir sayisi kadar dongu calisir
            	
                String satir = myReader.nextLine();

                String sayilar[] = satir.split(",");//her satirdaki 3 deger bu dizide tutulur.

				Proses p = new Proses();
                p.varisZamani = Integer.valueOf(sayilar[0].trim());//dosyadan okunan varis zamani integera cevirilerek prosesin degiskenine atanir.
                p.oncelik = Integer.valueOf(sayilar[1].trim());//dosyadan okunan oncelik degeri integera cevirilerek prosesin degiskenine atanir.
                p.sure = Integer.valueOf(sayilar[2].trim());//dosyadan okunan calisma süresi integera cevirilerek prosesin degiskenine atanir.
                p.id = prosessirasi;//listedeki siraya gore prosesin id degeri atanir.

                //Olusturulacak prosesin bilgileri listede tutulur.
	    		List<String> params = java.util.Arrays.asList( "java", "-jar","program.jar",sayilar[0].trim(),Integer.toString(prosessirasi),sayilar[1].trim(),sayilar[2].trim(),"basladi");
	    		ProcessBuilder builder = new ProcessBuilder(params);//listedeki degerler parametre olarak verilir.
	    		builder.redirectError();
	    		p.proses = builder.start();
                prosessirasi++;
                JobDispatch.Add(p);//Olusturulan proses JobDispatch kuyruguna atilir.
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}