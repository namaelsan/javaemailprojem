import java.util.Scanner;
import java.io.*;

public class Main{
    static geneluye g[]=new geneluye[50];
    static elituye e[]=new elituye[50];
    public static Scanner scan=new Scanner(System.in);
    public static void main(String argv[]) {
        File f=new File("uye.txt");
        if (!f.exists()){
            try {
                FileWriter fw=new FileWriter("uye.txt");
                fw.write("#GENEL UYELER\n#ELIT UYELER\n");
                fw.close();
            } catch (IOException e) {
                System.out.println("Dosya olusturulurken bir hata olustu.");
            }
        }
        
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
        System.out.println("\n1-Outlook/Hotmail\n2-Yahoo");
        System.out.println("\nEmail saglayicinizi seciniz:");
        int inp=scan.nextInt();
        if (inp==2)
            System.out.println("Yahoo ile giris yapmak icin guvenlik ayarlarindan bu uygulamaya ozel bir sifre almaniz gerekmektedir.");
        else if(inp!=1){
            System.out.println("Yanlis bir deger girdiniz");
            return;
        }
        scan.nextLine();
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

        mail.gonder(inp,email,sifre,hedefGrup,konu,mesaj);   
    }
}""