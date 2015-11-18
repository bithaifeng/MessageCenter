/**
 * Created by haifeng on 2015/11/10.
 */

import message.*;

public class Client {
    public static void main(String[] args) {
        MessageCenter center = MessageCenter.getInstance();


        for (int i = 0 ;i < 20; i++){
            EmailMessage emailMessage = new EmailMessage("LiHaifeng","EmailSomeOne");
            emailMessage.setContent("This is the " + i + "th");
            center.addMessage(emailMessage);
        }
        center.startCenter();
        try {
            Thread.sleep(2000);
        }
        catch(Exception e) {
        }

        center.suspendCenter();

        center.reStartCenter();

        for (int i = 0 ;i < 20; i++){
            HiMessage hiMessage = new HiMessage("LiHaifeng","HiSomeOne");
            hiMessage.setContent("This is the " + i + "th");
            center.addMessage(hiMessage);
        }

        for (int i = 0 ;i < 20; i++){
            SmsBaseMessage smsMessage = new SmsBaseMessage("LiHaifeng","SmsSomeOne");
            smsMessage.setContent("This is the " + i + "th");
            center.addMessage(smsMessage);
        }


    }
}
