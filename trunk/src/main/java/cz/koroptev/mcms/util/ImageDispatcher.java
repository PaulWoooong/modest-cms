package cz.koroptev.mcms.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Context;
import org.apache.tapestry5.services.Dispatcher;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Response;

import com.jirout.common.filestore.ImageFileStore;
import com.jirout.common.filestore.ImageFileStoreImpl;
import com.jirout.common.filestore.ImageResizerImpl;
import com.jirout.common.filestore.ResizeRequest;

import cz.koroptev.mcms.services.AppModule;

/**
 * Dispatcher that serve images. If image is not requested control is send to
 * next dispatcher in chain. This chain of dispatchers is defined in master
 * dispatcher. see
 * {@link AppModule#contributeMasterDispatcher(org.apache.tapestry5.ioc.OrderedConfiguration, Dispatcher, Dispatcher)}
 * for more details.
 * 
 * @author jan
 * 
 */
public class ImageDispatcher implements Dispatcher {

    private final static Logger logger = Logger
	    .getLogger(ImageDispatcher.class);

    private final static String[] IMAGE_PARAMETER_WIDTH = { "width", "w" };

    private final static String[] IMAGE_PARAMETER_HEIGHT = { "height", "h" };

    private final static String[] IMAGE_PARAMETER_KEEP_ASPECT_RATIO = {
	    "keepAspectRatio", "keepaspectratio", "kar" };

    private final String MAPPED_PATH = "/images";

    @Inject
    private Context context;

    private ImageFileStore fileStore;

    private String getParam(String paramName) {
	String systemProp = System.getProperty(paramName);
	if (systemProp == null || systemProp.length() == 0) {
	    return context.getInitParameter(paramName);
	} else {
	    return systemProp;
	}
    }

    private void initFileStore() {
	logger.info("inicializing Servlet Imager");
	fileStore = new ImageFileStoreImpl();
	fileStore.setRelativePath(getParam("mcns.imageRelativePath"));
	fileStore.setAbsolutePath(new File(getParam("mcns.dataBasePath"))
		.getAbsolutePath());
	logger.debug("initilizing image dispatcher witch base path: ");
	logger.debug(new File("./").getAbsolutePath());
	logger.debug(new File("").getAbsolutePath());
	logger.debug(new File(".").getAbsolutePath());
	logger.debug(new File("/").getAbsolutePath());
	logger.debug(new File("WEB-INF/file-store").getAbsolutePath());
	logger.debug("super: "
		+ new File(getParam("mcns.dataBasePath")).getAbsolutePath());
	logger.debug("pokus: " + getParam("mcns.imageRelativePath"));
	fileStore.setImageResizer(new ImageResizerImpl());
	ServiceProvider.setImageFileStore(fileStore);
	try {
	    fileStore.init();
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    throw new ScmException(e.getMessage(), e);
	}
    }

    public boolean dispatch(Request request, Response response)
	    throws IOException {
	if (fileStore == null) {
	    initFileStore();
	}
	String path = request.getPath();
	logger.debug("checking path: " + path);
	if (path.startsWith(MAPPED_PATH)) {

	    FileRequest fileRequest = new FileRequest(request);
	    Integer idImage = fileRequest.getFileId();
	    logger.debug("requested image: " + idImage);
	    if (idImage == 0) {
		idImage = getDefaultIdImage(request);
	    }
	    OutputStream out = response.getOutputStream(getImageFileStore()
		    .getContentType(
			    getImageFileStore().getFileExtension(idImage)));

	    // try to extract parameters: width, height and kar
	    ResizeRequest resizeRequest = new ResizeRequest();
	    resizeRequest.setWidth(fileRequest
		    .getParameterAsInt(IMAGE_PARAMETER_WIDTH));
	    resizeRequest.setHeight(fileRequest
		    .getParameterAsInt(IMAGE_PARAMETER_HEIGHT));
	    resizeRequest
		    .setKeepAspectRatio((fileRequest
			    .getParameter(IMAGE_PARAMETER_KEEP_ASPECT_RATIO) == null) ? false
			    : true);
	    try {
		getImageFileStore().writeImageToStream(idImage, out,
			resizeRequest);
		out.flush();
	    } catch (IOException be) {
		logger.warn("unable to find requested image id: "
			+ fileRequest.getFileName());
		/**
		 * There is no reason to write full stack trace. We know what
		 * happened.
		 * 
		 * try to show standard "no image image"
		 */
		try {
		    out = response.getOutputStream(getImageFileStore()
			    .getContentType(
				    getImageFileStore().getFileExtension(
					    idImage)));
		    getImageFileStore().writeImageToStream(
			    getDefaultIdImage(request), out, resizeRequest);
		    out.flush();
		} catch (IOException e) {
		    logger.error("unable to load default \"no image\" image");
		}
		// be.printStackTrace();
	    } finally {
		out.close();
	    }
	    return true;
	} else {
	    return false;
	}
    }

    public ImageFileStore getImageFileStore() {
	return fileStore;
    }

    /**
     * base on servlet mapping return default image id.
     * 
     * @param request
     * @return
     */
    private Integer getDefaultIdImage(Request request) {
	Integer defaultIdImage = getImageFileStore().getDefaultImageId(
		MAPPED_PATH);
	if (defaultIdImage == null) {
	    defaultIdImage = 0;
	}
	return defaultIdImage;
    }

}