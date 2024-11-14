
public class MessageDrivenQueueApp {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(5);
        MessageLogger messageLogger = new MessageLogger();

        Producer producer = new Producer(messageQueue,100);
        Consumer consumer = new Consumer(messageQueue, messageLogger);

        producer.start();
        consumer.start();
        try {
            producer.join();
            consumer.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Success Count: "+ messageLogger.getSuccessCount());
        System.out.println("Error Count: "+messageLogger.getErrorCount());
    }
}

