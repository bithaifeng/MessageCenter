package message;

import reciver.HiReciver;
import sender.HiSender;
import reciver.HiReciver;
import sender.HiSender;

/**
 * Created by haifeng on 2015/11/6.
 */
public class HiMessage extends BaseMessage{
    public HiMessage(String sender,String reciver){
        super(new HiSender(sender),new HiReciver(reciver));
    }

    public HiMessage(String content, String hiSender,String hiReciver){
        super(content,new HiSender(hiSender),new HiReciver(hiReciver));
    }
}
