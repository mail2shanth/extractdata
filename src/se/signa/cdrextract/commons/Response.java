
package se.signa.cdrextract.commons;

/**
 *
 * @author VGajja
 */
public class Response {
    
    int status;
    String outputFile = null;
    long totalRecords = 0;
    long timeConsumed = 0;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
    
    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public long getTimeConsumed() {
        return timeConsumed;
    }

    public void setTimeConsumed(long timeConsumed) {
        this.timeConsumed = timeConsumed;
    }

    @Override
    public String toString() {
        return "Response{" + "status=" + status + ", outputFile=" + outputFile + ", totalRecords=" + totalRecords + ", timeConsumed=" + timeConsumed + '}';
    }

    
}
