package tw.dkyang.taipei_parking_lot;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private CustomFragmentAdapter adapter;
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initSet();

    }

    private void initView() {
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        tab = (TabLayout)findViewById(R.id.view_tab);
    }

    private void initSet() {

        tab.addTab(tab.newTab().setText("附近停車場列表"));
        tab.addTab(tab.newTab().setText("地圖顯示"));



        adapter = new CustomFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        adapter.setDataOne("第一頁");
        adapter.setDataTwo("這不是第一頁");

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}
