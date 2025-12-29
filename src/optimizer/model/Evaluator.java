package optimizer.model;

public class Evaluator {
    public static double evaluate(Solution solution, Instance instance) {
        double totalDistance = 0;
        boolean[] sol = solution.solution();
        int n = instance.getTotalNumberOfItems();

        for (int i = 0; i < n - 1; i++) {
            if (!sol[i]) continue; // Optimización simple
            for (int j = i + 1; j < n; j++) {
                if (sol[j]) {
                    totalDistance += instance.getDistance(i, j);
                }
            }
        }
        return totalDistance;
    }
}
