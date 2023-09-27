package com.david.gridsim;

import com.david.gridsim.Model.GridCell;
import com.david.gridsim.Model.GridCellFactory;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class GridCellFactoryTest {

    private GridCellFactory factory = new GridCellFactory();

    @Test
    public void testEmptyCell1() {
        GridCell cell = factory.makeCell(0, 0, 0);
        assertEquals("Blank", cell.getCellType());
    }
    @Test
    public void testEmptyCell2() {
        GridCell cell = factory.makeCell(2001, 0, 0);
        assertEquals("Blank", cell.getCellType());
    }
    @Test
    public void testReserved() {
        GridCell cell = factory.makeCell(4001, 0, 0);
        assertEquals("Reserved", cell.getCellType());
    }

    @Test
    public void testTree() {
        GridCell cell = factory.makeCell(1000, 0, 0);
        assertEquals("Tree", cell.getCellType());
    }

    @Test
    public void testBushes() {
        GridCell cell = factory.makeCell(1001, 0, 0);
        assertEquals("Bushes", cell.getCellType());
    }

    @Test
    public void testClover() {
        GridCell cell = factory.makeCell(2002, 0, 0);
        assertEquals("Clover", cell.getCellType());
    }

    @Test
    public void testMushroom() {
        GridCell cell = factory.makeCell(2003, 0, 0);
        assertEquals("Mushroom", cell.getCellType());
    }

    @Test
    public void testSunflower() {
        GridCell cell = factory.makeCell(3000, 0, 0);
        assertEquals("Sunflower", cell.getCellType());
    }
    @Test
    public void testGardener() {
        GridCell cell = factory.makeCell(1000000, 0, 0);
        assertEquals("Gardener", cell.getCellType());
    }
    @Test
    public void testShovel() {
        GridCell cell = factory.makeCell(2000000, 0, 0);
        assertEquals("Shovel", cell.getCellType());
    }
    @Test
    public void testCart() {
        GridCell cell = factory.makeCell(10000000, 0, 0);
        assertEquals("Cart", cell.getCellType());
    }

    @Test
    public void testNonsenseValue() {
        GridCell cell = factory.makeCell(20000001, 0, 0);
        assertThrows(IllegalArgumentException.class, cell::getCellType);
    }
}
