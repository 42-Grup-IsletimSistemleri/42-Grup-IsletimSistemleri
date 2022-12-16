package cihaz;

import java.util.LinkedList;
import java.util.Queue;

public class Kuyruk {

    Queue<Process> prosesler;
    private int kuyrukSayisi;

    Kuyruk(){
        prosesler=new LinkedList<>();
        kuyrukSayisi = 0;
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

    public Process First(){
        Process p= prosesler.peek();
        return p;
    }
}