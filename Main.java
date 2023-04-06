import java.util.Scanner;
import java.io.*;
import javax.mail.*;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Main{
    public static Scanner scan=new Scanner(System.in);
    public static void main(String argv[]) {
        File f=new File("uye.txt");
        //dosyanın var olup olmadığına bakar
        if (!f.exists()){
            try {
                FileWriter fw=new FileWriter("uye.txt");
                //dosya yoksa oluşturup içine template yazar
                fw.write("#GENEL UYELER\n#ELIT UYELER\n");
                fw.close();
            } catch (IOException e) {
                System.out.println("Dosya olusturulurken bir hata olustu.");
            }
        }

        //giriş menüsü döngü içinde oluşturulur
        int inp;
        while (true){
            System.out.println("1-Elit Üye Ekleme\n2-Genel Uye Ekleme\n3-Mail Gonderme\n4-Cikis\n");
            inp = scan.nextInt();
            scan.nextLine();
                switch (inp){
                case 1:
                    uye.euyeekleme();
                    break;
                case 2:
                    uye.guyeekleme();
                    break;
                case 3:
                    mailmenu();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Gecersiz bir deger girdiniz.\n");
            }
        }
    }

    public static void mailmenu(){

        int hedefGrup;
        String email="",sifre="",konu="",mesaj="";

        //emailin yollanması için gerekli bilgiler alınır
        System.out.println("\n1-Outlook/Hotmail\n2-Yahoo");
        System.out.println("\nEmail saglayicinizi seciniz:");
        int inp=scan.nextInt();
        if (inp==2)
            System.out.println("Yahoo ile giris yapmak icin guvenlik ayarlarindan bu uygulamaya ozel bir sifre almaniz gerekmektedir.");
        else if(inp!=1){
            System.out.println("Yanlis bir deger girdiniz");
            return;
        }
        scan.nextLine();//stdin temizler

        System.out.println("Eposta adresiniz:");
        email=scan.nextLine().trim().toLowerCase();
        System.out.println("Sifreniz:");
        sifre=scan.nextLine();
        System.out.println("Mesajinizin konusu:");
        konu=scan.nextLine();
        System.out.println("Mesajiniz:");
        mesaj=scan.nextLine();
        System.out.println("\n\n1-Genel Uyeler\n2-Elit Uyeler\n3-Hepsi\nMesaji gondermek istediginiz grubu giriniz:");
        hedefGrup=scan.nextInt();

        //mesajı yollayan fonksiyon çağırılır
        mail.gonder(inp,email,sifre,hedefGrup,konu,mesaj);   
    }
}



class mail {

    public static void gonder(int saglayici,String email,String sifre,int hedefGrup,String konu,String mesaj) {

        String hedef[];
        if (hedefGrup==1){//hedeflenen gruba göre hedef arrayi belirlenir
            hedef=filex.readG();
        }
        else if(hedefGrup==2)
            hedef=filex.readE();
        else{
            hedef=filex.readAll();
        }

        Properties prop = new Properties();

        //email ile ilgili özellikler seçilen sağlayıcıya göre belirlenir
        switch (saglayici){
            case 1:
                prop.put("mail.smtp.host", "smtp.office365.com");
                prop.put("mail.smtp.port", "587");
                prop.put("mail.smtp.auth", "true");
                prop.put("mail.smtp.starttls.enable", "true");
                break;
            case 2:
                prop.put("mail.smtp.host", "smtp.mail.yahoo.com");
                prop.put("mail.smtp.port", "587");
                prop.put("mail.smtp.auth", "true");
                prop.put("mail.smtp.starttls.enable", "true");
        }

        //emailin gönderilmesi için gerekli
        Session session = Session.getInstance(prop,new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {return new PasswordAuthentication(email, sifre);} 
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(email));

            InternetAddress[] IAhedef = new InternetAddress[filex.i];
            for (int i=0; i<filex.i; i++) {
                IAhedef[i]=new InternetAddress(hedef[i]);
            }

            msg.setRecipients(Message.RecipientType.TO, IAhedef);
            msg.setSubject(konu);
            msg.setText(mesaj);

            Transport.send(msg);
            System.out.println("Emailiniz basariyla iletilti.");

        } catch (MessagingException e) {
            System.out.println("Mesajiniz yollanamadi");
        }
    }
}


