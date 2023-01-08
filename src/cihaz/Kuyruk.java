package cihaz;

import java.util.LinkedList;
import java.util.Queue;

public class Kuyruk {

    Queue<Proses> prosesler; //icerisinde proses sýnýfýndan nesneler tutan bir kuyruk tanimlanir.
    private int kuyrukSayisi;

    Kuyruk(){
        prosesler=new LinkedList<>();
        kuyrukSayisi = 0;
    }

    public void Add(Proses process){//kuyruga eleman ekleme fonksiyonu
        kuyrukSayisi++;
        prosesler.offer(process);
    }

    public int Length(){//kuyrugun uzunlugunu donduren fonksiyon
        return kuyrukSayisi;
    }

    public int size(){
        return prosesler.size();
    }
    public Proses Delete(){//kuyruktan eleman silme fonksiyonu
        Proses p=prosesler.poll();
        kuyrukSayisi--;
        return p;
    }

    public Proses First(){//kuyrugun ilk elemanini getiren fonksiyon
        Proses p= prosesler.peek();
        return p;
    }
    public boolean doluMu(){//kuyrugun bos olup olmadigini konutrol eden fonksiyon
        if(kuyrukSayisi<=0){
            return false;
        }
        else{
            return  true;
        }
    }

}