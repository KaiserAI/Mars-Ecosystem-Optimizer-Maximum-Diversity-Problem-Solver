package optimizer.algorithms.population;

import optimizer.algorithms.local.TimedLocalSearch;
import optimizer.model.Instance;
import optimizer.model.Solution;
import java.util.concurrent.TimeUnit;

public class MemeticAlgorithm extends AbstractGeneticAlgorithm {

    private static final long MAX_RUNTIME_MS = TimeUnit.SECONDS.toMillis(40);
    private long deadlineMillis;

    public MemeticAlgorithm() {
        super(50, 100, 2, 0.95, 0.15, 2);
    }

    @Override
    public Solution run(Instance instance) {
        this.deadlineMillis = System.currentTimeMillis() + MAX_RUNTIME_MS;
        return super.run(instance);
    }

    @Override
    protected Solution[] breed(Solution p1, Solution p2, Instance instance) {
        if (random.nextDouble() >= crossoverRate) {
            return new Solution[]{ p1, p2 };
        }
        Solution c1 = greedyCompletionCrossover(p1, p2, instance);
        Solution c2 = greedyCompletionCrossover(p2, p1, instance);

        return new Solution[]{ improve(c1, instance), improve(c2, instance) };
    }

    private Solution improve(Solution sol, Instance inst) {
        // 1. Mutar
        boolean[] mutated = mutate(sol.solution());
        Solution cand = new Solution(mutated);

        // 2. Local Search con tiempo
        if (System.currentTimeMillis() > deadlineMillis) return cand;

        if (random.nextDouble() < 0.5) {
            return TimedLocalSearch.firstImprovement(cand, inst, deadlineMillis);
        } else {
            return TimedLocalSearch.bestImprovement(cand, inst, deadlineMillis);
        }
    }

    @Override
    public String toString() { return "Memetic Algorithm"; }
}