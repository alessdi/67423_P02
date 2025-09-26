
import java.util.Random;

public class MCH {
    private static long globalCount = 0; 
    private static final Object lock = new Object();

    static class MonteCarloPI extends Thread {
        private final int numSamples;
        private final int threadId;

        public MonteCarloPI(int numSamples, int threadId) {
            this.numSamples = numSamples;
            this.threadId = threadId;
        }

        @Override 
        public void run() {
            Random rand = new Random();
            long localCount = 0;

            for (int i = 0; i < numSamples; i++) {
                double x = rand.nextDouble();
                double y = rand.nextDouble();
                if (x * x + y * y <= 1.0) {
                    localCount++;
                }
            }

            synchronized (lock) {
                System.out.println("Hilo " + threadId + ": añade " + localCount + " puntos al total");
                globalCount += localCount;
            }
        }
    }

    public static void main(String[] args) {
        int totalSamples = 1_000_000; 
        int numThreads = 4; 
        int samplesPerThread = totalSamples / numThreads;

        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new MonteCarloPI(samplesPerThread, i);
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        double piApprox = (4.0 * globalCount) / totalSamples;
        System.out.println("Número total de puntos: " + totalSamples);
        System.out.println("Puntos dentro del círculo: " + globalCount);
        System.out.println("Aproximación de pi: " + piApprox);
        System.out.println("Error: " + Math.abs(piApprox - Math.PI));
    }
}

// Alessandro Novelo 67423