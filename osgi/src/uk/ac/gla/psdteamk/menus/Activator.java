package uk.ac.gla.psdteamk.menus;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;

public class Activator implements BundleActivator {
    public synchronized void start(BundleContext context)
    {
    	ServiceReference ref = context.getServiceReference(SessionManagerService.class.getName());
    	
    	if (ref == null) {
    		System.out.println("Can't find Session Manager service.");
    	} else {
    		System.out.println("Start managing some sessions...");
    	}
    	
    	
    }
    
    public synchronized void stop(BundleContext context)
    {
    	
    }
}
