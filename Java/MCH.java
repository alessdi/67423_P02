import java.util.Random;

public class MCH {
    // Variable compartida
    private static long globalCount = 0;
    private static final Object lock = new Object();

    // CLASE INTERNA PARA EL HILO
    static class MonteCarloPI extends Thread {
        private final int numSamples;

        public MonteCarloPI(int numSamples) {
            this.numSamples = numSamples;
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
                globalCount += localCount;
            }
        }
    }
    
    
    public static void main(String[] args) {
        int totalSamples = 10_000_000;
        int numThreads = 4; // Usa el número de hilos que necesites
        int samplesPerThread = totalSamples / numThreads;

        Thread[] threads = new Thread[numThreads];

        long tiempoInicio = System.nanoTime();

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new MonteCarloPI(samplesPerThread);
            threads[i].start();
        }

        try {
            for (int i = 0; i < numThreads; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long tiempoFin = System.nanoTime();
        double tiempoParalelo = (tiempoFin - tiempoInicio) / 1_000_000.0;
        
        System.out.println("--- Versión Paralela ---");
        System.out.printf("Tiempo Paralelo: %.2f ms%n", tiempoParalelo);
    }
}