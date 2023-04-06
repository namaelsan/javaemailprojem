import javax.mail.*;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mail {

    public static void gonder(int saglayici,String email,String sifre,int hedefGrup,String konu,String mesaj) {

        String hedef[];
        if (hedefGrup==1){
            hedef=filex.readG();
        }
        else
            hedef=filex.readE();

        Properties prop = new Properties();

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
