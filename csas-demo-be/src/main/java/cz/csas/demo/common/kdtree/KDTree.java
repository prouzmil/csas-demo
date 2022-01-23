package cz.csas.demo.common.kdtree;

import cz.csas.demo.common.Coordinates2D;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * KD strom s funkci hledani nejblizsiho souseda
 * @param <T>
 */
public class KDTree<T extends Coordinates2D> {
    private final KDTreeNode<T> root;

    /**
     * Konstruktor ze seznamu prvku
     * @param items seznam prvku
     */
    public KDTree(List<T> items) {
        // sestaveni stromu
        root = buildTree(new ArrayList<>(items), false);
    }

    /**
     * @param items seznam prvku
     * @param splitByY zda rozdelit prvky podle Y
     * @return vygenerovany KD strom
     */
    private KDTreeNode<T> buildTree(List<T> items, boolean splitByY) {
        if (items == null || items.isEmpty()) {
            return null;
        }

        items.sort(Comparator.comparingDouble(value -> splitByY ? value.getY() : value.getX()));

        int medianIndex = items.size() / 2;
        T median = items.get(items.size() / 2);
        List<T> itemsBefore = null;
        List<T> itemsAfter = null;
        if (items.size() > 1) {
            itemsBefore = items.subList(0, medianIndex);
        }
        if (medianIndex + 1 < items.size()) {
            itemsAfter = items.subList(medianIndex + 1, items.size());
        }

        return new KDTreeNode<T>(
                median,
                buildTree(itemsBefore, !splitByY),
                buildTree(itemsAfter, !splitByY)
        );
    }

    /**
     * @param searchedItem hledany prvek
     * @return nejblizsi prvek v KD stromu
     */
    public T findNearest(Coordinates2D searchedItem) {
        KDSearchResult<T> searchResult = findNearest(root, searchedItem, false, null);
        if (searchResult == null) {
            return null;
        } else {
            return searchResult.getNearestNode().getItem();
        }
    }

    /**
     * @param node uzel KD stromu
     * @param item hledany prvek
     * @return druha mocnina vzdalenosti
     */
    private double squareDistance(KDTreeNode<T> node, Coordinates2D item) {
        double dx = node.getItem().getX() - item.getX();
        double dy = node.getItem().getY() - item.getY();
        return dx * dx + dy * dy;
    }

    /**
     * @param startNode uzel, ze ktereho se ma zacit hledani
     * @param searchedItem hledany prvek
     * @param splitByY zda jsou polozky rozdelene podle Y
     * @param prevResult vysledek hledani z predchoziho kroku
     * @return vysledek hledani
     */
    private KDSearchResult<T> findNearest(KDTreeNode<T> startNode, Coordinates2D searchedItem, boolean splitByY, KDSearchResult<T> prevResult) {
        KDSearchResult<T> searchResult = prevResult;

        if (startNode == null) {
            return searchResult;
        }

        double distance = squareDistance(startNode, searchedItem);
        if (searchResult == null || distance < searchResult.getMinDistance()) {
            searchResult = new KDSearchResult<T>(startNode, distance);
        }

        double diff = splitByY
                ? searchedItem.getY() - startNode.getItem().getY()
                : searchedItem.getX() - startNode.getItem().getX();
        KDTreeNode<T> nodeToContinue = diff < 0 ? startNode.getLeftChild() : startNode.getRightChild();
        KDTreeNode<T> nodeToSkip = diff < 0 ? startNode.getRightChild() : startNode.getLeftChild();

        searchResult = findNearest(nodeToContinue, searchedItem, !splitByY, searchResult);

        double distanceToSplit = diff * diff;
        if (distanceToSplit < searchResult.getMinDistance()) {
            return findNearest(nodeToSkip, searchedItem, !splitByY, searchResult);
        }

        return searchResult;
    }

}
