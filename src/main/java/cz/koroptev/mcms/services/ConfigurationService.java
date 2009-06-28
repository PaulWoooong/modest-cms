package cz.koroptev.mcms.services;

/**
 * Application related configuration service. Allows to read and set all
 * application configuration. There are three ways how to configure this
 * application:
 * <ul>
 * <li>web.xml file context-param tag</li>
 * <li>system property</li>
 * <li>code</li>
 * </ul>
 * Configurations are loaded in same way like previous list. file 'web.xml' can
 * be overridden by System property and system property can be overridden by
 * some java code.
 * 
 * @author jan
 * 
 */
public interface ConfigurationService {

    String getProperty(String key);

    void setProperty(String key, String value);

}
