package cz.koroptev.mcms.services.hibernate;

import org.apache.log4j.Logger;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.cfg.Configuration;

import cz.koroptev.mcms.services.AppModule;
import cz.koroptev.mcms.services.ConfigurationService;

/**
 * Configuration that use commonly used configuration
 * {@link ConfigurationService}. See field {@link #hibernateConfigurationKeys}
 * where are stored all configuration items that are customizable.
 * 
 * @author jan
 * 
 */
public class CustomHibernateConfigurerImpl implements CustomHibernateConfigurer {

    private final static Logger logger = Logger.getLogger(AppModule.class);

    public final static String[] hibernateConfigurationKeys = new String[] {
	    "hibernate.dialect", "hibernate.connection.username",
	    "hibernate.connection.password", "hibernate.connection.url",
	    "hibernate.connection.driver_class" };

    @Inject
    private ConfigurationService configurationService;

    public void configure(Configuration configuration) {
	logger.debug("Configuring hibernate");
	for (String configurationKey : hibernateConfigurationKeys) {
	    System.out.println(configurationKey + ": "
		    + configurationService.getProperty(configurationKey));
	    configuration.setProperty(configurationKey, configurationService
		    .getProperty(configurationKey));
	}

    }

}
