package uk.ac.gla.psdteamk.sessions;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import uk.ac.gla.psdteamk.mycampus.service.MyCampusService;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;

public class Activator implements BundleActivator {
    public void start(BundleContext context)
    {
    	ServiceReference dbRef = context.getServiceReference(DatabaseAdapterService.class.getName());
    	ServiceReference mcRef = context.getServiceReference(MyCampusService.class.getName());
     
    	context.registerService(
                SessionManagerService.class.getName(), new SessionManager(), null);
    }
    
    public void stop(BundleContext context)
    {
    	
    }
}