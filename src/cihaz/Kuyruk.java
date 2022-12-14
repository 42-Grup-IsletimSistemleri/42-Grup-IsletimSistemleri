package cihaz;

import java.util.LinkedList;
import java.util.Queue;

public class Kuyruk {

    Queue<Process> prosesler;
    public static int kuyrukSayisi=0;

    Kuyruk(){
        prosesler=new LinkedList<>();
    }

    public void Add(Process process){
        kuyrukSayisi++;
        prosesler.offer(process);
    }

    public int Length(){
        return kuyrukSayisi;
    }

    public Process Delete(){
        Process p=prosesler.poll();
        kuyrukSayisi--;
        return p;
    }
}
