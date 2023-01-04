package cihaz;

import java.util.Random;

public class Proses {
    public String renkler[] = new String[17];
    
    public int varisZamani;
    public int oncelik;
    public int sure;
    public int id;
    public String renk;
    public java.lang.Process proses;
    
    Proses(){
	    for(int i=0;i<8;i++) renkler[i] = "\u001B["+ (30+i+1) +"m";
	    for(int i=8;i<17;i++) renkler[i] = "\u001B["+ (90+i-8) +"m";

	    Random rand = new Random();
	    int random = rand.nextInt(17);
	    renk = renkler[random];
  
    }
}