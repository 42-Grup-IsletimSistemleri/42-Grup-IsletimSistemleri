package cihaz;

import java.util.LinkedList;
import java.util.Queue;

public class Kuyruk {

    Queue<Proses> prosesler;
    private int kuyrukSayisi;

    Kuyruk(){
        prosesler=new LinkedList<>();
        kuyrukSayisi = 0;
    }

    public void Add(Proses process){
        kuyrukSayisi++;
        prosesler.offer(process);
    }

    public int Length(){
        return kuyrukSayisi;
    }

    public int size(){
        return prosesler.size();
    }
    public Proses Delete(){
        Proses p=prosesler.poll();
        kuyrukSayisi--;
        return p;
    }

    public Proses First(){
        Proses p= prosesler.peek();
        return p;
    }
    public boolean doluMu(){
        if(kuyrukSayisi==0){
            return false;
        }
        else{
            return  true;
        }
    }

}