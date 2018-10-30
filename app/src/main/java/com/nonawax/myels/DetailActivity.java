package com.nonawax.myels;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ListViewCompat;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.nonawax.myels.code.Constants;
import com.nonawax.myels.util.XmlUtil;
import com.nonawax.myels.vo.AssetVO;
import com.nonawax.myels.vo.ElsVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private String TAG = this.getClass().getName();

    ElsVO elsVO;
    EditText elsNm, edtStartDt, edtEndDt;
    ImageButton btnStartDt, btnEndDt;
    int mYear, mMonth, mDay;
    AppCompatButton btnSave, btnCancel, btnKnockInPoint, btnKnockInYn, btnConditionPoint, btnInput, btnDelete;
	AppCompatSpinner spnAsset1Nm,spnAsset2Nm,spnAsset3Nm, spnKnockIn, spnCondition;
	List<ElsVO> listVo;	//파일에서 불러온 데이터 담을 객체
	String detailMode;		//상세화면 모드
	ArrayAdapter<AssetVO> adapter; //리스트뷰 어뎁터
	AppCompatTextView tvKnockIn,tvKnockInRto;
	int elsId;				//ELS ID
	boolean bKnockIn = false;
	AppCompatEditText textCondition;


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
		btnKnockInYn = (AppCompatButton)findViewById(R.id.btnKnockInYn);
		btnKnockInPoint = (AppCompatButton)findViewById(R.id.btnKnockInPoint);
		btnInput = (AppCompatButton)findViewById(R.id.btnInput);
		btnDelete = (AppCompatButton)findViewById(R.id.btnDelete);
		btnConditionPoint = (AppCompatButton)findViewById(R.id.btnConditionPoint);
		spnAsset1Nm = (AppCompatSpinner)findViewById(R.id.spnAsset1Nm);
		spnAsset2Nm = (AppCompatSpinner)findViewById(R.id.spnAsset2Nm);
		spnAsset3Nm = (AppCompatSpinner)findViewById(R.id.spnAsset3Nm);
        spnKnockIn = (AppCompatSpinner)findViewById(R.id.spnKnockIn);
        spnCondition = (AppCompatSpinner)findViewById(R.id.spnCondition);
		tvKnockIn = (AppCompatTextView) findViewById(R.id.tvKnockIn);
        tvKnockInRto = (AppCompatTextView) findViewById(R.id.tvKnockInRto);
        textCondition = (AppCompatEditText) findViewById(R.id.textCondition);

        //인텐트에서 가져오기
		Intent intent = getIntent();
		detailMode = intent.getStringExtra(Constants.MODE);			//상세화면 모드값
		elsId = intent.getIntExtra("elsId",-1);	//메인에서 선택된 elsId값

		// 기존 데이터 읽어오기
		getData();

		// 기존 데이터가 없을 경우 객체 생성
		if(listVo == null){
			listVo = new ArrayList<ElsVO>();
			ElsVO emptyVo = new ElsVO();
			listVo.add(emptyVo);
		}

        //현재 날짜와 시간을 가져오기위한 Calendar 인스턴스 선언
        Calendar cal = new GregorianCalendar();
        //금요일때까지 날짜 셋팅
        while (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
        	cal.add(Calendar.DATE,1);
		}
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        //청약일 기본셋팅
		edtStartDt.setText(mYear + "-" + (mMonth+1) + "-" + mDay);

		//만기일 기본셋팅
		cal.add(Calendar.YEAR, 3);
		cal.add(Calendar.DATE,-1);
		edtEndDt.setText((mYear+3) + "-" + (mMonth+1) + "-" + mDay);

		//날짜 수동수정 금지
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
		btnKnockInPoint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mOnClick(v);
			}
		});
		btnKnockInYn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mOnClick(v);
			}
		});
		btnConditionPoint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mOnClick(v);
			}
		});
		btnInput.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mOnClick(v);
			}
		});
		btnDelete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mOnClick(v);
			}
		});

		//상환조건 스피너 셋팅
		spnAsset1Nm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override //값 선택시
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		//낙인 스피너 셋팅
		spnKnockIn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				tvKnockInRto.setText((String) spnKnockIn.getSelectedItem());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		try{
			if(detailMode.equals(Constants.MODE_U)){
				//수정모드일 경우 : 기존 자산리스트 찾아서 셋팅
				int idx = XmlUtil.getInstance().getXmlIdx(elsId, listVo);
				adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listVo.get(idx).getAssetVoList());
			}
		}catch (Exception e){
			Toast.makeText(getApplication(), "자산리스트 구성 중 오류", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}


    }

    public void mOnClick(View view) {
        String strElsNm = String.valueOf(elsNm.getText());
        String strStartDt = String.valueOf(edtStartDt.getText()).replaceAll("-", "");
        String strEndDt = String.valueOf(edtEndDt.getText()).replaceAll("-", "");
		String strTextCondition = String.valueOf(textCondition.getText()).trim();

        switch (view.getId()) {
			case R.id.tvStartDt:  // 청약일 날짜 버튼
				new DatePickerDialog(DetailActivity.this, mStartDateSetListener
						, Integer.parseInt(edtStartDt.getText().toString().split("-")[0])
						, Integer.parseInt(edtStartDt.getText().toString().split("-")[1])-1
						, Integer.parseInt(edtStartDt.getText().toString().split("-")[2])).show();
				break;

            case R.id.btnStartDt:  // 청약일 날짜 버튼
				new DatePickerDialog(DetailActivity.this, mStartDateSetListener
						, Integer.parseInt(edtStartDt.getText().toString().split("-")[0])
						, Integer.parseInt(edtStartDt.getText().toString().split("-")[1])-1
						, Integer.parseInt(edtStartDt.getText().toString().split("-")[2])).show();
                break;

			case R.id.tvEndDt:  // 마감일 날짜 버튼
				new DatePickerDialog(DetailActivity.this, mEndDateSetListener
						, Integer.parseInt(edtEndDt.getText().toString().split("-")[0])
						, Integer.parseInt(edtEndDt.getText().toString().split("-")[1])-1
						, Integer.parseInt(edtEndDt.getText().toString().split("-")[2])).show();
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
				AssetVO a1 = new AssetVO("KOSPI200", "2300.12");
				AssetVO a2 = new AssetVO("HSCEI", "15000.34");
				AssetVO a3 = new AssetVO("SAMSUNG", "40000.45");
				ArrayList<AssetVO> assetList = new ArrayList<AssetVO>();
				assetList.add(a1);
				assetList.add(a2);
				assetList.add(a3);
				elsVO.setAssetVoList(assetList);
                insertData(elsVO);
                finish();
                break;

            case R.id.btnCancel:    //취소버튼 클릭시
                finish();
                break;
			case R.id.btnKnockInPoint : 	//낙인포인트버튼 클릭시
				//.5가 안 붙어있는 경우에만 뒤에 소수점 추가
				if(tvKnockInRto.getText().toString().indexOf(".5") == -1){
					String tempKnockIn = tvKnockInRto.getText().toString();
					tempKnockIn += ".5";
					tvKnockInRto.setText(tempKnockIn) ;
				}
				break;

			case R.id.btnKnockInYn : 	//낙인여부버튼 클릭시

				if(!bKnockIn){
					tvKnockIn.setText("노낙인");
					btnKnockInPoint.setClickable(false);
					btnKnockInPoint.setVisibility(View.INVISIBLE);
					spnKnockIn.setVisibility(View.INVISIBLE);
					tvKnockInRto.setText("");
				}else{
					tvKnockIn.setText("낙인 : ");
					btnKnockInPoint.setClickable(true);
					btnKnockInPoint.setVisibility(View.VISIBLE);
					spnKnockIn.setVisibility(View.VISIBLE);
					tvKnockInRto.setText((String) spnKnockIn.getSelectedItem());
				}
				//값 변경
				bKnockIn = !bKnockIn;
				break;

			case R.id.btnInput : //입력버튼 클릭시:상환조건 입력버튼
				//입력된 값이 없는 경우
				if(strTextCondition.trim().length() > 1){
					textCondition.setText((String) spnCondition.getSelectedItem());
				}else{
					//입력값이 있으면 , 추가하고
					textCondition.setText(strTextCondition + "," + (String) spnCondition.getSelectedItem());
				}
				initSpnCondition();
				break;

			case R.id.btnDelete : //삭제버튼 클릭시:상환조건 삭제버튼
				//TODO : textCondition에서 마지막 요소를 삭제
				//입력된 값이 없는 경우
				if(strTextCondition.trim().length() > 1){
					Toast.makeText(getApplication(), "삭제할 상환조건이 없습니다.", Toast.LENGTH_SHORT).show();
				}else{
					String tempStr = strTextCondition.substring(0,strTextCondition.lastIndexOf(","));
					textCondition.setText(tempStr);
				}
				initSpnCondition();
				break;

			case R.id.btnConditionPoint: //상황조건의 0.5버튼 클릭시
				//입력된 값이 없는 경우
				if(strTextCondition.trim().length() > 1){
					textCondition.setText((String) spnCondition.getSelectedItem());
				}else{
					//입력값이 있으면 , 추가하고
					textCondition.setText(strTextCondition + "," + ((String) spnCondition.getSelectedItem()).trim() + ".5");
				}
				initSpnCondition();
				break;
        }
    }


    public boolean insertData(ElsVO vo) {
        FileOutputStream fos = null;

        try {

            // 기존 데이터에 추가
			elsVO.setId(XmlUtil.getInstance().getId(listVo));
            listVo.add(elsVO);

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
    private void getData() {
        FileInputStream fis = null;
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

	//조기상환 스피너 초기화 함수
	private void initSpnCondition(){

	}
	//개별_함수_END
}
