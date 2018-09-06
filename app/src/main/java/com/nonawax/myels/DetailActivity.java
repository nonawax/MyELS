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

import com.nonawax.myels.code.Constants;
import com.nonawax.myels.util.XmlUtil;
import com.nonawax.myels.vo.ElsVO;
import com.nonawax.myels.vo.ElsVOList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends Activity {
    private String TAG = this.getClass().getName();

    ElsVO elsVO;
    EditText elsNm, startDt, endDt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        //id값셋팅
        elsNm = (EditText) findViewById(R.id.edtElsNm);
        startDt = (EditText) findViewById(R.id.edtStartDt);
        endDt = (EditText) findViewById(R.id.edtEndDt);

    }

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    public void mOnClick(View view) {
        String strElsNm = String.valueOf(elsNm.getText());
        String strStartDt = String.valueOf(startDt.getText());
        String strEndDt = String.valueOf(endDt.getText());

        switch (view.getId()) {
            case R.id.btnSave:
                elsVO = new ElsVO();
                elsVO.setElsNm(strElsNm);
                elsVO.setStartDt(strStartDt);
                elsVO.setEndDt(strEndDt);
                insertData(elsVO);
                break;
            case R.id.btnCancel:
                finish();
                break;
        }
    }


    public boolean insertData(ElsVO vo) {
        FileOutputStream fos = null;

        try {
            // 기존 데이터 읽어오기
			ElsVOList listVo = getData();

            // 기존 데이터가 없을 경우 객체 생성
            if(listVo == null)
                listVo = new ElsVOList();

            // 기존 데이터가 없을 경우 객체 생성
            if(listVo.getVoList() == null)
            	listVo.setVoList(new ArrayList<ElsVO>());

            // 기존 데이터에 추가
			elsVO.setId(XmlUtil.getInstance().getId(listVo));
            listVo.getVoList().add(elsVO);

            // Data저장
            fos = openFileOutput(Constants.FILE_NM, Context.MODE_WORLD_WRITEABLE);
            boolean rst = XmlUtil.getInstance().insertData(listVo, fos);
            fos.close();

            if(rst)
                Toast.makeText(getApplication(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplication(), "저장되지 않았습니다. 앱 종료 후 재시도 해주세요.", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(getApplication(), "insertData 오류", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)

                    fos.close();
            } catch (Exception e) {
                Log.e(TAG, "insertData ERR");
                Log.getStackTraceString(e);
            }
        }
        return true;
    }

    /**
     * 기존 데이터 읽어오기
     * @return 파일에서 읽은 데이터 List
     */
    private ElsVOList getData() {
        FileInputStream fis = null;
		ElsVOList listVo = null;
        try {
            //파일이 존재하지 않는 경우에는 생성
            File file = new File(getFilesDir().toString() + "/" + Constants.FILE_NM);
            if(!file.exists()){
                file.createNewFile();
            }
            fis = openFileInput(Constants.FILE_NM);
            listVo = XmlUtil.getInstance().getData(fis);
            fis.close();
        } catch (Exception e) {
            Toast.makeText(getApplication(), this.getClass().getMethods() + Constants.ERR, Toast.LENGTH_LONG).show();
			Log.e(TAG, this.getClass().getMethods() + Constants.ERR);
			e.printStackTrace();
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (Exception e) {
                Log.e(TAG, "getData ERR");
                Log.getStackTraceString(e);
            }
        }
        return listVo;
    }


}
