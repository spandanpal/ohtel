package com.ohtel.ohtel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;

import com.ohtel.ohtel.database.SharedPreferenceDB;
import com.ohtel.ohtel.screens.screens.login.LoginActivity;

import java.io.File;

public abstract class BaseActivity extends AppCompatActivity {

    private AppCompatImageView imageBackArrow, toolBarMenu;
    private AppCompatTextView toolBarTitle;
    private LinearLayoutCompat toolBarLayout;
    public DrawerLayout drawerLayout;
    public RelativeLayout rl_base_container;


    ///navigation layout
    private AppCompatTextView dashboardView;
    private AppCompatTextView usedEquipmentView;
    private AppCompatTextView newEquipmentView;
    private AppCompatTextView importerExporterView;
    private AppCompatTextView vendersView;
    private AppCompatTextView jobsView;
    private AppCompatTextView selfRequirment;
    private AppCompatTextView logoutView;
    private AppCompatTextView navUserName;
    private AppCompatTextView emailIdView;
    private AppCompatImageView closeNavIcon;
    private AppCompatTextView tollFree;
    private static final int CALL_PERM = 7;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        findViewById();
        findViewNavMenuById();
    }

    public void findViewById(){
        imageBackArrow = findViewById(R.id.image_back_icon_id);
        toolBarMenu = findViewById(R.id.toolbar_menu_left);
        toolBarTitle = findViewById(R.id.tool_bar_title_id);
        toolBarLayout = findViewById(R.id.tool_bar_layout_id);
        drawerLayout = findViewById(R.id.drawerLayout);
        rl_base_container = findViewById(R.id.rl_base_container);
        navUserName = findViewById(R.id.nav_user_name);

        toolBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerToggle();
            }
        });

        imageBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void findViewNavMenuById(){
        dashboardView = findViewById(R.id.nav_dashboard_id);
        usedEquipmentView = findViewById(R.id.nav_used_equipment_id);
        newEquipmentView = findViewById(R.id.nav_new_equipment_id);
        importerExporterView = findViewById(R.id.nav_importer_exporter_id);
        vendersView = findViewById(R.id.nav_venders_id);
        jobsView = findViewById(R.id.nav_jobs_id);
        selfRequirment = findViewById(R.id.nav_self_job_requirement_id);
        logoutView = findViewById(R.id.nav_logout);
        emailIdView = findViewById(R.id.nav_email_id);
        tollFree = findViewById(R.id.nav_toll_free_id);
        closeNavIcon = findViewById(R.id.nav_close_icon_id);

        logoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trimCache(BaseActivity.this);
                SharedPreferenceDB.defaultInstance().saveLogin(BaseActivity.this, false);
                Intent intent = new Intent(BaseActivity.this, LoginActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });

    }

    private void trimCache(BaseActivity baseActivity) {
        try {
            File dir = this.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    private void drawerToggle() {
        if (drawerLayout.isDrawerOpen(Gravity.START)){
            drawerLayout.closeDrawer(Gravity.START);
        }else{
            drawerLayout.openDrawer(Gravity.START);
        }
    }

    public void setTitle(String title){
        toolBarTitle.setText(title);
    }
}
