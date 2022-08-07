package ga.muzzle.mapper;

import ga.muzzle.pojo.Article;
import ga.muzzle.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @author himear
 */
public interface FileMapper {
    List<Article> exportArticle();
    void importArticle(Map<String,Object> map);
    List<User> exportUser();
    void importUser(Map<String,Object> map);
}
