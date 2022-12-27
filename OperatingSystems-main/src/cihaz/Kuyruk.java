package cihaz;

import java.util.LinkedList;
import java.util.Queue;

public class Kuyruk {

    Queue<ProcessBuilder> prosesler;
    private int kuyrukSayisi;

    Kuyruk(){
        prosesler=new LinkedList<>();
        kuyrukSayisi = 0;
    }

    public void Add(ProcessBuilder process){
        kuyrukSayisi++;
        prosesler.offer(process);
    }

    public int Length(){
        return kuyrukSayisi;
    }

    public ProcessBuilder Delete(){
        ProcessBuilder p=prosesler.poll();
        kuyrukSayisi--;
        return p;
    }

    public ProcessBuilder First(){
        ProcessBuilder p= prosesler.peek();
        return p;
    }
}