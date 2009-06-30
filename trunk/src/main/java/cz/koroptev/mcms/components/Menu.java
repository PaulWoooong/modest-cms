package cz.koroptev.mcms.components;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.ioc.annotations.Inject;

import cz.koroptev.mcms.base.UserAwareComponent;
import cz.koroptev.mcms.entities.MenuItem;
import cz.koroptev.mcms.pages.Index;
import cz.koroptev.mcms.services.MenuItemService;

public class Menu extends UserAwareComponent {

    private final static Logger logger = Logger.getLogger(Menu.class);

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
	logger.debug("Moving right");
	menuItemService.moveRight(idMenuItem);
	return Index.class;
    }

    public Object onActionFromMoveLeft(Integer idMenuItem) {
	logger.debug("Moving left");
	menuItemService.moveLeft(idMenuItem);
	return Index.class;
    }

    public boolean isMovableLeft() {
	return count > 0;
    }

    public boolean isMovableRight() {
	return count < getMenuItems().size() - 1;
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
