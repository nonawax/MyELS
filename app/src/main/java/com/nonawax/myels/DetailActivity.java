package com.nonawax.myels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.nonawax.myels.vo.ElsData;

import java.io.FileOutputStream;
import java.io.IOException;

public class DetailActivity extends Activity {
    private String TAG = this.getClass().getName();

    ElsData elsData;
    EditText elsNm, startDt, endDt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        //id값셋팅
        elsNm = (EditText)findViewById(R.id.edtElsNm);
        startDt = (EditText)findViewById(R.id.edtStartDt);
        endDt = (EditText)findViewById(R.id.edtEndDt);

    }

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    public void mOnClick(View view){
        String strElsNm = String.valueOf(elsNm.getText());
        String strStartDt = String.valueOf(startDt.getText());
        String strEndDt = String.valueOf(endDt.getText());

        switch (view.getId()){
            case R.id.btnSave:
                elsData = new ElsData();
                elsData.setIdx(0);
                elsData.setElsNm(strElsNm);
                elsData.setStartDt(strStartDt);
                elsData.setEndDt(strEndDt);
                insertData(elsData);
                break;
            case R.id.btnCancel:
                finish();
                break;
        }
    }


    public boolean insertData(ElsData data){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("ELS.data", Context.MODE_WORLD_WRITEABLE);
            //TODO

            XmlOptions xmlOptions = new XmlOptions();
            xmlOptions.setSavePrettyPrint();
            xmlOptions.setSavePrettyPrintIndent(4);
            xmlOptions.setCharacterEncoding("UTF-8");
            String xmlStr = mailDoc.xmlText(xmlOptions);


            fos.close();
            Toast.makeText(getApplication(), "DATA Saved ", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Toast.makeText(getApplication(), "파일 오류 발생", Toast.LENGTH_LONG).show();
        } finally {
            try {
                if(fos != null)
                    fos.close();
            } catch (Exception e) {
                Log.e(TAG, "insertData ERR");
                e.printStackTrace();
            }
        }
        return true;
    }
}
