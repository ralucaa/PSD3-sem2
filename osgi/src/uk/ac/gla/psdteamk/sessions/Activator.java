package uk.ac.gla.psdteamk.sessions;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import uk.ac.gla.psdteamk.mycampus.service.MyCampusService;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;

public class Activator implements BundleActivator {
    public synchronized void start(BundleContext context)
    {
    	ServiceReference<DatabaseAdapterService> dbRef = context.getServiceReference(DatabaseAdapterService.class);
    	ServiceReference<MyCampusService> mcRef = context.getServiceReference(MyCampusService.class);
     
    	if (dbRef == null) {
    		System.out.println("Can't find Database Adapter service!");
    	} else if (mcRef == null) {
    		System.out.println("Can't find MyCampus service!");
    	} else {
    		
    		DatabaseAdapterService da = context.getService(dbRef);
    		MyCampusService mc = context.getService(mcRef);
    		
        	context.registerService(
                    SessionManagerService.class.getName(), new SessionManager(da, mc), null);
    	}
    	

    }
    
    public synchronized void stop(BundleContext context)
    {
    	
    }
}
