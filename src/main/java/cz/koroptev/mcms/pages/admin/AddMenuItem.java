package cz.koroptev.mcms.pages.admin;

import org.apache.tapestry5.ioc.annotations.Inject;

import cz.koroptev.mcms.entities.MenuItem;
import cz.koroptev.mcms.entities.PageCategory;
import cz.koroptev.mcms.entities.WelcomePage;
import cz.koroptev.mcms.form.CreateMenuItem;
import cz.koroptev.mcms.pages.Index;
import cz.koroptev.mcms.services.MenuItemService;
import cz.koroptev.mcms.services.PageCategoryService;
import cz.koroptev.mcms.services.WelcomePageService;

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
