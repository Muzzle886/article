package ga.muzzle.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.muzzle.mapper.ArticleMapper;
import ga.muzzle.pojo.Article;
import ga.muzzle.pojo.ArticleTag;
import ga.muzzle.pojo.ReturnMessage;
import ga.muzzle.utils.MybatisUtils;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author Muzzle
 */
public class ArticleService {

    public static boolean createArticle(String title, long author, long categoryId, String text, List<Long> tags) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        Article article = new Article(title, author, categoryId, text);
        int i = mapper.createArticle(article);
        ArticleTag articleTag = new ArticleTag(article.getId(), tags);
        mapper.setTagMap(articleTag);
        MybatisUtils.closeSqlSession(sqlSession);
        return i > 0;
    }

    public static boolean deleteArticle(long id) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        int i = mapper.deleteArticle(id);
        mapper.deleteTag(id);
        mapper.removeAllCollection(id);
        MybatisUtils.closeSqlSession(sqlSession);
        return i > 0;
    }

    public static boolean editArticle(long id, String title, String text, long categoryId, List<Long> tags) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        Article article = new Article(id, title, categoryId, text);
        ArticleTag articleTag = new ArticleTag(id, tags);
        int i = mapper.editArticle(article);
        mapper.deleteTag(article.getId());
        mapper.setTagMap(articleTag);
        MybatisUtils.closeSqlSession(sqlSession);
        return i > 0;
    }

    public static List<Article> getAllArticle() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        List<Article> allArticle = mapper.getAllArticle();
        MybatisUtils.closeSqlSession(sqlSession);
        return allArticle;
    }

    public static Article getArticle(long id) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        Article article = mapper.getArticleById(id);
        MybatisUtils.closeSqlSession(sqlSession);
        return article;
    }

    public static List<Article> getUserArticle(long id) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        List<Article> article = mapper.getArticleByUserId(id);
        MybatisUtils.closeSqlSession(sqlSession);
        return article;
    }
}
