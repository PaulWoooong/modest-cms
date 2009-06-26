package cz.koroptev.scms.pages.admin;

import org.apache.tapestry5.ioc.annotations.Inject;

import cz.koroptev.scms.form.CreateMenuItem;
import cz.koroptev.scms.model.MenuItem;
import cz.koroptev.scms.model.PageCategory;
import cz.koroptev.scms.model.WelcomePage;
import cz.koroptev.scms.pages.Index;
import cz.koroptev.scms.services.MenuItemService;
import cz.koroptev.scms.services.PageCategoryService;
import cz.koroptev.scms.services.WelcomePageService;

/**
 * Page with list of users.
 * 
 * @author jan
 * 
 */
public class AddMenuItem {

    @Inject
    private MenuItemService menuItemService;

    @Inject
    private WelcomePageService welcomePageService;

    @Inject
    private PageCategoryService pageCategoryService;

    private CreateMenuItem menuItem;

    public Object onSuccess() {
	if (menuItem.getType() == 1) {
	    WelcomePage welcomePage = new WelcomePage(null, menuItem.getUrl(),
		    menuItem.getTitle(), menuItem.getCaption());
	    welcomePageService.create(welcomePage);
	    MenuItem item = new MenuItem();
	    item.setAbstractPage(welcomePage);
	    item.setName(menuItem.getName());
	    menuItemService.create(item);
	} else {
	    PageCategory welcomePage = new PageCategory(null,
		    menuItem.getUrl(), menuItem.getTitle(), menuItem
			    .getCaption());
	    pageCategoryService.create(welcomePage);
	    MenuItem item = new MenuItem();
	    item.setAbstractPage(welcomePage);
	    item.setName(menuItem.getName());
	    menuItemService.create(item);
	}
	return Index.class;
    }

    /**
     * @return the menuItem
     */
    public CreateMenuItem getMenuItem() {
	return menuItem;
    }

    /**
     * @param menuItem
     *            the menuItem to set
     */
    public void setMenuItem(CreateMenuItem menuItem) {
	this.menuItem = menuItem;
    }

}
