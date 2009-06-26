package cz.koroptev.scms.components;

import org.apache.tapestry5.annotations.Parameter;

import cz.koroptev.scms.base.UserAwareComponent;
import cz.koroptev.scms.model.MenuItem;

public class MenuElement extends UserAwareComponent {

    @Parameter(required = true, defaultPrefix = "prop")
    private MenuItem menuItem;

    /**
     * @return the menuItem
     */
    public MenuItem getMenuItem() {
        return menuItem;
    }

    /**
     * @param menuItem the menuItem to set
     */
    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
    
    
}
