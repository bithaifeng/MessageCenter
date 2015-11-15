package message;

import reciver.Reciver;
import sender.Sender;

/**
 * Created by haifeng on 2015/11/6.
 */
public class BaseMessage {
    private Reciver reciver;
    private Sender sender;
    private String content;

    public BaseMessage(){

    }

    public BaseMessage(String content){
        setContent(content);
    }

    public BaseMessage(Sender sender, Reciver reciver){
        setSender(sender);
        setReciver(reciver);
    }

    public BaseMessage(String content, Sender sender, Reciver reciver){
        setContent(content);
        setSender(sender);
        setReciver(reciver);
    }

    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setReciver(Reciver reciver){
        this.reciver = reciver;
    }
    public void setSender(Sender sender){
        this.sender = sender;
    }
    public Reciver getReciver(){
        return this.reciver;
    }
    public Sender getSender(){
        return this.sender;
    }
}
