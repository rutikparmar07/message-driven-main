public class Consumer extends Thread {
    private MessageQueue messageQueue;
    private MessageLogger messageLogger;

    public Consumer(MessageQueue messageQueue, MessageLogger messageLogger) {
        this.messageQueue = messageQueue;
        this.messageLogger = messageLogger;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = messageQueue.consume();
                if (message == null) {
                    // No more messages and producer is done
                    break;
                }
                processMessage(message);
                messageLogger.successIncrement();
            } catch (Exception e) {
                messageLogger.errorIncrement();
            }
        }
        System.out.println("Consumer has finished processing.");
    }

    private void processMessage(String message) throws Exception {
        if (Math.random() > 0.8) {
            throw new Exception("Failed to process message: " + message);
        }
        System.out.println("Consumed: " + message);
    }

}
