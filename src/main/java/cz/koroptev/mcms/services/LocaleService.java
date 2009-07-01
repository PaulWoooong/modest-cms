package cz.koroptev.mcms.services;

import org.apache.tapestry5.services.Request;

/**
 * Simplify manipulation with localization. Allows to cut localization part from
 * URL.
 * 
 * @author jan
 * 
 */
public interface LocaleService {

    /**
     * Remove from URL localization part. If there is not any localization
     * identifier then URL is returned unchanged.
     * 
     * @param url
     * @return
     */
    String removeLocale(String url);

    /**
     * If url is newly created generated URL then methods helps to encode into
     * it requested localization.
     * 
     * @param url
     * @param request
     * @return
     */
    String applyLocale(String url, Request request);

}
