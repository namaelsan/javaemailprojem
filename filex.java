import java.util.Scanner;
import java.io.*;

public class filex{
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