package cz.koroptev.mcms.components;

import org.apache.tapestry5.annotations.Parameter;

import cz.koroptev.mcms.base.UserAwareComponent;
import cz.koroptev.mcms.model.MenuItem;

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
