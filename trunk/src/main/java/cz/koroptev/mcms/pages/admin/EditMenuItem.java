package cz.koroptev.mcms.pages.admin;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.Retain;
import org.apache.tapestry5.ioc.annotations.Inject;

import cz.koroptev.mcms.model.MenuItem;
import cz.koroptev.mcms.pages.Index;
import cz.koroptev.mcms.services.MenuItemService;

/**
 * Page for creating & editing user.
 * 
 * @author jan
 * 
 */
public class EditMenuItem {

    private final static Logger logger = Logger.getLogger(EditMenuItem.class);

    @Inject
    private MenuItemService menuItemService;

    private MenuItem menuItem;

    @Retain
    private Integer idCategory;

    void onActivate(Integer idUser) {
	menuItem = menuItemService.getById(idUser);
	this.idCategory = idUser;
    }

    Integer onPassivate() {
	return idCategory;
    }

    public Object onSuccess() {
	logger.debug("on success:" + idCategory + ", " + menuItem + ", "
		+ menuItem.getName());
	menuItem.setId(idCategory);
	idCategory = 0;
	menuItemService.createOrUpdate(menuItem);
	return Index.class;
    }

    /**
     * @return the menuItem
     */
    public MenuItem getMenuItem() {
	return menuItem;
    }

    /**
     * @param menuItem
     *            the menuItem to set
     */
    public void setMenuItem(MenuItem menuItem) {
	this.menuItem = menuItem;
    }

}
