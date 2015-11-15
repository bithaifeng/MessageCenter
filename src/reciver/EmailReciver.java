package reciver;



/**
 * Created by haifeng on 2015/11/6.
 */
public class EmailReciver implements Reciver {

    private String reciverAddress;

    public EmailReciver(String reciverAddress){
        this.reciverAddress = reciverAddress;
    }

    @Override
    public void setInformation(String information){
        this.reciverAddress = information;
    }

    @Override
    public String getInformation(){
        return this.reciverAddress;
    }
}
