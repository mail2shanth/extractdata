
package se.signa.cdrextract.commons;

import java.util.List;

/**
 *
 * @author VGajja
 */
public class Response {
    
    int status;
    List<String> outputFiles = null;
    long totalRecords = 0;
    long timeConsumed = 0;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
    
    public List<String> getOutputFiles() {
        return outputFiles;
    }

    public void setOutputFiles(List<String> outputFiles) {
        this.outputFiles = outputFiles;
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
        return "Response{" + "status=" + status + ", outputFile=" + outputFiles + ", totalRecords=" + totalRecords + ", timeConsumed=" + timeConsumed + '}';
    }

    
}
