package cz.koroptev.mcms.services;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import com.jirout.common.Chunk;

import cz.koroptev.mcms.entities.Article;

/**
 * 
 * @author jan
 * 
 */
public interface ArticleService extends SingleIdObjectService<Article> {

    @CommitAfter
    void create(Article entity);

    @CommitAfter
    void update(Article entity);

    @CommitAfter
    void createOrUpdate(Article entity);

    @CommitAfter
    void remove(Integer id);

    List<Article> getArticlesByCategory(Integer idCategory);

    List<Article> getArticles(Chunk chunk);

}
