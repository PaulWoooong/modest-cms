package cz.koroptev.mcms.util;

import org.apache.log4j.Logger;
import org.apache.tapestry5.services.Request;

public class FileRequest {
    private final static Logger logger = Logger.getLogger(FileRequest.class);

    private Request request;

    public FileRequest(Request request) {
	this.request = request;
    }

    /**
     * Extract file name.
     * 
     * @return requested file name
     */
    public String getFileName() {
	String urlStr = request.getPath();
	int posOfSlash = urlStr.lastIndexOf("/");
	if (posOfSlash > 0) {
	    urlStr = urlStr.substring(posOfSlash + 1);
	}
	int posOfDot = urlStr.indexOf(".");
	if (posOfDot >= 0) {
	    urlStr = urlStr.substring(0, posOfDot);
	}
	urlStr.trim();
	if (urlStr.length() == 0) {
	    urlStr = "0";
	}
	return urlStr;
    }

    public Integer getFileId() {
	try {
	    return Integer.valueOf(getFileName());
	} catch (NumberFormatException nfe) {
	    logger.warn("Attempt to load image with invalid id ("
		    + getFileName() + ")");
	    return 0;
	}

    }

    /**
     * Get file extension.
     * 
     * @return file extension, if extension doesn't exists return
     *         <code>null</code>
     */
    public String getFileExtension() {
	String urlStr = request.getPath();
	int posOfDot = urlStr.indexOf(".");
	if (posOfDot >= 0) {
	    return urlStr.substring(posOfDot + 1);
	}
	return null;
    }

    /**
     * From request get some parameter.
     * 
     * @param name
     *            name of parameter
     * @return parameter value, if parameter not present return
     *         <code>null</code>
     */
    public String getParameter(String name) {
	return request.getParameter(name);
    }

    /**
     * For list of possible names return first founded value. It's for synonims
     * in parameter.
     * 
     * @param names
     *            field with names.
     * @return first parameter value. if there is no such parameters return
     *         <code>null</code>
     */
    public String getParameter(String names[]) {
	for (int i = 0; i < names.length; i++) {
	    String out = request.getParameter(names[i]);
	    if (out != null)
		return out;
	}
	return null;
    }

    /**
     * From request get some parameter as {@link Integer}.
     * 
     * @param name
     *            name of parameter
     * @return parameter value as {@link Integer}, if parameter not present
     *         return <code>null</code>
     */
    public Integer getParameterAsInt(String name, String aliasName) {
	String out = request.getParameter(name);
	if (out != null) {
	    return Integer.valueOf(out);
	}
	return null;
    }

    /**
     * For list of possible names return first founded value. It's for synonims
     * in parameter.
     * 
     * @param names
     *            field with names.
     * @return first parameter value as {@link Integer}. if there is no such
     *         parameters return <code>null</code>
     */
    public Integer getParameterAsInt(String names[]) {
	String out = getParameter(names);
	if (out != null) {
	    return Integer.valueOf(out);
	}
	return null;
    }

}
