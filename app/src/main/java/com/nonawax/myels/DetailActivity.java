package com.nonawax.myels;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.nonawax.myels.code.AssetSourceCode;
import com.nonawax.myels.code.Constants;
import com.nonawax.myels.util.XmlUtil;
import com.nonawax.myels.vo.AssetVO;
import com.nonawax.myels.vo.ElsVO;
import com.nonawax.myels.vo.ElsVOList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DetailActivity extends AppCompatActivity {
    private String TAG = this.getClass().getName();

    ElsVO elsVO;
    EditText elsNm, edtStartDt, edtEndDt;
    ImageButton btnStartDt, btnEndDt;
    int mYear, mMonth, mDay;
    AppCompatButton btnSave, btnCancel;
	AppCompatSpinner spnAsset;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        //id값셋팅
        elsNm = (EditText) findViewById(R.id.edtElsNm);
        edtStartDt = (EditText) findViewById(R.id.edtStartDt);
        edtEndDt = (EditText) findViewById(R.id.edtEndDt);
        btnStartDt = (ImageButton)findViewById(R.id.btnStartDt);
        btnEndDt = (ImageButton)findViewById(R.id.btnEndDt);
        btnSave = (AppCompatButton)findViewById(R.id.btnSave);
        btnCancel = (AppCompatButton)findViewById(R.id.btnCancel);
        spnAsset = (AppCompatSpinner)findViewById(R.id.spnAsset);

        //현재 날짜와 시간을 가져오기위한 Calendar 인스턴스 선언
        Calendar cal = new GregorianCalendar();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        //청약일, 만기일 기본 셋팅
		edtStartDt.setText(mYear + "-" + (mMonth+1) + "-" + mDay);
		edtEndDt.setText((mYear+3) + "-" + (mMonth+1) + "-" + mDay);
		edtStartDt.setInputType(InputType.TYPE_NULL);
		edtEndDt.setInputType(InputType.TYPE_NULL);

		//버튼_이벤트리스너
		btnStartDt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mOnClick(v);
			}
		});
		btnEndDt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mOnClick(v);
			}
		});
		btnCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mOnClick(v);
			}
		});
		btnSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mOnClick(v);
			}
		});

		//스피너 셋팅
		AssetSourceCode asc = new AssetSourceCode();
		ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, asc.getList());
		spnAsset.setAdapter(arrayAdapter);
		spnAsset.setPrompt("기초자산 선택");
    }

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    public void mOnClick(View view) {
        String strElsNm = String.valueOf(elsNm.getText());
        String strStartDt = String.valueOf(edtStartDt.getText()).replaceAll("-", "");
        String strEndDt = String.valueOf(edtEndDt.getText()).replaceAll("-", "");

        switch (view.getId()) {
            case R.id.btnStartDt:  // 청약일 날짜 버튼
				new DatePickerDialog(DetailActivity.this, mStartDateSetListener
						, Integer.parseInt(edtStartDt.getText().toString().split("-")[0])
						, Integer.parseInt(edtStartDt.getText().toString().split("-")[1])-1
						, Integer.parseInt(edtStartDt.getText().toString().split("-")[2])).show();
                break;

			case R.id.btnEndDt:  // 마감일 날짜 버튼
				new DatePickerDialog(DetailActivity.this, mEndDateSetListener
						, Integer.parseInt(edtEndDt.getText().toString().split("-")[0])
						, Integer.parseInt(edtEndDt.getText().toString().split("-")[1])-1
						, Integer.parseInt(edtEndDt.getText().toString().split("-")[2])).show();
				break;

            case R.id.btnSave:  // 저장버튼 클릭시
                //TODO 속성확정시 추가
                elsVO = new ElsVO();
                elsVO.setElsNm(strElsNm);
                elsVO.setStartDt(strStartDt);
                elsVO.setEndDt(strEndDt);
                insertData(elsVO);
                finish();
                break;

            case R.id.btnCancel:    //취소버튼 클릭시
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
            Toast.makeText(getApplication(), this.getLocalClassName() + Constants.ERR, Toast.LENGTH_LONG).show();
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


    //시작일 날짜대화상자 리스너
	DatePickerDialog.OnDateSetListener mStartDateSetListener =
		new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
				mYear = year;
				mMonth = month;
				mDay = dayOfMonth;
				updateEditDate(R.id.btnStartDt);
			}
		};

	//종료일 날짜대화상자 리스너
	// TODO
	DatePickerDialog.OnDateSetListener mEndDateSetListener =
		new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
				mYear = year;
				mMonth = month;
				mDay = dayOfMonth;
				updateEditDate(R.id.btnEndDt);
			}
		};

	//개별_함수_START
	//날짜값셋팅_호출함수
	private void updateEditDate(int id){
		switch (id){
			case R.id.btnStartDt:
				edtStartDt.setText(mYear + "-" + (mMonth+1) + "-" + mDay);
				break;
			case R.id.btnEndDt:
				edtEndDt.setText(mYear + "-" + (mMonth+1) + "-" + mDay);
				break;
		}
	}
	//개별_함수_END
}
