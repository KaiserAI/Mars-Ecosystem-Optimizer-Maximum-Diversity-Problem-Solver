package optimizer.algorithms.population;

import optimizer.model.Instance;
import optimizer.model.Solution;

public class GeneticAlgorithm extends AbstractGeneticAlgorithm {

    public GeneticAlgorithm() {
        super(400, 300, 3, 0.85, 0.05, 3);
    }

    @Override
    protected Solution[] breed(Solution p1, Solution p2, Instance instance) {
        if (random.nextDouble() >= crossoverRate) {
            return new Solution[]{ p1, p2 };
        }
        Solution child1 = greedyCompletionCrossover(p1, p2, instance);
        Solution child2 = greedyCompletionCrossover(p2, p1, instance);
        return new Solution[]{ child1, child2 };
    }

    @Override
    public String toString() { return "Genetic Algorithm"; }
}