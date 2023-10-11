package com.david.gridsim;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import android.widget.GridView;
import android.widget.TextView;

public class SimGridViewTest {

    @Mock
    private EventBus mockEventBus;

    @Mock
    private GridView mockGridView;

    @Mock
    private TextView mockTextView;

    private SimGridView simGridView;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        simGridView = new SimGridView(mockEventBus);
    }

    @Test
    public void testAttachRegistersEventBus() {
        simGridView.attach(mockTextView, mockGridView);
        verify(mockEventBus).register(simGridView);
    }

    @Test
    public void testDetachUnregistersEventBus() {
        simGridView.detach();
        verify(mockEventBus).unregister(simGridView);
    }

    @Test
    public void testAttachSetsAdapter() {
        simGridView.attach(mockTextView, mockGridView);
        verify(mockGridView).setAdapter(any(GridAdapter.class));
    }

    @Test
    public void testSetUsingJSONInvalidatesViews() throws JSONException {
        // First line of every response
        String basicGrid = "[[0,1000,1000,1000,0,1000,0,1000,1000,1000,0,0,0,0,0,0]]";
        JSONArray grid = new JSONArray(basicGrid);
        simGridView.attach(mockTextView, mockGridView);
        simGridView.setUsingJSON(grid);
        verify(mockGridView).invalidateViews();
    }
}
