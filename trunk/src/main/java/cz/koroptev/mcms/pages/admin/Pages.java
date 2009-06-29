package cz.koroptev.mcms.pages.admin;

import java.util.List;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

import cz.koroptev.mcms.entities.AbstractPage;
import cz.koroptev.mcms.services.PathService;

/**
 * Display list of abstract pages.
 * 
 * @author jan
 * 
 */
public class Pages {

    @Inject
    private PathService pathService;

    /**
     * Property for iterating.
     */
    private AbstractPage page;

    @Inject
    private BeanModelSource beanModelSource;

    @Inject
    private ComponentResources resources;

    @Inject
    private Block showWelcomePage, showArticle, showCategoryPage;
    
    public List<AbstractPage> getPages() {
	return pathService.getAll();
    }

    public BeanModel<AbstractPage> getModel() {
	BeanModel<AbstractPage> model = beanModelSource.createDisplayModel(
		AbstractPage.class, resources.getMessages());
	model.add("delete", null);
	model.add("View", null);
	return model;
    }

    public Block getViewBlock(){
	if ( page == null || page.getPageType()==null){
	    return null;
	}
	switch (page.getPageType()) {
	case 1:
	    return showWelcomePage;
	case 2:
	    return showArticle;
	case 3:
	    return showCategoryPage;
	default:
	    return null;
	}
    }
    
    void onActionFromDelete(Integer idUser) {
	pathService.delete(idUser);
    }

    public AbstractPage getPage() {
        return page;
    }

    public void setPage(AbstractPage page) {
        this.page = page;
    }

}
