package optimizer.algorithms.trajectory;

import optimizer.algorithms.Algorithm;
import optimizer.model.Instance;
import optimizer.model.Solution;
import java.util.Random;

public class RandomAlgorithm implements Algorithm {
    @Override
    public Solution run(Instance instance) {
        int m = instance.getTotalNumberOfItems();
        int n = instance.getNumberOfItemsToPick();
        boolean[] toSol = new boolean[m];
        Random random = new Random();

        int count = 0;
        while (count < n) {
            int index = random.nextInt(m);
            if (!toSol[index]) {
                toSol[index] = true;
                count++;
            }
        }
        return new Solution(toSol);
    }

    @Override
    public String toString() { return "Random Search"; }
}