package optimizer.main;

import optimizer.algorithms.Algorithm;
import optimizer.algorithms.population.GeneticAlgorithm;
import optimizer.algorithms.population.MemeticAlgorithm;
import optimizer.algorithms.trajectory.GraspAlgorithm;
import optimizer.algorithms.trajectory.RandomAlgorithm;
import optimizer.algorithms.trajectory.SimulatedAnnealing;
import optimizer.model.Evaluator;
import optimizer.model.Instance;
import optimizer.model.Solution;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void runTest(String instanceFolder, List<Algorithm> algorithms, String outputCsv) throws IOException {
        File folder = new File(instanceFolder);
        if (!folder.exists()) {
            System.err.println("Carpeta de instancias no encontrada: " + instanceFolder);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputCsv))) {
            writer.write("Instancia,Algoritmo,Score,Tiempo(s)\n");

            for (File file : folder.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    Instance instance = new Instance(file.getAbsolutePath());
                    System.out.println("--- Procesando: " + file.getName() + " ---");

                    for (Algorithm algo : algorithms) {
                        System.out.print("   Ejecutando " + algo.toString() + "... ");

                        Instant start = Instant.now();
                        Solution sol = algo.run(instance);
                        Instant end = Instant.now();

                        double score = Evaluator.evaluate(sol, instance);
                        double time = Duration.between(start, end).toMillis() / 1000.0;

                        System.out.println("Score: " + String.format("%.2f", score) + " [" + time + "s]");

                        writer.write(String.format("%s,%s,%.2f,%.4f\n",
                                file.getName(), algo.toString(), score, time));
                    }
                }
            }
        }
        System.out.println("\nResultados guardados en: " + outputCsv);
    }

    public static void main(String[] args) throws IOException {
        String instancePath = "src/resources/instances";

        List<Algorithm> algorithms = Arrays.asList(
                new RandomAlgorithm(),
                new GraspAlgorithm(),
                new SimulatedAnnealing(),
                new GeneticAlgorithm(),
                new MemeticAlgorithm()
        );

        runTest(instancePath, algorithms, "resultados_unificados.csv");
    }
}
