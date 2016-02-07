
package se.signa.cdrextract.ui;

import java.util.Calendar;

/**
 *
 * @author VGajja
 */
public class FormValidator {
    
    public static boolean isValidFromAndToDates(Calendar fromDate, Calendar toDate){
       if(fromDate == null)
           return false;
       if(toDate == null)
           return false;
       if(fromDate.getTimeInMillis() > toDate.getTimeInMillis())
           return false;
        return true;
    }
    
}
