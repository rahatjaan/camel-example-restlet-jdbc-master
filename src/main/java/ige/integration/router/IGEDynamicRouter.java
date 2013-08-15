package ige.integration.router;

public class IGEDynamicRouter {

	
	public String slip(String body) {
	    
		/*bodies.add(body);
	    invoked++;

	    if (invoked == 1) {
	        return "mock:a";
	    } else if (invoked == 2) {
	        return "mock:b,mock:c";
	    } else if (invoked == 3) {
	        return "direct:foo";
	    } else if (invoked == 4) {
	        return "mock:result";
	    }

	    // no more so return null*/
	    return "http://localhost:8080/RestfullConsumer/InRoomDinning";
	}
}
