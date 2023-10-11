package com.david.gridsim;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Poller {
    private static final String SERVER_URL = "http://stman1.cs.unh.edu:6191/games"; // Replace with your server URL
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    // Singleton instance
    private static final Poller INSTANCE = new Poller();
    private Context context;

    private Poller() {
        // Private constructor to prevent instantiation
    }

    public static Poller getInstance() {
        return INSTANCE;
    }

    public synchronized void startPolling(Context context) {
        if (!scheduler.isShutdown()) {
            stopPolling();
        }
        this.context = context;
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            try {
                fetchDataFromServer();
            } catch (Exception e) {
                Log.e("Poller", "Error fetching data", e);
            }
        }, 0, 500, TimeUnit.MILLISECONDS);
    }

    public synchronized void stopPolling() {
        scheduler.shutdownNow();
    }

    private void fetchDataFromServer() {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, SERVER_URL, null,
                response -> {
                    Log.d("Poller", "Response Received");
                    try {
                        JSONArray grid = response.getJSONArray("grid");
                        GridUpdateEvent event = new GridUpdateEvent(grid);
                        EventBus.getDefault().post(event);
                    } catch (JSONException e) {
                        Log.e("Poller", "Error parsing JSON", e);
                    }
                },
                error -> {
                    Log.e("Poller", "Error fetching data", error);
                }
        );

        queue.add(jsonObjectRequest);
    }
}
