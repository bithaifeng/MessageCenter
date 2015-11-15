package sender;

/**
 * Created by haifeng on 2015/11/6.
 */
public class EmailSender implements Sender {
    private String senderAddress;

    public EmailSender(String senderAddress){
        this.senderAddress = senderAddress;
    }

    @Override
    public void setInformation(String information){
        this.senderAddress = information;
    }

    @Override
    public String getInformation(){
        return this.senderAddress;
    }
}
