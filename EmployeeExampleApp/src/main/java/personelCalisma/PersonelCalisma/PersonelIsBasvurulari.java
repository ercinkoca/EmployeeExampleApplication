package personelCalisma.PersonelCalisma;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PersonelIsBasvurulari {
	
	private int id;
	private int personelId;
	private LocalDate tarih;
	private int ilId;
	private Boolean engel;
	private String isyeriAd;
	private String pozisyon;
	private String aciklama;
	
	public PersonelIsBasvurulari() {
		
	}
	
	public PersonelIsBasvurulari(int id, int personelId, LocalDate tarih, int ilId, Boolean engel, String isyeriAd,
			String pozisyon, String aciklama) {
		super();
		this.id = id;
		this.personelId = personelId;
		this.tarih = tarih;
		this.ilId = ilId;
		this.engel = engel;
		this.isyeriAd = isyeriAd;
		this.pozisyon = pozisyon;
		this.aciklama = aciklama;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPersonelId() {
		return personelId;
	}

	public void setPersonelId(int personelId) {
		this.personelId = personelId;
	}

	public LocalDate getTarih() {
		return tarih;
	}

	public void setTarih(LocalDate tarih) {
		this.tarih = tarih;
	}

	public int getIlId() {
		return ilId;
	}

	public void setIlId(int ilId) {
		this.ilId = ilId;
	}

	public Boolean getEngel() {
		return engel;
	}

	public void setEngel(Boolean engel) {
		this.engel = engel;
	}

	public String getIsyeriAd() {
		return isyeriAd;
	}

	public void setIsyeriAd(String isyeriAd) {
		this.isyeriAd = isyeriAd;
	}

	public String getPozisyon() {
		return pozisyon;
	}

	public void setPozisyon(String pozisyon) {
		this.pozisyon = pozisyon;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

}
