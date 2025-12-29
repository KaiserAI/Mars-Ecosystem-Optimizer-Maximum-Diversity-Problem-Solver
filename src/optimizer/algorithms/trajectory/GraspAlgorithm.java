package optimizer.algorithms.trajectory;

import optimizer.algorithms.Algorithm;
import optimizer.model.Instance;
import optimizer.model.Solution;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GraspAlgorithm implements Algorithm {
    @Override
    public Solution run(Instance instance) {
        int nTotal = instance.getTotalNumberOfItems();
        int nPick = instance.getNumberOfItemsToPick();
        double[][] dists = instance.getDistances();
        boolean[] toSol = new boolean[nTotal];

        int first = (int) (Math.random() * nTotal);
        toSol[first] = true;

        List<Integer> candidates = IntStream.range(0, nTotal).boxed().collect(Collectors.toList());
        candidates.remove((Integer) first);
        int lastSpecies = first;

        for (int i = 0; i < nPick - 1; i++) {
            int next = chooseFromRCL(dists[lastSpecies], candidates);
            toSol[next] = true;
            candidates.remove((Integer) next);
            lastSpecies = next;
        }
        return new Solution(toSol);
    }

    private int chooseFromRCL(double[] distRow, List<Integer> candidates) {
        int nToRCL = Math.max(1, (int) (candidates.size() * 0.2));
        candidates.sort((a, b) -> Double.compare(distRow[b], distRow[a])); // Descendente

        // Elegir aleatorio entre los mejores nToRCL
        return candidates.get((int) (Math.random() * nToRCL));
    }

    @Override
    public String toString() { return "GRASP"; }
}