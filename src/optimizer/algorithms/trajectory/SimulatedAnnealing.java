package optimizer.algorithms.trajectory;

import optimizer.algorithms.Algorithm;
import optimizer.model.Evaluator;
import optimizer.model.Instance;
import optimizer.model.Solution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SimulatedAnnealing implements Algorithm {

    @Override
    public Solution run(Instance instance) {
        // Generar solución inicial aleatoria (o podría usar GRASP)
        Solution initialSolution = new RandomAlgorithm().run(instance);
        return simulatedAnnealing(initialSolution, instance);
    }

    public Solution simulatedAnnealing(Solution initialSolution, Instance instance) {
        double t0 = 1000.0;
        double alpha = 0.95;
        int nRep = 100;
        double minTemperature = 0.01;

        Solution currentSolution = initialSolution.deepCopy();
        Solution bestSolution = currentSolution.deepCopy();
        double currentCost = Evaluator.evaluate(currentSolution, instance);
        double bestCost = currentCost;
        double temperature = t0;

        while (temperature > minTemperature) {
            for (int i = 0; i < nRep; i++) {
                Solution neighbor = generateNeighbor(currentSolution);
                double neighborCost = Evaluator.evaluate(neighbor, instance);

                double delta = neighborCost - currentCost; // Maximización: delta positivo es bueno

                // Aceptamos si mejora (delta > 0) o por probabilidad de Boltzmann si empeora
                if (delta > 0 || Math.random() < Math.exp(delta / temperature)) {
                    currentSolution = neighbor.deepCopy();
                    currentCost = neighborCost;

                    if (currentCost > bestCost) {
                        bestSolution = currentSolution.deepCopy();
                        bestCost = currentCost;
                    }
                }
            }
            temperature *= alpha;
        }
        return bestSolution;
    }

    private Solution generateNeighbor(Solution currentSolution) {
        boolean[] arr = Arrays.copyOf(currentSolution.solution(), currentSolution.solution().length);
        List<Integer> trueIdx = new ArrayList<>();
        List<Integer> falseIdx = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i]) trueIdx.add(i); else falseIdx.add(i);
        }

        Random rand = new Random();
        int toTrue = falseIdx.get(rand.nextInt(falseIdx.size()));
        int toFalse = trueIdx.get(rand.nextInt(trueIdx.size()));

        arr[toTrue] = true;
        arr[toFalse] = false;
        return new Solution(arr);
    }

    @Override
    public String toString() { return "Simulated Annealing"; }
}