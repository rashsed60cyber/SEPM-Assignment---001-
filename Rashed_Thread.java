import java.util.concurrent.atomic.AtomicLong;

public class Rashed_Thread extends Thread {

    static AtomicLong safeCounter = new AtomicLong(0);
    static long unsafeCounter = 0;

    long instanceCounter = 0;
    int increments;
    boolean safe;

    Rashed_Thread(int increments, boolean safe) {
        this.increments = increments;
        this.safe = safe;
    }

    public void run() {
        for (int i = 0; i < increments; i++) {
            if (safe) {
                safeCounter.incrementAndGet();
            } else {
                unsafeCounter++;
            }

            instanceCounter++;
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        if (args.length != 3) {
            System.out.println(
                "Usage: java Rashed_Thread threads increments true/false"
            );
            return;
        }

        int numberOfThreads = Integer.parseInt(args[0]);
        int increments = Integer.parseInt(args[1]);

        if (numberOfThreads <= 0 || increments < 0) {
            System.out.println("Invalid thread or increment count.");
            return;
        }

        if (!args[2].equalsIgnoreCase("true")
                && !args[2].equalsIgnoreCase("false")) {
            System.out.println("Mode must be true or false.");
            return;
        }

        boolean safe = Boolean.parseBoolean(args[2]);

        safeCounter.set(0);
        unsafeCounter = 0;

        Rashed_Thread[] threads =
                new Rashed_Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Rashed_Thread(increments, safe);
        }

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i].start();
        }

        long nonStaticTotal = 0;

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i].join();
            nonStaticTotal += threads[i].instanceCounter;
        }

        long staticTotal;

        if (safe) {
            staticTotal = safeCounter.get();
        } else {
            staticTotal = unsafeCounter;
        }

        long expected = (long) numberOfThreads * increments;
        long difference = Math.abs(staticTotal - nonStaticTotal);

        double percentage = nonStaticTotal == 0
                ? 0.0
                : difference * 100.0 / nonStaticTotal;

        System.out.println("Mode: " + (safe ? "SAFE" : "UNSAFE"));
        System.out.println("Threads: " + numberOfThreads);
        System.out.println("Increments per thread: " + increments);
        System.out.println("Expected count: " + expected);
        System.out.println("Static count: " + staticTotal);
        System.out.println("Non-static total: " + nonStaticTotal);
        System.out.println("Absolute difference: " + difference);

        System.out.printf(
            java.util.Locale.US,
            "Difference: %.4f%%%n",
            percentage
        );
    }
}