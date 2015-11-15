package reciver;

/**
 * Created by haifeng on 2015/11/6.
 */
public class HiReciver implements Reciver{

    private String reciverHiNumber;

    public HiReciver(String reciverHiNumber){
        this.reciverHiNumber = reciverHiNumber;
    }

    @Override
    public void setInformation(String information){
        this.reciverHiNumber = information;
    }

    @Override
    public String getInformation(){
        return this.reciverHiNumber;
    }
}
