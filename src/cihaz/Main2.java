package cihaz;

public class Main2 {

	public static void main(String[] args) throws InterruptedException {
		
		 String saniye=args[0];
		 String id=args[1];
		 String oncelik=args[2];
		 String patlamaZamani=args[3];
		 String durum=args[4];
		 
		 if(durum.equals("basladi"))
			 System.out.println(" Proses Baslatildi (id:"+String.format("%04d",Integer.parseInt(id))+" Oncelik: "+oncelik+" Kalan Sure: "+patlamaZamani+")");
		 
		 if(durum.equals("yurutuluyor"))
			 System.out.println(" Proses Yurutuluyor (id:"+String.format("%04d",Integer.parseInt(id))+" Oncelik: "+oncelik+" Kalan Sure: "+patlamaZamani+")");
		 
		 if(durum.equals("askida"))
			 System.out.println(" Proses Askiya Alindi (id:"+String.format("%04d",Integer.parseInt(id))+" Oncelik: "+oncelik+" Kalan Sure: "+patlamaZamani+")");
		
		 if(durum.equals("sonlandi"))
			 System.out.println(" Proses Sonlandi (id:"+String.format("%04d",Integer.parseInt(id))+" Oncelik: "+oncelik+" Kalan Sure: "+patlamaZamani+")");
	}
}