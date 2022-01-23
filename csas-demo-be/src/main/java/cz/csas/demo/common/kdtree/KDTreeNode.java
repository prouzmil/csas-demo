package cz.csas.demo.common.kdtree;

import cz.csas.demo.common.Coordinates2D;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Uzel KD stromu
 * @param <T> typ prvku
 */
@AllArgsConstructor
@Getter
class KDTreeNode<T extends Coordinates2D> {
    /** Prvek */
    private T item;
    /** Levy potomek */
    private KDTreeNode<T> leftChild;
    /** Pravy potomek */
    private KDTreeNode<T> rightChild;
}
