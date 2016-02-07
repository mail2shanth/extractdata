package se.signa.cdrextract.commons;

import java.util.Properties;
/**
*
* @author VGajja
*/
public class ApplicationProperties {
	private static ApplicationProperties applicationProperties;
	Properties properties = null;
	private ApplicationProperties(){
	}
	public static ApplicationProperties getInstance(){
		if(applicationProperties == null){
			applicationProperties = new ApplicationProperties();
		}
		return applicationProperties;
	}
	public void setProperties(Properties props){
		properties = props;
	}
	public String getPropertyValue(String key){
		return properties.getProperty(key);
	}
}
