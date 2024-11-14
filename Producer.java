public class Producer extends Thread {
    private MessageQueue queue;
    private int messageCount;

    public Producer(MessageQueue queue, int messageCount) {
        this.queue = queue;
        this.messageCount = messageCount;
    }

    @Override
    public void run() {
        for (int i = 0; i < messageCount; i++) {
            try {
                String message = "Message" + (i + 1);
                queue.produce(message);
                System.out.println("Produced: " + message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.finish();
        System.out.println("Producer finished producing messages.");
    }
}
