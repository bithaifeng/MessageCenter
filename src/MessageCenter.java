
import java.util.Queue;
import java.util.LinkedList;
import message.BaseMessage;
import send.BaseSend;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ExecutorService;

/**
 * Created by haifeng on 2015/11/7.
 */
public class MessageCenter {
    private static BlockingDeque<BaseMessage> sendQueue = new LinkedBlockingDeque<message.BaseMessage>();
    private static BlockingDeque<BaseMessage> reciveQueue = new LinkedBlockingDeque<message.BaseMessage>();
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

    public void startCenter(){
        if(null == this.sendThread){
            sendThread = new SendThread();
            this.sendThread.start();
        }
    }

    public static synchronized MessageCenter getInstance() {
        if (singleton == null) {
            synchronized (MessageCenter.class) {
                if (singleton == null) {
                    singleton = new MessageCenter();
                }
            }
        }
        return singleton;
    }

    public void suspendCenter(){
        if(executorService.isShutdown() != true) {
            System.console().printf("Center is shuting down!");
            executorService.shutdown();
        }
        else {
            System.console().printf("Center has shut down");
        }
    }

    public void addMessage(BaseMessage message){
        try{
            sendQueue.put(message);
        } catch (Exception e){

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
        @Override
        public void run(){
            while(!sendQueue.isEmpty()){
                try{
                    BaseMessage message = sendQueue.take();
                    send(message);
                    Thread.sleep(60000/SENDPERMINS);
                } catch (Exception e){

                }


            }
        }



    }

}
