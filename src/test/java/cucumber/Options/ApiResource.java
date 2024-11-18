package cucumber.Options;

public enum ApiResource {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
    public String resource;
    
    ApiResource(String resource)
    {
    	this.resource = resource;
    }
    
    public String getResource()
    {
    	return resource;
    }

}
