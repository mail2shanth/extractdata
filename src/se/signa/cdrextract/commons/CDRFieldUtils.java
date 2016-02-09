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
            for(String token : str.split("\\$\\$")){
                if(!token.trim().isEmpty())
                	strList.add(token.trim());
            }
        }
        return strList;
    }
    
    public static String getListAsString(List<String> list){
    	if(list != null && !list.isEmpty()){
    		StringBuilder sb = new StringBuilder();
    		int index = 0;
    		int size = list.size();
    		for(String bNum : list){
    			sb.append("'");
    			sb.append(bNum);
    			sb.append("'");
    			if(index< size-1){
    				sb.append(",");
    			}
    			index++;
    		}
    		return sb.toString();
    		//	}
    	}else{
    		return null;
    	}
    }
    
    public static String getLikeClauseCondition(List<String> list, String fieldName){
    	if(list != null && !list.isEmpty()){
    		StringBuilder sb = new StringBuilder();
    		int index = 0;
    		int size = list.size();
    		sb.append(" ( ");
    		for(String bNum : list){
    			sb.append(fieldName);
    			sb.append(" LIKE ");
    			sb.append("'");
    			sb.append(bNum);
    			sb.append("'");
    			if(index< size-1){
    				//sb.append(",");
    				sb.append(" OR ");
    			}
    			index++;
    		}
    		sb.append(" ) ");
    		return sb.toString();
    	}else{
    		return null;
    	}
    }
}
