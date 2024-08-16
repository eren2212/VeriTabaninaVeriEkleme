
import com.mysql.cj.jdbc.Driver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Baglanti {
    private String kullanici_adi = "root";
    private String parola = "";
    private String host = "localhost";
    private int port = 3306;
    private String db_ismi = "demo";
    private Connection con = null;
    
    private Statement statement = null;
    
    
    public void calisanlariGetir(){
        
        String sorgu = "Select * from calisanlar";
        try {
            
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sorgu);
            
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String mail = rs.getString("email");
                
                System.out.println("İd: "+ id +" Ad: "+ad+" Soyad: "+soyad +" Email: "+mail);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void calisanEkle(){
        
        try{
            statement = con.createStatement();
            String ad = "semih";
            String soyad = "aktaş";
            String email = "semihaktaş@gmail.com";
            
            String sorgu = "Insert Into calisanlar (ad,soyad,email) VALUES (" +"'"+ ad+"',"+"'"+soyad+"',"+"'"+email+"')";
            statement.executeUpdate(sorgu);
            
        } catch (SQLException ex) {
            Logger.getLogger(Baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public Baglanti(){
        //jdbc:mysql://:localhost:3306/demo
        String url = "jdbc:mysql://"+ host + ":" + port + "/" +db_ismi+"?useUnicode=true&characterEncoding=utf8";
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadi");
        }
        
        try {
            con = DriverManager.getConnection(url, kullanici_adi, parola);
            System.out.println("Bağlantı Başarılı");
        } catch (SQLException ex) {
            System.out.println("Bağlantı başarısız");
        }
        
        
    }
    public static void main(String[] args) {
        Baglanti baglanti = new Baglanti();
        System.out.println("Eklemeden önce");
        baglanti.calisanlariGetir();
        
        baglanti.calisanEkle();
        System.out.println("Ekledikten sonra");
        baglanti.calisanlariGetir();
    }
}
