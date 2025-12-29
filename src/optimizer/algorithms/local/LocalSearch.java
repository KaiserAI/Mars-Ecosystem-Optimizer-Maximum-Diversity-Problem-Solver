package optimizer.algorithms.local;

import optimizer.model.Evaluator;
import optimizer.model.Instance;
import optimizer.model.Solution;
import java.util.ArrayList;
import java.util.List;

public class LocalSearch {

    public static Solution firstImprovement(Solution sol, Instance inst) {
        boolean[] cur = sol.solution().clone();
        int n = cur.length;
        boolean improved = true;

        while (improved) {
            improved = false;
            double base = Evaluator.evaluate(new Solution(cur), inst);

            List<Integer> ones = new ArrayList<>();
            List<Integer> zeros = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (cur[i]) ones.add(i); else zeros.add(i);
            }

            outer:
            for (int i : ones) {
                for (int j : zeros) {
                    cur[i] = false; cur[j] = true;
                    if (Evaluator.evaluate(new Solution(cur), inst) > base) {
                        improved = true;
                        break outer;
                    }
                    cur[i] = true; cur[j] = false; // Revert
                }
            }
        }
        return new Solution(cur);
    }
}