package message;

import reciver.EmailReciver;
import sender.EmailSender;

/**
 * Created by haifeng on 2015/11/6.
 */
public class EmailMessage extends BaseMessage{
    public EmailMessage(String sender,String reciver){
        super(new EmailSender(sender),new EmailReciver(reciver));
    }

    public EmailMessage(String content, String emailSender,String emailReciver){
        super(content,new EmailSender(emailSender),new EmailReciver(emailReciver));
    }
}
