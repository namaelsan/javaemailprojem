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