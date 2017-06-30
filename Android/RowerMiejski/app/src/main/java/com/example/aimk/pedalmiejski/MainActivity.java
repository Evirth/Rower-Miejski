package com.example.aimk.pedalmiejski;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Camera camera;
    Camera.Parameters parameters;
    boolean isFlash = false;
    boolean isOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //ekran startowy
        android.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame,new RentBikeFragment()).commit();

        if(getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))
        {
            camera=Camera.open();
            parameters=camera.getParameters();
            isFlash=true;
        }
        View hView = navigationView.getHeaderView(0);

        ImageView headerImage = (ImageView) hView.findViewById(R.id.imageView);
        headerImage.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                diallContactPhone("48111222333");
            }});
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean turnFlashLight()
    {
        if(isFlash)
        {
            if(!isOn) {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(parameters);
                camera.startPreview();
                isOn=true;
            }
            else {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(parameters);
                camera.stopPreview();
                isOn=false;

            }
            return true;
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Error...");
            builder.setMessage("Flashlight is not Available on this device...");
            builder.setPositiveButton("OK",new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick (DialogInterface dialog,int width){
                    dialog.dismiss();
                    finish();
                }
            });
            AlertDialog alertDialog = builder.create();
            return false;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_torch) {
            turnFlashLight();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        android.app.FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.rent_bike_drawer) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new RentBikeFragment()).commit();
        } else if (id == R.id.return_bike_drawer) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new ReturnBikeFragment()).commit();

        } else if (id == R.id.show_map_drawer) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new ShowMapFragment()).commit();

        } else if (id == R.id.account_settings_drawer) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new AccountSettingsFragment()).commit();

        } else if (id == R.id.rental_history_drawer) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new RentalHistoryFragment()).commit();

        } else if (id == R.id.crash_report_drawer) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new CrashReportFragment()).commit();

        }else if (id == R.id.faq_drawer) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new FaqFragment()).commit();

        }else if (id == R.id.logout_drawer) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"Wylogowano pomyślnie :)", Toast.LENGTH_SHORT).show();
            finish();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void onStop()
    {
        super.onStop();
        if(camera!=null)
        {
            camera.release();
            camera=null;
        }
    }
    private void diallContactPhone(final String phoneNumber)
    {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
}
