package stepdefination;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@Deleteplace")
	public void beforeScenerio() throws IOException
	{
		StepDefination m = new StepDefination();
		if(StepDefination.placeId == null)
		{
			m.add_place_payload("anbu", "tamil", "kaikati");
			m.user_calls_with_post_http_request("addPlaceAPI","post");
			m.checkplaceid_to_by_using("anbu", "getPlaceAPI");
		}
		
		
	}
	
	
}
