package cz.koroptev.mcms.services.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;

import com.jirout.common.Chunk;

import cz.koroptev.mcms.model.Article;
import cz.koroptev.mcms.services.ArticleService;
import cz.koroptev.mcms.util.ScmException;

public class ArticleServiceImpl extends
	SingleIdObjectHibernateServiceImpl<Article> implements ArticleService {

    @Override
    public Class<Article> getEntityClass() {
	return Article.class;
    }

    @SuppressWarnings("unchecked")
    public List<Article> getArticlesByCategory(Integer idCategory) {
	try {
	    Query query = getSession().createQuery(
		    "select article from Article as article "
			    + "where article.category.id = :idCategory "
			    + "order by dateCreate");
	    query.setInteger("idCategory", idCategory);
	    return query.list();
	} catch (HibernateException e) {
	    logger.error(e.getMessage(), e);
	    throw new ScmException(e.getMessage(), e);
	}
    }

    @SuppressWarnings("unchecked")
    public List<Article> getArticles(Chunk chunk) {
	try {
	    return getSession().createCriteria(Article.class).addOrder(
		    Order.asc("dateCreate")).setMaxResults(chunk.getSize())
		    .setFirstResult(chunk.getStart()).list();
	} catch (HibernateException e) {
	    logger.error(e.getMessage(), e);
	    throw new ScmException(e.getMessage(), e);
	}
    }

}
