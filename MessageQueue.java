import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    private Queue<String> queue = new LinkedList<>();
    private int capacity;
    private boolean finished = false;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(String message) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(message);
        notifyAll();
    }

    public synchronized String consume() throws InterruptedException {

        while (queue.isEmpty() && !isFinished()) {
            wait();
        }
        String message = queue.poll();
        notifyAll();
        return message;
    }

    public synchronized void finish() {
        finished = true;
        notify();  // Wake up consumer in case it's waiting for new messages
    }

    public boolean isFinished() {
        return finished;
    }
}
