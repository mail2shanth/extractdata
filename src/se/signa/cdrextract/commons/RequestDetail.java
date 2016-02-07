
package se.signa.cdrextract.commons;

import java.util.Date;
import java.util.List;

/**
 *
 * @author VGajja
 */
public class RequestDetail {
    
    private String accName = null;
    private String bndName = null;
    private List<String> aNumbers = null;
    private List<String> bNumbers = null;
    private Date fromDateTime = null;
    private Date toDateTime = null;
    private String causeCode = null;
    private String billProfile = null;

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getBndName() {
        return bndName;
    }

    public void setBndName(String bndName) {
        this.bndName = bndName;
    }

    public List<String> getbNumbers() {
        return bNumbers;
    }

    public void setbNumbers(List<String> bNumbers) {
        this.bNumbers = bNumbers;
    }

    public List<String> getaNumbers() {
        return aNumbers;
    }

    public void setaNumbers(List<String> aNumbers) {
        this.aNumbers = aNumbers;
    }

    public Date getFromDateTime() {
        return fromDateTime;
    }

    public void setFromDateTime(Date fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    public Date getToDateTime() {
        return toDateTime;
    }

    public void setToDateTime(Date toDateTime) {
        this.toDateTime = toDateTime;
    }

    public String getCauseCode() {
        return causeCode;
    }

    public void setCauseCode(String causeCode) {
        this.causeCode = causeCode;
    }

    public String getBillProfile() {
        return billProfile;
    }

    public void setBillProfile(String billProfile) {
        this.billProfile = billProfile;
    }
    public boolean isEmptyRequest(){
    	if( this.accName != null && !this.accName.isEmpty() ||
    			this.bndName != null && !this.bndName.isEmpty() ||
    			this.aNumbers != null && !this.aNumbers.isEmpty() ||
    			this.bNumbers != null && !this.bNumbers.isEmpty() ||
    			this.fromDateTime != null ||
    			this.toDateTime != null ||
    			this.billProfile != null && !this.billProfile.isEmpty() ||
    			this.causeCode != null && !this.causeCode.isEmpty()){
    		return false;
    	}
    	return true;
    }

    @Override
    public String toString() {
        return "RequestDetail{" + "accName=" + accName + ", bndName=" + bndName + ", aNumbers=" + aNumbers + ", bNumbers=" + bNumbers + ", fromDateTime=" + fromDateTime + ", toDateTime=" + toDateTime + ", causeCode=" + causeCode + ", billProfile=" + billProfile + '}';
    }

}
