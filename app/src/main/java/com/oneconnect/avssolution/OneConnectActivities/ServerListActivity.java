package com.oneconnect.avssolution.OneConnectActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.oneconnect.avssolution.OneConnectAdapter.ServerFreeAdapter;
import com.oneconnect.avssolution.OneConnectAdapter.ServerProAdapter;
import com.oneconnect.avssolution.OneConnectFragments.OneConnectPro;
import com.oneconnect.avssolution.OneConnectModel.Countries;
import com.oneconnect.avssolution.R;
import com.oneconnect.avssolution.SubscriptionId;
import com.oneconnect.avssolution.Utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ServerListActivity extends AppCompatActivity implements ServerFreeAdapter.RegionListAdapterInterface, ServerProAdapter.RegionListAdapterInterface {


    // Ffree_server
    private RecyclerView recyclerView;
    private ServerFreeAdapter adapter;
    private ArrayList<Countries> countryArrayList;
    private OneConnectPro.RegionChooserInterface regionChooserInterface;
    // Ffree_server


    private RecyclerView recyclerView2;
    private ServerProAdapter adapter2;
    private OneConnectPro.RegionChooserInterface regionChooserInterface2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_list);

        // Ffree_server
        recyclerView = findViewById(R.id.region_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        countryArrayList = new ArrayList<>();
        adapter = new ServerFreeAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);
        loadServers();
        // Ffree_server

        recyclerView2 = findViewById(R.id.region_recycler_view2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter2 = new ServerProAdapter(getApplicationContext());
        recyclerView2.setAdapter(adapter2);
        loadServers2();

        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadServers() {

        ArrayList<Countries> servers = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(Constants.FREE_SERVERS);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                servers.add(new Countries(object.getString("serverName"),
                        object.getString("flag_url"),
                        object.getString("ovpnConfiguration"),
                        object.getString("vpnUserName"),
                        object.getString("vpnPassword")


                ));

                if ((i % 2 == 0) && (i > 0)) {
                    if (!SubscriptionId.OneConnectSubTwo && !SubscriptionId.OneConnectSubThree) {
                        servers.add(null);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
            Log.d("mylog", String.valueOf(e));
        }
        adapter.setData(servers);
    }

    private void loadServers2() {

        ArrayList<Countries> servers = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(Constants.PREMIUM_SERVERS);

            for (int i=0; i < jsonArray.length();i++){
                JSONObject object = (JSONObject) jsonArray.get(i);
                servers.add(new Countries(object.getString("serverName"),
                        object.getString("flag_url"),
                        object.getString("ovpnConfiguration"),
                        object.getString("vpnUserName"),
                        object.getString("vpnPassword")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter2.setData(servers);
    }

    @Override
    public void onCountrySelected(Countries item) {
        regionChooserInterface.onRegionSelected(item);
        regionChooserInterface2.onRegionSelected(item);
    }


}