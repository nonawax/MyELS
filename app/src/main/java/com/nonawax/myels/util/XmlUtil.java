package com.nonawax.myels.util;

import android.util.Log;

import com.nonawax.myels.code.Constants;
import com.nonawax.myels.vo.ElsVO;
import com.nonawax.myels.vo.ElsVOList;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.NodeException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class XmlUtil {
    private static XmlUtil ourInstance;
    private Serializer serializer;
    private final String TAG = this.getClass().getName();

    private XmlUtil () {
        serializer = new Persister();
    }


    public static synchronized  XmlUtil getInstance() throws XmlPullParserException {
        if(ourInstance == null)
            ourInstance = new XmlUtil();
        return ourInstance;
    }


    /**
     * ELS 데이터 추가
     * @param voList ELS 데이터
     * @param fos Activity객체에서 넘겨주는 FileOutputStream
     * @return 성공여부
     * @throws Exception
     */
    public boolean insertData(ElsVOList voList, FileOutputStream fos) {
        try{
            serializer.write(voList, fos);
        }catch (Exception e){
            Log.e(TAG, this.getClass().getMethods() + Constants.ERR);
            e.printStackTrace();
            return false;
        }
        return true;
    }

	/**
	 * 기존 데이터 가져오기
	 * @param fis Activity객체에서 넘겨주는 FileInputStream
	 * @return 저장된 리스트 객체
	 * @throws Exception
	 */
    public ElsVOList getData(FileInputStream fis){
		ElsVOList voList = null;

        try {
            voList = (ElsVOList) serializer.read(ElsVOList.class, fis);
        }catch (NodeException ne){
            //Document has no root element
            return new ElsVOList();
        }catch (Exception e){
            Log.e(TAG, this.getClass().getMethods() + Constants.ERR);
            e.printStackTrace();
            return new ElsVOList();
        }
        return voList;
    }

    /**
     * 빈 id값 리턴
     */
    public int getId(ElsVOList voList) {
        int id = 0;
        boolean isExist = true;
        List<ElsVO> list = voList.getVoList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            for (ElsVO vo : list ) {
                if (vo.getId() == id) isExist = true;
            }
            if (isExist)
                id++;
            else
                break;
        }
        return id;
    }
}
