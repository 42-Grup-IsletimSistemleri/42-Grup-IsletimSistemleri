package cihaz;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Renk {
    public Process process;
    public float r;
    public float g;
    public float b;
    public Color randomColor;
    public LinkedList<Color> renkler = new LinkedList<>();

    Renk() {
        Random Rand = new Random();
        //System.out.println(String.format("#%06x", random.nextInt(256 * 256 * 256)));
        // Java 'Color' class takes 3 floats, from 0 to 1.
        r = Rand.nextFloat();
        g = Rand.nextFloat();
        b = Rand.nextFloat();
        randomColor = new Color(r, g, b);
    }

    public Color RenkUret() {
        return randomColor;
    }
/*
    public Boolean varMi(Renk renk){
        for (var rgb: renkler) {
            if( )

        }
        return true;
    }*/
}
