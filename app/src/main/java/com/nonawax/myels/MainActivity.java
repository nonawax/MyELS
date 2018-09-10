package com.nonawax.myels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.nonawax.myels.code.Constants;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends Activity {

    private HorizontalScrollView scrollView;
    private AdView mAdView;
    private static final String TAG = "MainActivity";
    private static final String dataFileName = "ELS.data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //id값셋팅
        scrollView = (HorizontalScrollView)findViewById(R.id.scrollView);

        //초기화
        registerForContextMenu(scrollView);

        //광고등록
        registerAdView();

    }


    public void mOnClick(View view){
        Toast.makeText(getApplicationContext(), "Button Clicked!", Toast.LENGTH_LONG).show();
        Log.i(TAG, "Button Clicked!");
    }


    //개별_함수_START
    //광고등록
    private void registerAdView(){
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("03483DF4CA3C0ECEE36FF09F40B84A0D")
                .build();
        Log.d(TAG, "mAdView loadAd Before");
        mAdView.loadAd(adRequest);
        Log.d(TAG, "mAdView loadAd After");
    }

    //추가클릭시_호출함수
    private void insertElsData(){
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putExtra(Constants.MODE, Constants.MODE_I);
        startActivity(intent);
    }

    //수정클릭시_호출함수
    //TODO
    private void updateElsData(){
        FileInputStream fis = null;
        try {
            fis = openFileInput(dataFileName);
            byte[] txt = new byte[100];
            fis.read(txt);
            String str = new String(txt);
            fis.close();

            Toast.makeText(getApplication(), str, Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            Toast.makeText(getApplication(), "파일 오류 발생", Toast.LENGTH_LONG).show();
        } finally {
            try {
                if(fis != null)
                    fis.close();
            } catch (Exception e) {
                Log.e(TAG, "updateElsData ERR");
                e.printStackTrace();
            }
        }
    }
    //개별_함수_END

    //Override_함수_START
    // 옵션메뉴만들기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //메뉴만들기
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mInflater = getMenuInflater();
        if(v == scrollView) {
            mInflater.inflate(R.menu.main, menu);
        }
    }

    //메뉴선택시_호출함수
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch(item.getItemId()) {
            case R.id.menuAdd:
                insertElsData();
                return true;
            case R.id.menuUpt:
                updateElsData();
                return true;
            case R.id.menuDel:
                return true;
            case R.id.menuNoti:
                return true;
        }
        return false;
    }
    //Override_함수_END
}

