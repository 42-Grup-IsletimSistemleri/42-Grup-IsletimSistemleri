package cihaz;

public class Main2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub	  try {
		  
		
		String saniye=args[0];
		 String id=args[1];
		 String oncelik=args[2];
		 String patlamaZamani=args[3];
		 String durum=args[4];
	
	
		 
		 if(durum.equals("basladi"))
		 System.out.println(saniye+". saniye "+" Proses Baslatildi (id:"+String.format("%04d",Integer.parseInt(id))+" Oncelik: "+oncelik+" Kalan Sure: "+patlamaZamani+")");
		 
		 if(durum.equals("yurutuluyor"))
			 System.out.println(saniye+". saniye "+" Proses Yurutuluyor (id:"+id+" Oncelik: "+oncelik+" Kalan Sure: "+patlamaZamani+")");
		 
		 if(durum.equals("askida"))
			 System.out.println(saniye+". saniye "+" Proses Askiya Alindi (id:"+id+" Oncelik: "+oncelik+" Kalan Sure: "+patlamaZamani+")");
		
		 if(durum.equals("sonlandi"))
			 System.out.println(saniye+". saniye "+" Proses Sonlandi (id:"+id+" Oncelik: "+oncelik+" Kalan Sure: "+patlamaZamani+")");
		 
		 
	}
     
	


}