
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

    public synchronized void startCenter(){
        if(null == this.sendThread){
            sendThread = new SendThread();
            this.sendThread.start();
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
//            System.console().printf("Successful!!Center suspends!!");
        }
        else{
//            System.console().printf("Failed!!Center is suspended!!");
        }
    }

    public void reStartCenter(){
        if(this.sendThread.isSuspend()){
//            System.console().printf("Failed!!Center is running!!");
        }
        else{
            this.sendThread.startCenter();
//            System.console().printf("Successful!!Center restarts!!");
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
            while(!sendQueue.isEmpty()){
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
//                System.console().printf("Failed!!SendThread is already running!!");
            }
            else{
                this.isStart = true;
//                System.console().printf("Successful!!SendThread restarts!! Please wait for 2 seconds");
//                System.console().printf("......");

            }
        }

        public boolean isSuspend(){
            return this.isStart;
        }

        public void suspendCenter(){
            if(this.isStart){
                this.isStart = false;
//                System.console().printf("Successful!!SendThread suspends!!");
            }
            else{
//                System.console().printf("Failed!!SendThread is already suspended!!");
            }
        }
    }

}
