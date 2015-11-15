package sender;

/**
 * Created by haifeng on 2015/11/6.
 */
public class HiSender implements Sender {

    private String senderHiNumber;

    public HiSender(String senderHiNumber){
        this.senderHiNumber = senderHiNumber;
    }

    @Override
    public void setInformation(String information){
        this.senderHiNumber = information;
    }

    @Override
    public String getInformation(){
        return this.senderHiNumber;
    }
}
