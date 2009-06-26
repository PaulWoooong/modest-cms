package cz.koroptev.scms.pages.admin;

import org.apache.tapestry5.annotations.Retain;
import org.apache.tapestry5.ioc.annotations.Inject;

import cz.koroptev.scms.model.MenuItem;
import cz.koroptev.scms.pages.Index;
import cz.koroptev.scms.services.MenuItemService;

/**
 * Page for creating & editing user.
 * 
 * @author jan
 * 
 */
public class EditMenuItem {

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
	System.out.println("on success:" + idCategory + ", " + menuItem + ", " + menuItem.getName());
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
