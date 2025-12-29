package optimizer.model;

import java.util.Arrays;

public record Solution(boolean[] solution) {
    @Override
    public String toString() {
        return Arrays.toString(solution);
    }

    public Solution deepCopy() {
        return new Solution(Arrays.copyOf(this.solution, this.solution.length));
    }
}