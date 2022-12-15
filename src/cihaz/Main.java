package cihaz;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        DosyaOkuma d=new DosyaOkuma();
        File dosya=new File(args[0]);
        d.dosyaOku(dosya);
    }
}
