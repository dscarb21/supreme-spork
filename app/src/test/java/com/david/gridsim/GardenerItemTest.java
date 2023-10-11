package com.david.gridsim;

import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.json.JSONArray;
import org.json.JSONObject;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.david.gridsim.Model.GardenerItem;

public class GardenerItemTest {

    private GardenerItem gardenerItem;
    private EventBus mockEventBus;

    @Before
    public void setUp() {
        mockEventBus = Mockito.mock(EventBus.class);
        gardenerItem = new GardenerItem(1001000, 0, 0); // Initial position (0,0)
    }

    @Test
    public void testInitialPosition() {
        String expectedHistory = "(0, 0)";
        assertTrue(gardenerItem.getCellInfo().contains(expectedHistory));
    }

    @Test
    public void testMoveToNewPosition() {
        gardenerItem.updateLocation(1, 1);
        String expectedHistory = "(1, 1)";
        assertTrue(gardenerItem.getCellInfo().contains(expectedHistory));
    }

    @Test
    public void testMoveToSamePosition() {
        gardenerItem.updateLocation(1, 1);
        int occurrences = countOccurrences(gardenerItem.getCellInfo(), "(1, 1)");
        assertEquals(1, occurrences); // Should only be logged once
    }

    @Test
    public void testMultipleMovements() {
        gardenerItem.updateLocation(5, 6);
        gardenerItem.updateLocation(7, 8);
        gardenerItem.updateLocation(9, 10);

        assertTrue(gardenerItem.getCellInfo().contains("(5, 6)"));
        assertTrue(gardenerItem.getCellInfo().contains("(7, 8)"));
        assertTrue(gardenerItem.getCellInfo().contains("(9, 10)"));
    }

    // CHAT GPT: "Give me a java method to count occurrences of a string inside of another string"
    private int countOccurrences(String haystack, String needle) {
        return haystack.split(needle, -1).length - 1;
    }
}