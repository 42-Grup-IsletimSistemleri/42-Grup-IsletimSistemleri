package cihaz;

import java.io.*;
public class Renk {
  
    public static final String ANSI_RESET  = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[93m";
    
    
    Renk() {
        System.out.println(ANSI_YELLOW + "This text is yellow" + ANSI_RESET);
    }
}