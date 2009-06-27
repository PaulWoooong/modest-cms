package cz.koroptev.mcms.components;

import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;

import cz.koroptev.mcms.base.UserAwareComponent;
import cz.koroptev.mcms.model.MenuItem;
import cz.koroptev.mcms.pages.Index;
import cz.koroptev.mcms.services.MenuItemService;

public class Menu extends UserAwareComponent {

    @Inject
    private MenuItemService menuItemService;

    private MenuItem menuItem;

    private List<MenuItem> menuItems;

    private int count;

    public List<MenuItem> getMenuItems() {
	menuItems = menuItemService.getAll();
	return menuItems;
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

    public Object onActionFromMoveRight(Integer idMenuItem) {
	System.out.println("Moving right");
	menuItemService.moveRight(idMenuItem);
	return Index.class;
    }

    public Object onActionFromMoveLeft(Integer idMenuItem) {
	System.out.println("Moving left");
	menuItemService.moveLeft(idMenuItem);
	return Index.class;
    }

    /**
     * @return the count
     */
    public int getCount() {
	return count;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(int count) {
	this.count = count;
    }

    public boolean isNotLast() {
	return getCount() < menuItems.size() - 1;
    }
}
