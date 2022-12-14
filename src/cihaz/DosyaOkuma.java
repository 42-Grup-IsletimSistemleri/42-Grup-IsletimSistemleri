package cihaz;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class DosyaOkuma {
    public void Operator(File dosya) {

        try {
            Scanner myReader = new Scanner(dosya);

            String  varisZamani="";
            String oncelik="";
            String sure="";

            while (myReader.hasNextLine()) {
                String satir = myReader.nextLine();

                String sayilar[]=satir.split(",");

                for(int i=0; i<3;i++){

                    System.out.println(Integer.valueOf(sayilar[i].trim()));
                }

            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
