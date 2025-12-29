package optimizer.algorithms.population;

import optimizer.algorithms.Algorithm;
import optimizer.model.Evaluator;
import optimizer.model.Instance;
import optimizer.model.Solution;
import java.util.*;

public abstract class AbstractGeneticAlgorithm implements Algorithm {

    protected final int populationSize;
    protected final int numGenerations;
    protected final int tournamentSize;
    protected final double crossoverRate;
    protected final double mutationRate;
    protected final int eliteCount;
    protected final Random random;

    protected AbstractGeneticAlgorithm(int popSize, int generations, int tournament, double crossRate, double mutRate, int elite) {
        this.populationSize = popSize;
        this.numGenerations = generations;
        this.tournamentSize = tournament;
        this.crossoverRate = crossRate;
        this.mutationRate = mutRate;
        this.eliteCount = elite;
        this.random = new Random();
    }

    @Override
    public Solution run(Instance instance) {
        List<Solution> population = initializePopulation(instance);

        for (int gen = 0; gen < numGenerations; gen++) {
            List<Solution> newPop = new ArrayList<>();

            // Generar descendencia
            while (newPop.size() < populationSize - eliteCount) {
                Solution p1 = tournamentSelection(population, instance);
                Solution p2 = tournamentSelection(population, instance);
                Solution[] offspring = breed(p1, p2, instance); // Abstracto

                for (Solution child : offspring) {
                    boolean[] mutated = mutate(child.solution());
                    newPop.add(new Solution(mutated));
                    if (newPop.size() >= populationSize - eliteCount) break;
                }
            }

            // Elitismo
            population.sort((a, b) -> Double.compare(Evaluator.evaluate(b, instance), Evaluator.evaluate(a, instance)));
            for (int i = 0; i < eliteCount; i++) {
                newPop.add(population.get(i));
            }
            population = newPop;
        }

        return population.stream()
                .max(Comparator.comparingDouble(s -> Evaluator.evaluate(s, instance)))
                .orElse(population.get(0));
    }

    protected List<Solution> initializePopulation(Instance instance) {
        List<Solution> pop = new ArrayList<>();
        int half = populationSize / 2;
        // Mitad aleatoria
        for(int i=0; i<half; i++) pop.add(randomSolution(instance));
        // Mitad greedy
        for(int i=half; i<populationSize; i++) pop.add(greedySolution(instance));
        return pop;
    }

    // Métodos auxiliares de inicialización y operadores genéticos comunes (mutate, tournamentSelection)
    // NOTA: Copiar aquí el código de 'initializePopulation', 'randomSolution', 'greedySolution', 'tournamentSelection', 'mutate'
    // y 'greedyCompletionCrossover' tal cual los tienes en tu archivo AbstractGeneticAlgorithm.java original.
    // Solo asegúrate de importar optimizer.model.* // ... (Implementaciones de randomSolution, greedySolution, tournamentSelection, greedyCompletionCrossover, mutate) ...

    protected abstract Solution[] breed(Solution p1, Solution p2, Instance instance);

    // Implementación rápida de dependencias para que compile este ejemplo:
    protected Solution randomSolution(Instance instance) {
        // Usar lógica de RandomAlgorithm
        return new optimizer.algorithms.trajectory.RandomAlgorithm().run(instance);
    }

    protected Solution greedySolution(Instance instance) {
        // Implementación básica del greedy que tenías
        return new optimizer.algorithms.trajectory.GraspAlgorithm().run(instance);
    }

    protected Solution tournamentSelection(List<Solution> pop, Instance inst) {
        Solution best = null;
        double bestVal = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < tournamentSize; i++) {
            Solution cand = pop.get(random.nextInt(pop.size()));
            double val = Evaluator.evaluate(cand, inst);
            if (val > bestVal) { best = cand; bestVal = val; }
        }
        return best;
    }

    protected boolean[] mutate(boolean[] sol) {
        boolean[] m = Arrays.copyOf(sol, sol.length);
        if (random.nextDouble() < mutationRate) {
            // Lógica simple de swap
            List<Integer> ones = new ArrayList<>(), zeros = new ArrayList<>();
            for (int i = 0; i < m.length; i++) { if(m[i]) ones.add(i); else zeros.add(i); }
            if(!ones.isEmpty() && !zeros.isEmpty()) {
                m[ones.get(random.nextInt(ones.size()))] = false;
                m[zeros.get(random.nextInt(zeros.size()))] = true;
            }
        }
        return m;
    }

    protected Solution greedyCompletionCrossover(Solution p1, Solution p2, Instance inst) {
        // Implementación que tenías en el archivo original
        boolean[] sA = p1.solution();
        boolean[] sB = p2.solution();
        boolean[] child = new boolean[sA.length];
        List<Integer> selected = new ArrayList<>();
        // 1. Intersección
        for(int i=0; i<sA.length; i++) {
            if(sA[i] && sB[i]) { child[i]=true; selected.add(i); }
        }
        // 2. Relleno (Simplificado para el ejemplo, usar el completo si se requiere precisión)
        int m = inst.getNumberOfItemsToPick();
        while(selected.size() < m) {
            int bestIdx = -1; double bestGain = -1;
            for(int i=0; i<sA.length; i++) {
                if(!child[i]) {
                    double g = 0;
                    for(int sel : selected) g += inst.getDistance(i, sel);
                    if(g > bestGain) { bestGain = g; bestIdx = i; }
                }
            }
            if(bestIdx != -1) { child[bestIdx]=true; selected.add(bestIdx); }
            else break;
        }
        return new Solution(child);
    }
}