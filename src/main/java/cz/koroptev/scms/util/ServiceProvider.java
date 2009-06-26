package cz.koroptev.scms.util;

import com.jirout.common.filestore.ImageFileStore;

/**
 * It's tricky class for getting image service.
 * 
 * @author jan
 * 
 */
public class ServiceProvider {

    private static ImageFileStore imageFileStore;

    public static ImageFileStore getImageFileStore() {
        return imageFileStore;
    }

    public static void setImageFileStore(ImageFileStore imageFileStore) {
        ServiceProvider.imageFileStore = imageFileStore;
    }

    
}
