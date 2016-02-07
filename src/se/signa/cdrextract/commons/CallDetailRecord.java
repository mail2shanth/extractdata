package se.signa.cdrextract.commons;

import java.util.Date;

/**
 *
 * @author VGajja
 */
public class CallDetailRecord {
	
	String aNumber;
	String bNumber;
	String causeCode;
	String originatingIp;
	String terminatingIp;
	Date eventDate;
	long usage;
	
	public String getANumber() {
		return aNumber;
	}
	public void setaNumber(String aNumber) {
		this.aNumber = aNumber;
	}
	public String getBNumber() {
		return bNumber;
	}
	public void setbNumber(String bNumber) {
		this.bNumber = bNumber;
	}
	public String getCauseCode() {
		return causeCode;
	}
	public void setCauseCode(String causeCode) {
		this.causeCode = causeCode;
	}
	public String getOriginatingIp() {
		return originatingIp;
	}
	public void setOriginatingIp(String originatingIp) {
		this.originatingIp = originatingIp;
	}
	public String getTerminatingIp() {
		return terminatingIp;
	}
	public void setTerminatingIp(String terminatingIp) {
		this.terminatingIp = terminatingIp;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public long getUsage() {
		return usage;
	}
	public void setUsage(long usage) {
		this.usage = usage;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aNumber == null) ? 0 : aNumber.hashCode());
		result = prime * result + ((bNumber == null) ? 0 : bNumber.hashCode());
		result = prime * result
				+ ((causeCode == null) ? 0 : causeCode.hashCode());
		result = prime * result
				+ ((eventDate == null) ? 0 : eventDate.hashCode());
		result = prime * result
				+ ((originatingIp == null) ? 0 : originatingIp.hashCode());
		result = prime * result
				+ ((terminatingIp == null) ? 0 : terminatingIp.hashCode());
		result = prime * result + (int) (usage ^ (usage >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CallDetailRecord other = (CallDetailRecord) obj;
		if (aNumber == null) {
			if (other.aNumber != null)
				return false;
		} else if (!aNumber.equals(other.aNumber))
			return false;
		if (bNumber == null) {
			if (other.bNumber != null)
				return false;
		} else if (!bNumber.equals(other.bNumber))
			return false;
		if (causeCode == null) {
			if (other.causeCode != null)
				return false;
		} else if (!causeCode.equals(other.causeCode))
			return false;
		if (eventDate == null) {
			if (other.eventDate != null)
				return false;
		} else if (!eventDate.equals(other.eventDate))
			return false;
		if (originatingIp == null) {
			if (other.originatingIp != null)
				return false;
		} else if (!originatingIp.equals(other.originatingIp))
			return false;
		if (terminatingIp == null) {
			if (other.terminatingIp != null)
				return false;
		} else if (!terminatingIp.equals(other.terminatingIp))
			return false;
		if (usage != other.usage)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CallDetailRecord [aNumber=" + aNumber + ", bNumber=" + bNumber
				+ ", causeCode=" + causeCode + ", originatingIp="
				+ originatingIp + ", terminatingIp=" + terminatingIp
				+ ", eventDate=" + eventDate + ", usage=" + usage + "]";
	}
}
