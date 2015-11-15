package message;

import reciver.MessageReciver;
import sender.MessageSender;


/**
 * Created by haifeng on 2015/11/6.
 */
public class SmsBaseMessage extends BaseMessage {
    public SmsBaseMessage(String sender,String reciver){
        super(new MessageSender(sender),new MessageReciver(reciver));
    }

    public SmsBaseMessage(String content, String messageSender,String messageReciver){
        super(content,new MessageSender(messageSender),new MessageReciver(messageReciver));
    }
}
