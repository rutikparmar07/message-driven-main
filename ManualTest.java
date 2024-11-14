class ManualTest {
    private MessageQueue queue;
    private MessageLogger logger;

    public ManualTest() {
        // Initialize components
        queue = new MessageQueue(5);
        logger = new MessageLogger();
    }

    // Test for successful message processing
    public void testSuccessfulProcessing() throws Exception {
        queue.produce("Test Message 1");
        queue.produce("Test Message 2");
        queue.finish();

        // Start the consumer to process messages
        Consumer consumer = new Consumer(queue, logger);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        // Allow time for processing
        consumerThread.join(500);

        // Check if all messages are either processed or logged as errors
        if (logger.getSuccessCount() + logger.getErrorCount() != 2) {
            throw new Exception("Total processed messages did not match the expected count.");
        }
        System.out.println("testSuccessfulProcessing passed");
    }

    // Test for error incrementing in the logger
    public void testErrorTracking() throws Exception {
        // Simulate an error increment
        logger.errorIncrement();

        if (logger.getErrorCount() != 1) {
            throw new Exception("Error count did not increment correctly.");
        }
        System.out.println("testErrorTracking passed");
    }

    // Test for message queue producing and consuming
    public void testMessageQueue() throws Exception {
        queue.produce("Message A");

        // Verify the message is in the queue
        String message = queue.consume();
        if (!"Message A".equals(message)) {
            throw new Exception("MessageQueue did not correctly store or retrieve the message.");
        }
        System.out.println("testMessageQueue passed");
    }
}
