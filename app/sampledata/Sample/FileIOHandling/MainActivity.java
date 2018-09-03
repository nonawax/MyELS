package com.ninano.fileiohandling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btnWrite, btnRead, btnRawRead;
	EditText edtRaw;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		btnWrite = (Button)findViewById(R.id.btnWrite);
		btnRead = (Button)findViewById(R.id.btnRead);
		btnRawRead = (Button)findViewById(R.id.btnRawRead);
		edtRaw = (EditText)findViewById(R.id.edtRaw);
		
		
	}
	
	
	public void mOnClick(View v) {
		switch(v.getId()){
		case R.id.btnWrite:
			FileOutputStream fos = null;
			try {
				fos = openFileOutput("pome.txt", Context.MODE_WORLD_WRITEABLE);
				String str = "밤부터 소쩍새는 그렇게 울었나 보다 보다 밤부터 소쩍새는 그렇게 울었나 보다 보다 \n 밤부터 소쩍새는 그렇게 울었나 보다 보다";
				fos.write(str.getBytes());
				fos.close();
				Toast.makeText(getApplication(), "poem.txt Created ", 1).show();
						
			} catch (IOException e) {
				Toast.makeText(getApplication(), "파일 오류 발생", 1).show();
			} finally {
				try {
					if(fos != null)
						fos.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case R.id.btnRead:
			FileInputStream fis = null;
			try {
				fis = openFileInput("pome.txt");
				byte[] txt = new byte[100];
				fis.read(txt);
				String str = new String(txt);
				fis.close();
				
				Toast.makeText(getApplication(), str, 1).show();
			} catch (IOException e) {
				Toast.makeText(getApplication(), "poem.txt 파일이 존재하지 않습니다..", 1).show();
			} finally {
				try {
					if(fis != null)
						fis.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case R.id.btnRawRead:
			InputStream is = null;
			try {
				is = getResources().openRawResource(R.raw.config);
				byte[] txt = new byte[is.available()];
				is.read(txt);
				is.close();
				
				edtRaw.setText(new String(txt));
			} catch (IOException e) {
				// TODO: handle exception
			} finally {
				try {
					if(is != null)
						is.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
