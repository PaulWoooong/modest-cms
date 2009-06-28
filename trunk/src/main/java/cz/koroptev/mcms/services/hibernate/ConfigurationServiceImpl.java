package cz.koroptev.mcms.services.hibernate;

import java.util.HashMap;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Context;

import cz.koroptev.mcms.services.ConfigurationService;

/**
 * Basic implementation.
 * 
 * @author jan
 * 
 */
public class ConfigurationServiceImpl implements ConfigurationService {

    @Inject
    private Context context;

    private final HashMap<String, String> configuration;

    public ConfigurationServiceImpl() {
	configuration = new HashMap<String, String>();
    }

    public String getProperty(String key) {
	String value = configuration.get(key);
	if (value == null || value.length() == 0) {
	    value = System.getProperty(key);
	    if (value == null || value.length() == 0) {
		return context.getInitParameter(key);
	    } else {
		return value;
	    }
	} else {
	    return value;
	}
    }

    public void setProperty(String key, String value) {
	configuration.put(key, value);
    }

}
