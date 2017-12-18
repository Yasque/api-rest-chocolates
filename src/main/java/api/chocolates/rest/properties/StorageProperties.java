package api.chocolates.rest.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {
	
	/**
     * Folder location for storing files
     */

	private String location = "./src/main/resources/public/img";


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
