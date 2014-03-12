/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reklamotomasyonu;

import java.sql.*;

/**
 *
 * @author Kursat
 */
public class Database {

    //Veritabanına bağlantı için Connection Stringimiz.
    
    private String baglantiStr = "jdbc:sqlserver://192.168.56.101:1433;databaseName=reklam;user=sa;password=1";

    public String[] GirisKontrol(String[] bilgiler) {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            String sorgu = "exec girisKontrol '" + bilgiler[0] + "','" + bilgiler[1] + "'";

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sorgu);
            String[] bilgi = new String[3];
            while (resultSet.next()) {
                bilgi[0] = resultSet.getString("Rol");
                bilgi[1] = resultSet.getString("KullaniciID");
                bilgi[2] = resultSet.getString("KullaniciAdi");
            }
            conn.close();
            return bilgi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] KullaniciDetay(int id) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            String sorgu = "exec KullaniciDetay '" + id + "'";

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sorgu);
            String[] bilgi = new String[12];
            while (resultSet.next()) {
                bilgi[0] = resultSet.getString("MusteriNo");
                bilgi[1] = resultSet.getString("MusteriAdi");
                bilgi[2] = resultSet.getString("Adres");
                bilgi[3] = resultSet.getString("Telefon");
                bilgi[4] = resultSet.getString("EPosta");
                bilgi[5] = resultSet.getString("sehiradi");
                bilgi[6] = resultSet.getString("tcno");
                bilgi[7] = resultSet.getString("vergino");
                bilgi[8] = resultSet.getString("aktifmi");
                bilgi[9] = resultSet.getString("KullaniciAdi");
                bilgi[10] = resultSet.getString("KullaniciSifre");
                bilgi[11] = resultSet.getString("PlakaNo");
            }
            conn.close();
            return bilgi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] Kullanicilar() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            String sorgu = "exec Kullanicilar";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sorgu);
            int i = 0;
            while (resultSet.next()) {
                i++;
            }
            String[] bilgi = new String[i];
            i = 0;
            resultSet = statement.executeQuery(sorgu);
            while (resultSet.next()) {
                bilgi[i] = resultSet.getString("MusteriNo") + "->" + resultSet.getString("MusteriAdi");
                i++;
            }
            conn.close();
            return bilgi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] Iller() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            String sorgu = "exec Iller";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sorgu);
            int i = 0;
            while (resultSet.next()) {
                i++;
            }
            String[] bilgi = new String[i];
            i = 0;
            resultSet = statement.executeQuery(sorgu);
            while (resultSet.next()) {
                bilgi[i] = resultSet.getString("PlakaNo") + "-" + resultSet.getString("SehirAdi");
                i++;
            }
            conn.close();
            return bilgi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String SonKullanici() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            String sorgu = "exec SonKullanici";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sorgu);

            String bilgi = null;
            resultSet = statement.executeQuery(sorgu);
            while (resultSet.next()) {
                bilgi = resultSet.getString("KullaniciID");
            }
            conn.close();
            return bilgi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean KullaniciEkle(String[] veriler) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            Statement statement = conn.createStatement();
            String ezquel = "exec KullaniciEkle1 '" + veriler[7] + "','" + veriler[8] + "','0'";
            statement.executeUpdate(ezquel);
            String kulID = SonKullanici();
            String sql = "exec KullaniciEkle2 "
                    + " '" + kulID + "','" + veriler[0] + "',"
                    + "'" + veriler[1] + "','" + veriler[2] + "','" + veriler[3] + "','" + veriler[4] + "','" + veriler[5] + "','" + veriler[6] + "'";
            statement.executeUpdate(sql);
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println("Kullanıcı Eklenemedi! ");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean KategoriEkle(String kategori) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            Statement statement = conn.createStatement();
            String ezquel = "exec KategoriEkle '" + kategori + "'";
            statement.executeUpdate(ezquel);
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println("Kategori Eklenemedi! ");
            System.err.println(e.getMessage());
        }

        return false;
    }

    public String[] Kategoriler() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            String sorgu = "exec Kategoriler";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sorgu);
            int i = 0;
            while (resultSet.next()) {
                i++;
            }
            String[] bilgi = new String[i];
            i = 0;
            resultSet = statement.executeQuery(sorgu);
            while (resultSet.next()) {
                bilgi[i] = resultSet.getString("KategoriID") + "-" + resultSet.getString("KategoriAdi");
                i++;
            }
            conn.close();
            return bilgi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] Urunler() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            String sorgu = "exec Urunler ";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sorgu);
            int i = 0;
            while (resultSet.next()) {
                i++;
            }
            String[] bilgi = new String[i];
            i = 0;
            resultSet = statement.executeQuery(sorgu);
            while (resultSet.next()) {
                bilgi[i] = resultSet.getString("UrunID") + "|" + resultSet.getString("UrunAdi");
                i++;
            }
            conn.close();
            return bilgi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] Urunler1() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            String sorgu = "exec Urunler1";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sorgu);
            int i = 0;
            while (resultSet.next()) {
                i++;
            }
            String[] bilgi = new String[i];
            i = 0;
            resultSet = statement.executeQuery(sorgu);
            while (resultSet.next()) {
                bilgi[i] = resultSet.getString("UrunID") + "|" + resultSet.getString("UrunAdi");
                i++;
            }
            conn.close();
            return bilgi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] UrunDetay(int id) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            String sorgu = "exec UrunDetay '" + id + "'";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sorgu);
            String[] bilgi = new String[9];
            while (resultSet.next()) {
                bilgi[0] = resultSet.getString("UrunID");
                bilgi[1] = resultSet.getString("UrunAdi");
                bilgi[2] = resultSet.getString("Aciklama");
                bilgi[3] = resultSet.getString("Fiyat");
                bilgi[4] = resultSet.getString("KdvOrani");
                bilgi[5] = resultSet.getString("Miktar");
                bilgi[6] = resultSet.getString("SatistaMi");
                bilgi[7] = resultSet.getString("KategoriID");
                bilgi[8] = resultSet.getString("KategoriAdi");
            }
            conn.close();
            return bilgi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean UrunEkle(String[] veriler) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            Statement statement = conn.createStatement();
            String sql = "exec UrunEkle '" + veriler[1] + "',"
                    + "'" + veriler[6] + "','" + veriler[3] + "','" + veriler[5] + "','" + veriler[4] + "','" + veriler[2] + "','" + veriler[8] + "'";
            statement.executeUpdate(sql);
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println("Urun Eklenemedi! ");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean FiyatGuncelle(int yuzde) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            Statement statement = conn.createStatement();
            
            String sql = "UPDATE Urun SET Fiyat=Fiyat+(Fiyat*" + yuzde + ")/100";
            
            statement.executeUpdate(sql);
            
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println("Guncelleme Hatasi! ");
            System.err.println(e.getMessage());
        }

        return false;
    }

    public boolean MusteriGuncelle(String[] gelen) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            Statement statement = conn.createStatement();
            //TODO
            String sql = "exec MusteriGuncelle '" + gelen[1] + "','" + gelen[2] + "','" + gelen[3] + "','" + gelen[4] + "','" + gelen[5] + "','" + gelen[6] + "','" + gelen[7] + "','" + gelen[8] + "','" + gelen[0] + "'";

            statement.executeUpdate(sql);
            String ezq = "exec MusteriGuncelle2 '" + gelen[9] + "','" + gelen[10] + "','" + gelen[0] + "'";
            statement.executeUpdate(ezq);
            conn.close();
            return true;
        } catch (Exception e) {
          
            System.err.println("Guncelleme Hatasi! ");
            System.err.println(e.getMessage());
        }

        return false;
    }

    public boolean MusteriSil(String ney) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            Statement statement = conn.createStatement();
            String sql = "exec MusteriSil '" + ney + "'";
            statement.executeUpdate(sql);
            sql = "exec MusteriSil2 '" + ney + "'";
            statement.executeUpdate(sql);
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println("Silme Hatasi! ");
            System.err.println(e.getMessage());
        }

        return false;
    }

    public boolean KategoriGuncelle(String[] gelen) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            Statement statement = conn.createStatement();
            String ezq = "exec KategoriGuncelle '" + gelen[1] + "' ,'" + gelen[0] + "'";
            statement.executeUpdate(ezq);
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println("Guncelleme Hatasi! ");
            System.err.println(e.getMessage());
        }

        return false;
    }

    public boolean KategoriSil(String gelen) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            Statement statement = conn.createStatement();
            String ezq = "exec KategoriSil '" + gelen + "'";
            statement.executeUpdate(ezq);
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println("Silme Hatasi! ");
            System.err.println(e.getMessage());
        }

        return false;
    }

    public boolean UrunGuncelle(String[] gelen) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            Statement statement = conn.createStatement();
            String sql = "exec UrunGuncelle '" + gelen[1] + "','" + gelen[2] + "','" + gelen[3] + "','" + gelen[4] + "','" + gelen[5] + "','" + gelen[6] + "','" + gelen[7] + "' ,'" + gelen[0] + "'";

            statement.executeUpdate(sql);

            conn.close();
            return true;
        } catch (Exception e) {
           
            System.err.println("Guncelleme Hatasi! ");
            System.err.println(e.getMessage());
        }

        return false;
    }

    public boolean UrunSil(String gelen) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            Statement statement = conn.createStatement();
            String ezq = "exec UrunSil '" + gelen + "'";
            statement.executeUpdate(ezq);
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println("Silme Hatasi! ");
            System.err.println(e.getMessage());
        }

        return false;
    }

    public String[] Siparisler() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            String sorgu = "exec Siparisler";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sorgu);
            int i = 0;
            while (resultSet.next()) {
                i++;
            }
            String[] bilgi = new String[i];
            i = 0;
            resultSet = statement.executeQuery(sorgu);
            while (resultSet.next()) {
                bilgi[i] = resultSet.getString("FaturaDetayNo");
                i++;
            }
            conn.close();
            return bilgi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String SonFatura() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            Statement statement = conn.createStatement();
            String ezq = "exec SonFatura";

            ResultSet resultSet;
            String bilgi = null;
            resultSet = statement.executeQuery(ezq);
            while (resultSet.next()) {
                bilgi = resultSet.getString("FaturaNo");
            }
            conn.close();
            return bilgi;
        } catch (Exception e) {
            System.err.println("Silme Hatasi! ");
            System.err.println(e.getMessage());
        }

        return null;
    }

    public boolean SiparisVer(String[] gelen) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            Statement statement = conn.createStatement();
            String ezq = "exec SiparisVer1 '" +gelen[0] + "','" + gelen[1] + "'";
            statement.executeUpdate(ezq);
            if (SonFatura() != null) {
                ezq = "INSERT INTO FaturaDetay(FaturaNo,UrunID,BirimFiyat,Adet,Toplam,durum) VALUES('" + SonFatura() + "','" + gelen[2] + "','" + gelen[3] + "','" + gelen[4] + "','" + gelen[5] + "','0')";
                statement.executeUpdate(ezq);
                //ürünün stoğundan düşmesi için
                ezq = "UPDATE Urun SET Miktar=Miktar-1 WHERE UrunID=" + gelen[2];
                statement.executeUpdate(ezq);
            }

            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println("Ekleme Hatasi! ");
            System.err.println(e.getMessage());
        }

        return false;
    }

    public String[] SiparisDetay(int id) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            String sorgu = "exec SiparisDetay " + id;
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sorgu);
            String[] bilgi = new String[15];
            while (resultSet.next()) {
                bilgi[0] = resultSet.getString("FaturaDetayNo");
                bilgi[1] = resultSet.getString("UrunID");
                bilgi[2] = resultSet.getString("BirimFiyat");
                bilgi[3] = resultSet.getString("Adet");
                bilgi[4] = resultSet.getString("Toplam");
                bilgi[5] = resultSet.getString("durum");
                bilgi[6] = resultSet.getString("MusteriNo");
                bilgi[7] = resultSet.getString("Tarih");
                bilgi[8] = resultSet.getString("MusteriAdi");
                bilgi[9] = resultSet.getString("UrunAdi");
            }
            conn.close();
            return bilgi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean SiparisAktif(int gelen) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            Statement statement = conn.createStatement();
            String ezq = "exec SiparisAktif " + gelen;
            statement.executeUpdate(ezq);
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println("Güncelleme Hatasi! ");
            System.err.println(e.getMessage());
        }

        return false;
    }

    public String SonPersonel() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            Statement statement = conn.createStatement();
            String ezq = "exec SonPersonel";

            ResultSet resultSet;
            String bilgi = null;
            resultSet = statement.executeQuery(ezq);
            while (resultSet.next()) {
                bilgi = resultSet.getString("SicilNo");
            }
            conn.close();
            return bilgi;
        } catch (Exception e) {
            System.err.println("asd Hatasi! ");
            System.err.println(e.getMessage());
        }

        return null;
    }

    public boolean PersonelEkle(String[] gelen) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            Statement statement = conn.createStatement();
            String ezq = "exec PersonelEkle '" + gelen[0] + "','" + gelen[1] + "','" + gelen[3] + "','" + gelen[5] + "','" + gelen[4] + "'";
            statement.executeUpdate(ezq);
            if (SonPersonel() != null) {
                ezq = "INSERT INTO PersonelMaas(SicilNo,Maas) VALUES('" + SonPersonel() + "','" + gelen[2] + "')";
                statement.executeUpdate(ezq);
            }

            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println("Ekleme Hatasi! ");
            System.err.println(e.getMessage());
        }

        return false;
    }

    public String[] PersonelDetay(int id) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            String sorgu = "exec PersonelDetay " + id;
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sorgu);
            String[] bilgi = new String[7];
            while (resultSet.next()) {
                bilgi[0] = resultSet.getString("SicilNo");
                bilgi[1] = resultSet.getString("PersonelAdi");
                bilgi[2] = resultSet.getString("Adres");
                bilgi[3] = resultSet.getString("PlakaNo") + "-" + resultSet.getString("SehirAdi");
                bilgi[4] = resultSet.getString("TcNo");
                bilgi[5] = resultSet.getString("Yas");
                bilgi[6] = resultSet.getString("Maas");
            }
            conn.close();
            return bilgi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] Personeller() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            String sorgu = "exec Personeller";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sorgu);
            int i = 0;
            while (resultSet.next()) {
                i++;
            }
            String[] bilgi = new String[i];
            i = 0;
            resultSet = statement.executeQuery(sorgu);
            while (resultSet.next()) {
                bilgi[i] = resultSet.getString("SicilNo");
                i++;
            }
            conn.close();
            return bilgi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean PersonelGuncelle(String[] gelen) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            Statement statement = conn.createStatement();
            //TODO
            //01354
            String sql = "exec PersonelGuncelle1 '" + gelen[0] + "','" + gelen[1] + "','" + gelen[3] + "','" + gelen[5] + "','" + gelen[4] + "','" + gelen[6]+"'";

            statement.executeUpdate(sql);
            String ezq = "exec PersonelGuncelle2 '" + gelen[2] + "','" + gelen[6]+"'";
            statement.executeUpdate(ezq);
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println("Guncelleme Hatasi! ");
            System.err.println(e.getMessage());
        }

        return false;
    }

    public boolean PersonelSil(int neyi) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            Statement statement = conn.createStatement();

            String sql = "exec PersonelSil " + neyi;
            statement.executeUpdate(sql);

            sql = "exec PersonelSil2 " + neyi;

            statement.executeUpdate(sql);

            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println("Guncelleme Hatasi! ");
            System.err.println(e.getMessage());
        }

        return false;
    }

}
