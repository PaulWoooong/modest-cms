package cz.koroptev.mcms.services;

import org.apache.log4j.Logger;
import org.apache.tapestry5.hibernate.HibernateTransactionDecorator;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.ioc.annotations.Match;
import org.apache.tapestry5.services.Dispatcher;
import org.apache.tapestry5.urlrewriter.URLRewriterRule;

import cz.koroptev.mcms.services.hibernate.ArticleFormServiceImpl;
import cz.koroptev.mcms.services.hibernate.ArticleServiceImpl;
import cz.koroptev.mcms.services.hibernate.CategoryServiceImpl;
import cz.koroptev.mcms.services.hibernate.ImageServiceImpl;
import cz.koroptev.mcms.services.hibernate.MenuItemServiceImpl;
import cz.koroptev.mcms.services.hibernate.PageCategoryServiceImpl;
import cz.koroptev.mcms.services.hibernate.PathServiceImpl;
import cz.koroptev.mcms.services.hibernate.RewriteRuleInboundImpl;
import cz.koroptev.mcms.services.hibernate.RewriteRuleOutboundImpl;
import cz.koroptev.mcms.services.hibernate.UserServiceImpl;
import cz.koroptev.mcms.services.hibernate.WelcomePageFormServiceImpl;
import cz.koroptev.mcms.services.hibernate.WelcomePageServiceImpl;
import cz.koroptev.mcms.util.AccessController;
import cz.koroptev.mcms.util.ImageDispatcher;

/**
 * Here are defined service bindings.
 * 
 * @author jan
 * 
 */
public class AppModule {

    private final static Logger logger = Logger.getLogger(AppModule.class);

    public static void bind(ServiceBinder binder) {
	logger.debug("Binding services in app module");
	binder.bind(AccessController.class).withId("AccessController");
	binder.bind(ImageDispatcher.class).withId("ImageDispatcher");
	binder.bind(ArticleService.class, ArticleServiceImpl.class);
	binder.bind(ArticleFormService.class, ArticleFormServiceImpl.class);
	binder.bind(UserService.class, UserServiceImpl.class);
	binder.bind(CategoryService.class, CategoryServiceImpl.class);
	binder.bind(ImageService.class, ImageServiceImpl.class);
	binder.bind(MenuItemService.class, MenuItemServiceImpl.class);
	binder.bind(PageCategoryService.class, PageCategoryServiceImpl.class);
	binder.bind(RewriteRuleInbound.class, RewriteRuleInboundImpl.class);
	binder.bind(RewriteRuleOutbound.class, RewriteRuleOutboundImpl.class);
	binder.bind(WelcomePageService.class, WelcomePageServiceImpl.class);
	binder.bind(WelcomePageFormService.class,
		WelcomePageFormServiceImpl.class);
	binder.bind(PathService.class, PathServiceImpl.class);
    }

    public static void contributeURLRewriter(
	    OrderedConfiguration<URLRewriterRule> configuration,
	    @InjectService("RewriteRuleInbound") RewriteRuleInbound rewriteRuleInbound,
	    @InjectService("RewriteRuleOutbound") RewriteRuleOutbound rewriteRuleOutbound) {
	configuration.add("rewritePathInbound", rewriteRuleInbound);
	configuration.add("rewriteRuleOutbound", rewriteRuleOutbound);
    }

    /**
     * Tells to master dispatcher that access controller should be run before
     * page rendering.
     * 
     * @param configuration
     * @param accessController
     */
    public void contributeMasterDispatcher(
	    OrderedConfiguration<Dispatcher> configuration,
	    @InjectService("AccessController") Dispatcher accessController,
	    @InjectService("ImageDispatcher") Dispatcher imageDispatcher) {
	configuration.add("AccessController", accessController,
		"before:PageRender");
	configuration.add("ImageDispatcher", imageDispatcher,
		"before:AccessController");
    }

    @Match("*Service")
    public static <T> T decorateTransactionally(
	    HibernateTransactionDecorator decorator, Class<T> serviceInterface,
	    T delegate, String serviceId) {
	/**
	 * Decorate all services with Hibernate transaction support.
	 */
	return decorator.build(serviceInterface, delegate, serviceId);
    }

}
