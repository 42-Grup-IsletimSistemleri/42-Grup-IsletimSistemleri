package cihaz;

import java.io.*;
public class Renk {
  
    public static final String ANSI_RESET  = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[93m";
    
    public String renkler[] = new String[18];
    
    
    Renk() {
	    for(int i=0;i<9;i++) {
	    	renkler[i] = "\u001B["+ (30+i) +"m";
	    
        System.out.println(renkler[i] + String.format("%04d",i) + ANSI_RESET);
        }
	    for(int i=0;i<9;i++) {
	    	renkler[i] = "\u001B["+ (90+i) +"m";
	    
        System.out.println(renkler[i] + String.format("%04d",i+9) + ANSI_RESET);
        }
    }
}