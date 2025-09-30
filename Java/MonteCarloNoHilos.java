import java.util.Random;

public class MonteCarloNoHilos {
    public static void main(String[] args) {
        int totalSamples = 10_000_000;
        long sequentialCount = 0;
        Random rand = new Random();

        long tiempoInicio = System.nanoTime();

        for (int i = 0; i < totalSamples; i++) {
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            if (x * x + y * y <= 1.0) {
                sequentialCount++;
            }
        }

        long tiempoFin = System.nanoTime();
        double tiempoSecuencial = (tiempoFin - tiempoInicio) / 1_000_000.0;
        
        System.out.println("--- Versión Secuencial ---");
        System.out.printf("Tiempo Secuencial: %.2f ms%n", tiempoSecuencial);
    }
}