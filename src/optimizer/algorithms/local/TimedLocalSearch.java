package optimizer.algorithms.local;

import optimizer.model.Evaluator;
import optimizer.model.Instance;
import optimizer.model.Solution;
import java.util.ArrayList;
import java.util.List;

public class TimedLocalSearch {

    public static Solution firstImprovement(Solution sol, Instance inst, long deadlineMillis) {
        boolean[] cur = sol.solution().clone();
        int n = cur.length;
        boolean improved = true;

        while (improved) {
            if (System.currentTimeMillis() > deadlineMillis) return new Solution(cur);
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
                    if (System.currentTimeMillis() > deadlineMillis) break outer;
                    cur[i] = false; cur[j] = true;
                    if (Evaluator.evaluate(new Solution(cur), inst) > base) {
                        improved = true;
                        break outer;
                    }
                    cur[i] = true; cur[j] = false;
                }
            }
        }
        return new Solution(cur);
    }

    public static Solution bestImprovement(Solution sol, Instance inst, long deadlineMillis) {
        boolean[] cur = sol.solution().clone();
        int n = cur.length;
        boolean improvement = true;

        while (improvement) {
            if (System.currentTimeMillis() > deadlineMillis) return new Solution(cur);
            improvement = false;
            double base = Evaluator.evaluate(new Solution(cur), inst);
            double bestGain = 0;
            int bi = -1, bj = -1;

            List<Integer> ones = new ArrayList<>();
            List<Integer> zeros = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (cur[i]) ones.add(i); else zeros.add(i);
            }

            for (int i : ones) {
                for (int j : zeros) {
                    if (System.currentTimeMillis() > deadlineMillis) break;
                    cur[i] = false; cur[j] = true;
                    double gain = Evaluator.evaluate(new Solution(cur), inst) - base;
                    if (gain > bestGain) {
                        bestGain = gain;
                        bi = i; bj = j;
                    }
                    cur[i] = true; cur[j] = false;
                }
            }
            if (System.currentTimeMillis() > deadlineMillis) break;

            if (bestGain > 0 && bi >= 0) {
                cur[bi] = false; cur[bj] = true;
                improvement = true;
            }
        }
        return new Solution(cur);
    }
}
