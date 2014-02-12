package uk.ac.gla.psdteamk.mycampus;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import uk.ac.gla.psdteamk.mycampus.service.MyCampusService;


public class Activator implements BundleActivator {
    public void start(BundleContext context)
    {
    	context.registerService(
                MyCampusService.class.getName(), new MyCampusStub(), null);
    	
    }
    
    public void stop(BundleContext context)
    {
    	
    }
}
