public class Test {
    public static void main(String[] args) {
        ManualTest tests = new ManualTest();

        int passed = 0;
        int failed = 0;
        try {
            // Use reflection to invoke test methods
            tests.testSuccessfulProcessing();
            passed++;
        } catch (Exception e) {
            System.out.println("testSuccessfulProcessing" + " failed: " + e.getCause().getMessage());
            failed++;
        }
        try {
            tests.testErrorTracking();
            passed++;
        } catch (Exception e) {
            System.out.println("testErrorTracking" + " failed: " + e.getCause().getMessage());
            failed++;
        }

        try {
            tests.testMessageQueue();
            passed++;
        } catch (Exception e) {
            System.out.println("testMessageQueue" + " failed: " + e.getCause().getMessage());
            failed++;
        }
        System.out.println("Tests passed: " + passed);
        System.out.println("Tests failed: " + failed);
    }
}
