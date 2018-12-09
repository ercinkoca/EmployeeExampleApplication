package personelCalisma.PersonelCalisma;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class vtIslemleri {
	
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DATABASE_URL = "jdbc:mysql://localhost/personel_veri?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
    private final String USERNAME = "root";
    private final String PASSWORD = "ercinkoca94";
    Connection connect=null;
    Statement statement=null;
    
    
    public Connection baglantiAc() {
    	try {
			Class.forName(JDBC_DRIVER);
            connect=DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
            System.out.println("Veri Tabanına Bağlanıldı.");
			
		} catch(SQLException e)
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
		return connect;
    }
    
    public void baglantiKapa() {
    	try {
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public ArrayList<PersonelIsBasvurulari> tablodanListele(){
    	ArrayList<PersonelIsBasvurulari> personelBasvuru=new ArrayList<>();
    	try {
    		
    		Connection conn=baglantiAc();
    		String sorgu="Select * from personel_isbasvurulari";
    		PreparedStatement ps=conn.prepareStatement(sorgu);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next()) {
    			PersonelIsBasvurulari personel=new PersonelIsBasvurulari();
    			personel.setId(rs.getInt("t_id"));
    			personel.setPersonelId(rs.getInt("t_personelid"));
    			personel.setTarih(rs.getDate("t_date").toLocalDate());
    			personel.setIlId(rs.getInt("t_ilid"));
    			personel.setEngel(rs.getBoolean("t_engel"));
    			personel.setIsyeriAd(rs.getString("t_isyeriad"));
    			personel.setPozisyon(rs.getString("t_pozisyon"));
    			personel.setAciklama(rs.getString("t_aciklama"));
    			personelBasvuru.add(personel);
    		}
    		
    		ps.close();
    		rs.close();
    		baglantiKapa();
    		
    	}catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
     }
    	return personelBasvuru;
    }
    
    public ArrayList<Personeller> personelListele(){
    	ArrayList<Personeller> personeller=new ArrayList<>();
    	try {
    		
    		Connection conn=baglantiAc();
    		String sorgu="Select * from personeller";
    		PreparedStatement ps=conn.prepareStatement(sorgu);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next()) {
    			Personeller pers=new Personeller();
    			pers.setId(rs.getInt("t_id"));
    			pers.setAdsoyad(rs.getString("t_adisoyadi"));
    			pers.setCinsiyet(rs.getString("t_cinsiyet"));
    			pers.setIlid(rs.getInt("t_ilid"));
    			pers.setIlceid(rs.getInt("t_ilceid"));
    			pers.setDogumTarihi(rs.getDate("t_dogumtarihi"));
    			pers.setAciklama(rs.getString("t_aciklama"));
    			personeller.add(pers);
    		}
    		
    		ps.close();
    		rs.close();
    		baglantiKapa();
    		
    	}catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
     }
    	return personeller;
    }
    
    public boolean personelKayitEkle(int id,String adsoyad,int ilid,int ilceid,String cinsiyet,String dogumtar,String aciklama) {
    	
    	try {
    		Connection conn=baglantiAc();
    		PreparedStatement ps=conn.prepareStatement("INSERT INTO personeller VALUES (?,?,?,?,?,?,?) ");
    		ps.setInt(1, id);
    		ps.setString(2, adsoyad);
    		ps.setInt(3,ilid);
    		ps.setInt(4, ilceid);
    		ps.setString(5, cinsiyet);
    		ps.setString(6, dogumtar);
    		ps.setString(7, aciklama);
    		ps.executeUpdate();
    		ps.close();
    		baglantiKapa();
    	}catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
     }
    	
    	return true;
    	
    }
    
    public ArrayList<Iller> sehirleriGetir(){
    	ArrayList<Iller> illerList=new ArrayList<>();
    	try {
    		Connection conn=baglantiAc();
    		String sorgu="Select * from iller";
    		PreparedStatement ps=conn.prepareStatement(sorgu);
    		ResultSet rs=ps.executeQuery();
    		
    		while(rs.next()) {
    			Iller iller=new Iller();
    			iller.setIlId(rs.getInt("t_id"));
    			iller.setIlAdi(rs.getString("t_adi"));
    			illerList.add(iller);
    		}
    		
    		ps.close();
    		rs.close();
    		baglantiKapa();
    	}catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
     }
    	return illerList;
    }
    
    public ArrayList<Ilceler> ileGoreIlceGetir(int ID){
    	ArrayList<Ilceler> ilcelerList=new ArrayList<>();
    	try {
    		Connection conn=baglantiAc();
    		String sorgu="Select * from ilceler where t_ilid="+ID;
    		PreparedStatement ps=conn.prepareStatement(sorgu);
    		ResultSet rs=ps.executeQuery();
    		
    		while(rs.next()) {
    			Ilceler ilceler=new Ilceler();
    			ilceler.setIlceid(rs.getInt("t_id"));
    			ilceler.setIlceAdi(rs.getString("t_adi"));
    			ilcelerList.add(ilceler);
    		}
    		
    		ps.close();
    		rs.close();
    		baglantiKapa();
    	}catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
     }
    	return ilcelerList;
    }
    
    public ArrayList<Iller> IDyeGoreSehirGetir(int ID){
    	ArrayList<Iller> illerList=new ArrayList<>();
    	try {
    		Connection conn=baglantiAc();
    		String sorgu="Select * from iller where t_id="+ID;
    		PreparedStatement ps=conn.prepareStatement(sorgu);
    		ResultSet rs=ps.executeQuery();
    		
    		while(rs.next()) {
    			Iller iller=new Iller();
    			iller.setIlId(rs.getInt("t_id"));
    			iller.setIlAdi(rs.getString("t_adi"));
    			illerList.add(iller);
    		}
    		
    		ps.close();
    		rs.close();
    		baglantiKapa();
    	}catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
     }
    	return illerList;
    }
    
    public ArrayList<Iller> ismeGoreSehirGetir(String sehir){
    	ArrayList<Iller> illerList=new ArrayList<>();
    	try {
    		Connection conn=baglantiAc();
    		String sorgu="Select * from iller where t_adi like ? ";
    		PreparedStatement ps=conn.prepareStatement(sorgu);
    		ps.setString(1, sehir);
    		ResultSet rs=ps.executeQuery();
    		
    		while(rs.next()) {
    			Iller iller=new Iller();
    			iller.setIlId(rs.getInt("t_id"));
    			iller.setIlAdi(rs.getString("t_adi"));
    			illerList.add(iller);
    		}
    		
    		ps.close();
    		rs.close();
    		baglantiKapa();
    	}catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
     }
    	return illerList;
    }
    
    public ArrayList<Ilceler> ismeGoreIlceGetir(String ilce){
    	ArrayList<Ilceler> ilcelerList=new ArrayList<>();
    	try {
    		Connection conn=baglantiAc();
    		String sorgu="Select * from ilceler where t_adi like ? ";
    		PreparedStatement ps=conn.prepareStatement(sorgu);
    		ps.setString(1, ilce);
    		ResultSet rs=ps.executeQuery();
    		
    		while(rs.next()) {
    			Ilceler ilceler=new Ilceler();
    			ilceler.setIlceid(rs.getInt("t_id"));
    			ilceler.setIlceAdi(rs.getString("t_adi"));
    			ilcelerList.add(ilceler);
    		}
    		
    		ps.close();
    		rs.close();
    		baglantiKapa();
    	}catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
     }
    	return ilcelerList;
    }

}
