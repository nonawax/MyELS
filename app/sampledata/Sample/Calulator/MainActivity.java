package com.ninano.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView tvDisp;
	int op1, op2;
	String oper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		tvDisp = (TextView)findViewById(R.id.tvDisp);
		
	}
	
	public void mOnClick(View v) {
		String strNum = tvDisp.getText().toString();
		
		
		switch(v.getId()) {
		case R.id.btnDiv:
			oper = "/";
			// 예외처리
			if(strNum.length() == 0) {
				break;
			}
			if(op1 == 0) {
				op1 = Integer.parseInt(tvDisp.getText().toString());
				tvDisp.append("/");
			}
			if(op2 != 0) {
				op2 = 0;
				tvDisp.append("/");
			}
			break;
			
		case R.id.btnMul:
			oper = "*";
			if(strNum.length() == 0) {
				break;
			}
			if(op1 == 0) {
				op1 = Integer.parseInt(tvDisp.getText().toString());
				tvDisp.append("*");
			}
			if(op2 != 0) {
				op2 = 0;
				tvDisp.append("*");
			}
			break;
			
		case R.id.btnSub:
			oper = "-";
			if(strNum.length() == 0) {
				break;
			}
			if(op1 == 0) {
				op1 = Integer.parseInt(tvDisp.getText().toString());
				tvDisp.append("-");
			}
			if(op2 != 0) {
				op2 = 0;
				tvDisp.append("-");
			}
			break;
			
		case R.id.btnAdd:
			oper = "+";
			if(strNum.length() == 0) {
				break;
			}
			if(op1 == 0) {
				op1 = Integer.parseInt(tvDisp.getText().toString());
				tvDisp.append("+");
			}
			if(op2 != 0) {
				op2 = 0;
				tvDisp.append("+");
			}
			break;
			
		case R.id.btnDel:
			op1 = 0;
			op2 = 0;
			tvDisp.setText("");
			break;
			
		case R.id.btnEqual:
			if(strNum.length() == 0) {
				break;
			}
			if(!oper.equals(null)) {
				if(op2 != 0) {
					if(oper.equals("+")) {
						tvDisp.setText(Integer.toString(op1));
					}else if(oper.equals("-")) {
						tvDisp.setText(Integer.toString(op1));
					}else if(oper.equals("*")) {
						tvDisp.setText(Integer.toString(op1));
					}else if(oper.equals("/")) {
						tvDisp.setText(Integer.toString(op1));
					}
				}else {
					// 메소드 정의
					operation();
				}
			}
			break;
			
		case R.id.btnDot:
			//소숫점은 생략
			break;
			
		case R.id.btnNum0:
			if(op2 != 0) {
				op2 = 0;
				tvDisp.setText("");
			}
			tvDisp.append("0");
			break;
			
		case R.id.btnNum1:
			if(op2 != 0) {
				op2 = 0;
				tvDisp.setText("");
			}
			tvDisp.append("1");
			break;
		case R.id.btnNum2:
			if(op2 != 0) {
				op2 = 0;
				tvDisp.setText("");
			}
			tvDisp.append("2");
			break;
		case R.id.btnNum3:
			if(op2 != 0) {
				op2 = 0;
				tvDisp.setText("");
			}
			tvDisp.append("3");
			break;
		case R.id.btnNum4:
			if(op2 != 0) {
				op2 = 0;
				tvDisp.setText("");
			}
			tvDisp.append("4");
			break;
		case R.id.btnNum5:
			if(op2 != 0) {
				op2 = 0;
				tvDisp.setText("");
			}
			tvDisp.append("5");
			break;
		case R.id.btnNum6:
			if(op2 != 0) {
				op2 = 0;
				tvDisp.setText("");
			}
			tvDisp.append("6");
			break;
		case R.id.btnNum7:
			if(op2 != 0) {
				op2 = 0;
				tvDisp.setText("");
			}
			tvDisp.append("7");
			break;
		case R.id.btnNum8:
			if(op2 != 0) {
				op2 = 0;
				tvDisp.setText("");
			}
			tvDisp.append("8");
			break;
		case R.id.btnNum9:
			if(op2 != 0) {
				op2 = 0;
				tvDisp.setText("");
			}
			tvDisp.append("9");
			break;
		}
	}

	private void operation() {
		String str = "", str1 = "", str2 = "";
		int length = 0, op = 0;
		
		// 스트링 가져오기
		str = tvDisp.getText().toString();
		// 총 길이 구함
		length = str.length();
		
		if(oper.equals("+")) {		
			// 오퍼레이션 기호 위치를 구함
			op = str.indexOf("+");
			
			str1 = str.substring(0, op);
			str2 = str.substring(op+1 , length);
			
			op1 = Integer.parseInt(str1);
			op2 = Integer.parseInt(str2);
			
			op1 = op1 + op2;
			tvDisp.setText(String.valueOf(op1));
//			tvDisp.setText(Integer.toString(op1));
		}
		
		else if(oper.equals("-")) {		
			// 오퍼레이션 기호 위치를 구함
			op = str.indexOf("-");
			
			str1 = str.substring(0, op);
			str2 = str.substring(op+1 , length);
			
			op1 = Integer.parseInt(str1);
			op2 = Integer.parseInt(str2);
			
			op1 = op1 - op2;
			tvDisp.setText(String.valueOf(op1));
//			tvDisp.setText(Integer.toString(op1));
		}
		
		else if(oper.equals("*")) {		
			// 오퍼레이션 기호 위치를 구함
			op = str.indexOf("*");
			
			str1 = str.substring(0, op);
			str2 = str.substring(op+1 , length);
			
			op1 = Integer.parseInt(str1);
			op2 = Integer.parseInt(str2);
			
			op1 = op1 * op2;
			tvDisp.setText(String.valueOf(op1));
//			tvDisp.setText(Integer.toString(op1));
		}
		
		else if(oper.equals("/")) {		
			// 오퍼레이션 기호 위치를 구함
			op = str.indexOf("/");
			
			str1 = str.substring(0, op);
			str2 = str.substring(op+1 , length);
			
			op1 = Integer.parseInt(str1);
			op2 = Integer.parseInt(str2);
			
			op1 = op1 / op2;
			tvDisp.setText(String.valueOf(op1));
//			tvDisp.setText(Integer.toString(op1));
		}
		
		oper = null;
	}
}
