package cz.csas.demo;

import cz.csas.demo.common.kdtree.KDTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class KDTreeTest {

    private List<Coordinates> items;

    @BeforeEach
    public void setup() {
        items = new ArrayList<>();
    }

    @Test
    public void findNearestNeighbor() {
        items.add(new Coordinates(100, 90));
        items.add(new Coordinates(140, 80));
        items.add(new Coordinates(10, 60));
        items.add(new Coordinates(120, 130));
        items.add(new Coordinates(250, 20));
        items.add(new Coordinates(200, 150));
        items.add(new Coordinates(210, 10));

        KDTree<Coordinates> kdTree = new KDTree<>(items);
        Coordinates foundItem = kdTree.findNearest(new Coordinates(120, 86));
        assertEquals(100, foundItem.getX());
        assertEquals(90, foundItem.getY());

        foundItem = kdTree.findNearest(new Coordinates(120, 84));
        assertEquals(140, foundItem.getX());
        assertEquals(80, foundItem.getY());

        foundItem = kdTree.findNearest(new Coordinates(220, 180));
        assertEquals(200, foundItem.getX());
        assertEquals(150, foundItem.getY());
    }

    @Test
    public void findNearestNeighbor_singleNode() {
        items.add(new Coordinates(100, 150));

        KDTree<Coordinates> kdTree = new KDTree<>(items);
        Coordinates foundItem = kdTree.findNearest(new Coordinates(120, 80));
        assertEquals(100, foundItem.getX());
        assertEquals(150, foundItem.getY());
    }

    @Test
    public void findNearestNeighbor_emptyTree() {
        KDTree<Coordinates> kdTree = new KDTree<>(items);
        Coordinates foundItem = kdTree.findNearest(new Coordinates(120, 80));
        assertNull(foundItem);
    }
}