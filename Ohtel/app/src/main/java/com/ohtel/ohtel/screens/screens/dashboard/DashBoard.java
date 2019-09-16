package com.ohtel.ohtel.screens.screens.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.ohtel.ohtel.BaseActivity;
import com.ohtel.ohtel.R;
import com.ohtel.ohtel.screens.screens.allcategory.AllCategory;
import com.ohtel.ohtel.screens.screens.importerexporter.ImporterExporter;
import com.ohtel.ohtel.screens.screens.specialservices.SpecialServices;
import com.ohtel.ohtel.screens.screens.vendors.Vendors;
import com.ohtel.ohtel.screens.screens.usedequipments.UsedEquipments;
import com.ohtel.ohtel.screens.screens.jobhunters.JobHunters;
import com.ohtel.ohtel.screens.screens.jobs.Jobs;
import com.ohtel.ohtel.screens.screens.newequipments.NewEquipments;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class DashBoard extends BaseActivity{

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.dashboard,rl_base_container);
        setTitle(R.string.dashboard);
        ButterKnife.bind(this);
        viewPager = findViewById(R.id.dashboard_viewpager_id);
        tabLayout = findViewById(R.id.dashboard_tab_layout_id);
        setupViewPager();
    }

    private void setupViewPager() {
        tabLayout.setupWithViewPager(viewPager);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new AllCategory(), getString(R.string.all));
        adapter.addFragment(new UsedEquipments(), getString(R.string.second_tab));
        adapter.addFragment(new NewEquipments(), getString(R.string.third));
        adapter.addFragment(new Vendors(), getString(R.string.fourth));
        adapter.addFragment(new ImporterExporter(), getString(R.string.fifth));
        adapter.addFragment(new Jobs(), getString(R.string.sixth));
        adapter.addFragment(new JobHunters(), getString(R.string.seventh));
        adapter.addFragment(new SpecialServices(), getString(R.string.special_services));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}

class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList=new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

}

