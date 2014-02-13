package uk.ac.gla.psdteamk.sessions;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import uk.ac.gla.psdteamk.mycampus.service.MyCampusService;

public class Activator implements BundleActivator {
    public void start(BundleContext context)
    {
    	ServiceReference dbRef = context.getServiceReference(DatabaseAdapterService.class.getName());
    	ServiceReference mcRef = context.getServiceReference(MyCampusService.class.getName());
     
    }
    
    public void stop(BundleContext context)
    {
    	
    }
}
