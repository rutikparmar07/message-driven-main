public class MessageLogger {
    private int successCount = 0;
    private int errorCount = 0;

    public synchronized void successIncrement() {
        successCount++;
    }

    public synchronized int getSuccessCount() {
        return successCount;
    }

    public synchronized void errorIncrement() {
        errorCount++;
    }

    public synchronized int getErrorCount() {
        return errorCount;
    }
}
