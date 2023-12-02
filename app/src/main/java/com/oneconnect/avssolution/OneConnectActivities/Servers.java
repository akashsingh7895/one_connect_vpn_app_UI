package com.oneconnect.avssolution.OneConnectActivities;




import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import com.oneconnect.avssolution.OneConnectFragments.OneConnectFree;
import com.oneconnect.avssolution.R;
import com.google.android.material.tabs.TabLayout;
import com.oneconnect.avssolution.OneConnectAdapter.ServerAdapter;
import com.oneconnect.avssolution.OneConnectFragments.OneConnectPro;

public class Servers extends AppCompatActivity {

    private ServerAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oneconnect_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarold);
        toolbar.setTitle("Servers List");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new ServerAdapter(getSupportFragmentManager());

        adapter.addFragment(new OneConnectPro(), "Free Location");
        adapter.addFragment(new OneConnectFree(), "Premium Location");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}
