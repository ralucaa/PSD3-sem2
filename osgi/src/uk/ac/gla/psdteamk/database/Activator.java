package uk.ac.gla.psdteamk.database;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import uk.ac.gla.psdteamk.database.DatabaseAdapter;


public class Activator implements BundleActivator {
    public synchronized void start(BundleContext context)
    {
    	context.registerService(
                DatabaseAdapterService.class.getName(), new DatabaseAdapter(), null);
    }
    
    public synchronized void stop(BundleContext context)
    {
    	
    }
}
