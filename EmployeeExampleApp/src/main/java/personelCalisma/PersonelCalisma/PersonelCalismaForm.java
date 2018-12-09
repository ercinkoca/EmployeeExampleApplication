package personelCalisma.PersonelCalisma;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.renderers.ComponentRenderer;

public class PersonelCalismaForm extends PersonelCalismaDesign {

	vtIslemleri veriTabani=new vtIslemleri(); //Veritabanına ait tüm sorguların yer aldığı sınıf.
	
    public PersonelCalismaForm() {
    	setConf();
    	comboDoldur();
		gridGetir();
	}
    
    private void setConf() {
    	//Cinsiyet Seçiminde Kullanılır.
    	chksecim.setItems(1,2);
    	chksecim.setItemCaptionGenerator(e->{
    		return e.equals(1) ? "Erkek" : "Kadın";
    	});
    	
    	btnKaydet.addClickListener(e->{
    		personelKaydiEkle(); //Personel Tablosuna Kayıt Atmak İçin Kullanılır.
    	});
    }
    
    private void gridGetir() {
    	
    	//Personel İş Başvuruları Tablosundaki Verileri Gride Yazmak İçin Kullanılır.
    	//Bu ekranda Personel İş Başvuruları tablosu elle doldurulmuştur.
    	grdPersonel.removeAllColumns();
    	List<PersonelIsBasvurulari> container=veriTabani.tablodanListele();
    	if(!container.isEmpty()) {
    		grdPersonel.setItems(container);
    	}
    	
    	grdPersonel.addColumn(e->{
    		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");
    		if(e.getTarih() != null) {
    			return e.getTarih().format(formatter);
    		}
    		else {
    			return null;
    		}
    	}).setCaption("Tarih");
    	
    	grdPersonel.addColumn(e->{
    		ArrayList<Iller> illerList=veriTabani.IDyeGoreSehirGetir(e.getIlId());
    		if(!illerList.isEmpty()) {
    			return illerList.get(0).getIlAdi();
    		}else {
    			return null;
    		}
    		
    	}).setCaption("Şehir");
    	grdPersonel.addColumn(PersonelIsBasvurulari::getIsyeriAd).setCaption("İş Yeri Adı");
    	grdPersonel.addColumn(PersonelIsBasvurulari::getPozisyon).setCaption("Pozisyon");
    	grdPersonel.addColumn(e->{
    		CheckBox chk=new CheckBox();
    		if(e.getEngel()) {
    			chk.setValue(e.getEngel());
    		}
    		chk.setEnabled(false);
    		return chk;
    	}).setRenderer(new ComponentRenderer()).setCaption("Seyahat Engelim Yok");
    	grdPersonel.addColumn(PersonelIsBasvurulari::getAciklama).setCaption("Açıklama");
  
    }
    
    private void personelKaydiEkle() {
    	
    	//Personel Tablosuna Kayıt Atmak İçin Kullanılır.
    	
    	String cinsiyet="";
    	if(chksecim.getSelectedItem().equals(1)) {
    		cinsiyet="Kadın";
    	}else {
    		cinsiyet="Erkek";
    	}
    	
    	//Personel Tablosundaki Kayıtları Listeleyip,En Son Kaydın Id'sine 1 ekleyip kayıt yapmak için kullanılır.
    	ArrayList<Personeller> personelListesi=veriTabani.personelListele();
    	int atanacakPersonelid=0;
    	atanacakPersonelid=personelListesi.get(personelListesi.size()-1).getId();
    	
    	//Seçilen İli ve İlçeyi listeleyip Id'lerine erişmek için kullanılır.
    	ArrayList<Iller> illerListesi=veriTabani.ismeGoreSehirGetir(cmbIller.getValue().getIlAdi());
    	ArrayList<Ilceler> ilcelerListesi=veriTabani.ismeGoreIlceGetir(cmbIlceler.getValue().getIlceAdi());
    	
    	if(!txtAdSoyad.isEmpty() && !cmbIller.isEmpty() && !cmbIlceler.isEmpty() && !chksecim.isEmpty() && !dtDogumTarihi.isEmpty()) {
    		Boolean a=veriTabani.personelKayitEkle(atanacakPersonelid+1,txtAdSoyad.getValue(), illerListesi.get(0).getIlId(), ilcelerListesi.get(0).getIlceid(), cinsiyet, dtDogumTarihi.getValue().toString(), txtAciklama.getValue());
        	if(a.equals(true)) {
        		Notification.show("Kayıt Eklendi");
        	}
    	}
    	else {
    		Notification.show("Lütfen Boş Alan Bırakmayınız!");
    	}
    	
    	
    	
    }
    
    private void comboDoldur() {
    	ArrayList<Iller> illerList=veriTabani.sehirleriGetir();//Iller tablosundaki eklenmiş verileri combobox'a eklemek için kullanılır.
    	cmbIller.setItems(illerList);
    	cmbIller.setItemCaptionGenerator(e->e.getIlAdi());
    	cmbIller.setEmptySelectionAllowed(false);
    	
    	cmbIller.addValueChangeListener(e->{
    		//Hangi il seçilmişse ona bağlı ilçeleri listelemek için kullanılır.
    		ArrayList<Ilceler> ilcelist=veriTabani.ileGoreIlceGetir(e.getValue().getIlId());
    		cmbIlceler.setItems(ilcelist);
    		cmbIlceler.setItemCaptionGenerator(f->f.getIlceAdi());
    		cmbIlceler.setEmptySelectionAllowed(false);
    		
    	});
    	
    	
    }

}
