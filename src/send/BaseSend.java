package send;
import message.*;
import reciver.Reciver;
import sender.Sender;

/**
 * Created by haifeng on 2015/11/6.
 */
public class BaseSend {
    public BaseSend(){

    }

    public void send(BaseMessage message){
        send(message,message.getReciver());
    }

    public void send(BaseMessage message,Reciver reciver){
        send(message,message.getSender(),reciver);
    }

    public void send(BaseMessage message,Sender sender,Reciver reciver){
        if(message.getClass().isAssignableFrom(SmsBaseMessage.class)){
            System.out.print("This is a SMS from "+ sender.getInformation()+ " to " + reciver.getInformation() +
                    ". The content is '" + message.getContent()+ "'.\n");
        }else if(message.getClass().isAssignableFrom(HiMessage.class)){
            System.out.print("This is a Hi from "+ sender.getInformation()+ " to " + reciver.getInformation() +
                    ". The content is '" + message.getContent()+ "'.\n");
        }else if(message.getClass().isAssignableFrom(EmailMessage.class)){
            System.out.print("This is a Email from "+ sender.getInformation()+ " to " + reciver.getInformation() +
                    ". The content is '" + message.getContent()+ "'.\n");
        }else{
            System.out.print("This is a "+message.getClass().getName()+" from "+ sender.getInformation()+ " to " + reciver.getInformation() +
                    ". The content is '" + message.getContent()+ "'.\n");
        }

    }
}
