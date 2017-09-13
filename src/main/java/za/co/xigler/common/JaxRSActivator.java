package za.co.xigler.common;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath(JaxRSActivator.REST_PATH)
public class JaxRSActivator extends Application {

	public static final String REST_PATH = "api";

}
