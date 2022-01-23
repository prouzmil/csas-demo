package cz.csas.demo.common.kdtree;

import cz.csas.demo.common.Coordinates2D;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Vysledek hledani nejblizsiho prvku v KD stromu
 * @param <T> typ prvku
 */
@AllArgsConstructor
@Getter
class KDSearchResult<T extends Coordinates2D> {
    /** Nejblizsi nalezenu uzel */
    private KDTreeNode<T> nearestNode;
    /** Vzdalenost nalezeneho uzlu od souradnic hledane polozky */
    private double minDistance;
}