class filex{
    static int i;
    public static String[] readG() {
        File f=new File("uye.txt");
        String genel[]=new String[50];
        String satir;
        String[] word=new String[3];
        int n=0;
        i=0;
        try {
            Scanner scan=new Scanner(f);
            while (scan.hasNextLine() && n!=2){
                satir=scan.nextLine();
                if (satir.toCharArray()[0]=='#'){
                    n++;
                }
                else{
                    word=satir.split("\t");
                    genel[i]=word[2];
                    i++;
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Dosya olusturulurken hata olustu.");
        }
        return genel;
    }

    public static String[] readE() {
        int n=0;
        i=0;
        File f= new File("uye.txt");
        String elit[]=new String[50];
        String satir,word[]=new String[3];
        try {
            Scanner scan=new Scanner(f);
            while (scan.hasNextLine()){
                satir=scan.nextLine();
                if (satir.toCharArray()[0]=='#') n++;
                if (n==2){
                    while (scan.hasNextLine()){
                        satir=scan.nextLine();
                        word=satir.split("\t");
                        elit[i]=word[2];
                        i++;
                    }
                    break;
                }
            }
            scan.close();
        } catch (FileNotFoundException e){
            System.out.println("Dosya olusturulurken hata olustu.");
        }
        return elit;
    }

    public static String[] readAll() {
        File f=new File("uye.txt");
        String all[]=new String[50];
        String satir;
        String[] word=new String[3];
        i=0;
        try {
            Scanner scan=new Scanner(f);
            while (scan.hasNextLine()){
                satir=scan.nextLine();
                if (satir.toCharArray()[0]=='#') continue;
                else{
                    word=satir.split("\t");
                    all[i]=word[2];
                    i++;
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Dosya olusturulurken hata olustu.");
        }
        return all;
    }


    public static void writeE(elituye x){
        try{
            FileWriter fw=new FileWriter("uye.txt",true);
            String exp=x.ad+"\t"+x.soyad+"\t"+x.email+'\n';
            fw.write(exp);
            fw.close();
        } catch (IOException e){
            System.out.println("Dosya olusturulurken hata olustu.");
        }
    }

    public static void writeG(geneluye x){
        File f=new File("uye.txt");
        String dosyag="",dosyae="",satir="";
        int n=0;
        try {
            Scanner scan=new Scanner(f);
            while (scan.hasNextLine()){
                satir=scan.nextLine();
                if (satir.toCharArray()[0]=='#'){
                    n++;
                    if (n==2){
                        dosyae=dosyae.concat(satir+"\n");
                        while(scan.hasNextLine()){
                            satir=scan.nextLine();
                            dosyae=dosyae.concat(satir+"\n");
                        }
                        break;
                    }
                }
                dosyag=dosyag.concat(satir+"\n");
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Dosya olusturulurken hata olustu.");
        }
        try{
            FileWriter fw=new FileWriter("uye.txt");
            String exp=dosyag+x.ad+"\t"+x.soyad+"\t"+x.email+'\n'+dosyae;
            fw.write(exp);
            fw.close();
        } catch (IOException e){
            System.out.println("Dosya olusturulurken hata olustu.");
        }
    }
}

class uye{
    uye(String ad,String soyad,String email){
        this.ad=ad;
        this.soyad=soyad;
        this.email=email;
    };
    public String ad,soyad,email;

    public static void guyeekleme(){
        String ad,soyad,email;
        System.out.print("Uyenin Adi:");
        ad=Main.scan.nextLine().trim();
        System.out.print("Uyenin Soyadi:");
        soyad=Main.scan.nextLine().trim();
        System.out.print("Uyenin Emaili:");
        email=Main.scan.nextLine().trim().toLowerCase();
        filex.writeG(new geneluye(ad, soyad, email));
    }

    public static void euyeekleme(){
        String ad,soyad,email;
        System.out.print("Uyenin Adi:");
        ad=Main.scan.nextLine().trim();
        System.out.print("Uyenin Soyadi:");
        soyad=Main.scan.nextLine().trim();
        System.out.print("Uyenin Emaili:");
        email=Main.scan.nextLine().trim().toLowerCase();
        filex.writeE(new elituye(ad, soyad, email));
    }
}

class geneluye extends uye{
    geneluye(String ad,String soyad,String email){
        super(ad,soyad,email);
    }
}

class elituye extends uye{
    elituye(String ad,String soyad,String email){
        super(ad,soyad,email);
    }
}