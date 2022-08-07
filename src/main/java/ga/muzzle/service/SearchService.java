package ga.muzzle.service;

import ga.muzzle.mapper.ArticleMapper;
import ga.muzzle.mapper.UserMapper;
import ga.muzzle.pojo.Article;
import ga.muzzle.pojo.User;
import ga.muzzle.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Muzzle
 */
public class SearchService {
    public static List<Article> search(String method, String keyword) {
        keyword = "%" + keyword + "%";
        switch (method) {
            case "content":
                return searchArticle("article_text", keyword);
            case "title":
                return searchArticle("article_title", keyword);
            case "author":
                return searchArticleByAuthorName(keyword);
            case "tag":
                return searchArticleTag(keyword);
            default:
                return null;
        }
    }

    public static List<Article> searchArticle(String field, String keyword) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        Map<String, Object> map = new HashMap<>(2);
        map.put("field", field);
        map.put("keyword", keyword);
        List<Article> search = mapper.search(map);
        MybatisUtils.closeSqlSession(sqlSession);
        return search;
    }

    public static List<Article> searchArticleTag(String keyword) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        List<Long> articleIds = mapper.searchTag(keyword);
        List<Article> articles = new ArrayList<>();
        for (Long articleId : articleIds) {
            articles.add(mapper.getArticleById(articleId));
        }
        MybatisUtils.closeSqlSession(sqlSession);
        return articles;
    }

    public static List<Article> searchArticleByAuthorName(String keyword) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
        List<User> users = userMapper.searchUser(keyword);
        List<Article> articles = new ArrayList<>();
        for (User user : users) {
            articles.addAll(articleMapper.getArticleByUserId(user.getId()));
        }
        MybatisUtils.closeSqlSession(sqlSession);
        return articles;
    }
}
