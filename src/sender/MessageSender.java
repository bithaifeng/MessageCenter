package sender;

/**
 * Created by haifeng on 2015/11/6.
 */
public class MessageSender implements Sender{
    private String senderPhoneNumber;

    public MessageSender(String senderPhoneNumber){
        this.senderPhoneNumber = senderPhoneNumber;
    }

    @Override
    public void setInformation(String information){
        this.senderPhoneNumber = information;
    }

    @Override
    public String getInformation(){
        return this.senderPhoneNumber;
    }
}
