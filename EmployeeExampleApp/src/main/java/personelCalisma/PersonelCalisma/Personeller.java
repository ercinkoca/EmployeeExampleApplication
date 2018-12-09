package personelCalisma.PersonelCalisma;

import java.util.Date;

public class Personeller {
	
	private int Id;
	private String adsoyad;
	private int ilid;
	private int ilceid;
	private String cinsiyet;
	private Date dogumTarihi;	
	private String aciklama;
	
	public Personeller() {
		// TODO Auto-generated constructor stub
	}

	public Personeller(int id, String adsoyad, int ilid, int ilceid, String cinsiyet, Date dogumTarihi,
			String aciklama) {
		super();
		Id = id;
		this.adsoyad = adsoyad;
		this.ilid = ilid;
		this.ilceid = ilceid;
		this.cinsiyet = cinsiyet;
		this.dogumTarihi = dogumTarihi;
		this.aciklama = aciklama;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getAdsoyad() {
		return adsoyad;
	}

	public void setAdsoyad(String adsoyad) {
		this.adsoyad = adsoyad;
	}

	public int getIlid() {
		return ilid;
	}

	public void setIlid(int ilid) {
		this.ilid = ilid;
	}

	public int getIlceid() {
		return ilceid;
	}

	public void setIlceid(int ilceid) {
		this.ilceid = ilceid;
	}

	public String getCinsiyet() {
		return cinsiyet;
	}

	public void setCinsiyet(String cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	public Date getDogumTarihi() {
		return dogumTarihi;
	}

	public void setDogumTarihi(Date dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	
	

}
