package reciver;
import java.lang.String;
/**
 * Created by haifeng on 2015/11/6.
 */
public class MessageReciver implements Reciver{
    
    private String reciverPhoneNumber;

    public MessageReciver(String reciverPhoneNumber){
        this.reciverPhoneNumber = reciverPhoneNumber;
    }

    @Override
    public void setInformation(String information){
        this.reciverPhoneNumber = information;
    }

    @Override
    public String getInformation(){
        return this.reciverPhoneNumber;
    }
}
