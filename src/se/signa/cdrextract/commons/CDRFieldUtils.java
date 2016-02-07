package se.signa.cdrextract.commons;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VGajja
 */
public class CDRFieldUtils {
    
    public static List<String> getListFromString(String str){
        List<String> strList = null;
        if(str != null && !str.isEmpty()){
            strList = new ArrayList<String>();
            for(String token : str.split(Constants.MULTI_VALUE_INPUT_DELIMETER)){
                strList.add(token);
            }
        }
        return strList;
    }
    
    public static String getListAsString(List<String> list){
    	if(list != null && !list.isEmpty()){
    		if(list.size() == 1){
    			return list.get(0);
    		}else{
    			StringBuilder sb = new StringBuilder();
    			int index = 0;
    			int size = list.size();
    			for(String bNum : list){
    				sb.append("'");
    				sb.append(bNum);
    				sb.append("'");
    				if(index< size-1){
    					sb.append(Constants.MULTI_VALUE_INPUT_DELIMETER);
    				}
    				index++;
    			}
    			return sb.toString();
    		}
    	}else{
    		return null;
    	}
    }
}
