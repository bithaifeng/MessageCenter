
import message.BaseMessage;
import send.BaseSend;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by haifeng on 2015/11/7.
 */
public class MessageCenter {
    private static BlockingDeque<BaseMessage> sendQueue = new LinkedBlockingDeque<message.BaseMessage>();
    static private Logger logger = LoggerFactory.getLogger(MessageCenter.class);
    private static Integer SENDPERMINS = 1000;
    private static BaseSend sender = new BaseSend();
    private Executor executor;
    ExecutorService executorService;
    private static MessageCenter singleton = null;
    private SendThread sendThread;

    private MessageCenter(){
        executor = Executors.newCachedThreadPool();
        executorService = (ExecutorService) executor;
    }

    public synchronized void startCenter(){
        if(null == this.sendThread){
            logger.info("MessageCenter is starting...");
            logger.info("Please wait for 2 seconds.");
            try {
                Thread.sleep(2000);
            }
            catch(Exception e) {
            }
            sendThread = new SendThread();
            this.sendThread.start();

        }
        else{
            logger.info("MessageCenter has started...");
        }
    }


    public static MessageCenter getInstance() {
        synchronized(MessageCenter.class) {
            if (singleton == null) {
                synchronized (MessageCenter.class) {
                    if (singleton == null) {
                        singleton = new MessageCenter();
                    }
                }
            }
        }
        return singleton;
    }

    public void suspendCenter(){
        if(this.sendThread.isSuspend()){
            this.sendThread.suspendCenter();
            logger.info("MessageCenter suspends!!");
        }
        else{
            logger.info("Failed!!Center is suspended!!");
        }
    }

    public void reStartCenter(){
        if(this.sendThread.isSuspend()){
            logger.info("Failed!!Center is running!!");
        }
        else{
            logger.info("MessageCenter is starting...");
            logger.info("Please wait for 2 seconds.");
            try {
                Thread.sleep(2000);
            }
            catch(Exception e) {
            }
            this.sendThread.startCenter();
            logger.info("Successful!!Center restarts!!");
            try {
                Thread.sleep(500);
            }
            catch(Exception e) {
            }
        }
    }

    public void addMessage(BaseMessage message){
        try{
            sendQueue.put(message);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void send(BaseMessage message){
        Runnable task = new Runnable(){
            @Override
            public void run(){
                sender.send(message);
            }
        };
        executor.execute(task);
    }


    class SendThread extends Thread{
        private boolean isStart = true;
        @Override
        public void run(){
            while(true){
                if(this.isStart) {
                    try {
                        BaseMessage message = sendQueue.take();
                        send(message);
                        Thread.sleep(60000 / SENDPERMINS);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void startCenter(){
            this.isStart = true;
            if(this.isStart){
            }
            else{
                this.isStart = true;

            }
        }

        public boolean isSuspend(){
            return this.isStart;
        }

        public void suspendCenter(){
            if(this.isStart){
                this.isStart = false;
            }
            else{
            }
        }
    }

}
