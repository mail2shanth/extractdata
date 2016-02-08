
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
    private Date fromDate = null;
    private Date toDate = null;
    private String causeCode = null;
    private String billProfile = null;
    private String aNumbersString = null;
    private String bNumbersString = null;
    private String requestName = null;

    public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	public String getANumbersString() {
		return aNumbersString;
	}

	public void setANumbersString(String numbersString) {
		aNumbersString = numbersString;
	}

	public String getBNumbersString() {
		return bNumbersString;
	}

	public void setBNumbersString(String numbersString) {
		bNumbersString = numbersString;
	}

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

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDateTime) {
        this.fromDate = fromDateTime;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDateTime) {
        this.toDate = toDateTime;
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
    			//this.fromDate != null ||
    			//this.toDate != null ||
    			this.billProfile != null && !this.billProfile.isEmpty() ||
    			this.causeCode != null && !this.causeCode.isEmpty()){
    		return false;
    	}
    	return true;
    }

    @Override
    public String toString() {
        return "RequestDetail{" + "accName=" + accName + ", bndName=" + bndName + ", aNumbers=" + aNumbers + ", bNumbers=" + bNumbers + ", fromDateTime=" + fromDate + ", toDateTime=" + toDate + ", causeCode=" + causeCode + ", billProfile=" + billProfile + '}';
    }

}
