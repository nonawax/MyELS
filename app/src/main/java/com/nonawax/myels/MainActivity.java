package com.nonawax.myels;

import android.app.Activity;
import android.content.Context;
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

import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends Activity {

    private HorizontalScrollView scrollView;
    private AdView mAdView;
    private static final String TAG = "MainActivity";

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
    //TODO
    private void insertElsData(){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("ELS.DATA", Context.MODE_WORLD_WRITEABLE);
            String str = "name:삼성1234||period:3||startDt:20180903||EndDt:20190103||KnocIn:50||Idx1:290";
            fos.write(str.getBytes());
            fos.close();
            Toast.makeText(getApplication(), "ELS DATA Created ", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Toast.makeText(getApplication(), "파일 오류 발생", Toast.LENGTH_LONG).show();
        } finally {
            try {
                if(fos != null)
                    fos.close();
            } catch (Exception e) {
                Log.e(TAG, "insertElsData ERR");
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
                Toast.makeText(getApplicationContext(), "메뉴추가 클릭!", Toast.LENGTH_SHORT).show();
                //TODO
                insertElsData();
                return true;
            case R.id.menuUpt:
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

