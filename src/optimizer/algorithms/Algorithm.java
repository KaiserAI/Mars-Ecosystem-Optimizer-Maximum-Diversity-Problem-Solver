package optimizer.algorithms;

import optimizer.model.Instance;
import optimizer.model.Solution;

public interface Algorithm {
    Solution run(Instance instance);

    // Método default para obtener el nombre de la clase de forma limpia
    default String toStringName() {
        return this.getClass().getSimpleName();
    }
}
