package ga.muzzle.mapper;

import ga.muzzle.pojo.Article;
import ga.muzzle.pojo.ArticleTag;
import ga.muzzle.pojo.Favorite;

import java.util.List;
import java.util.Map;

/**
 * @author himea
 */
public interface ArticleMapper {
    /**
     * 获取所有文章
     *
     * @return ga.muzzle.pojo.Article
     */
    List<Article> getAllArticle();

    /**
     * 通过Id获取文章
     *
     * @param id 文章id
     * @return 文章对象
     */
    Article getArticleById(long id);

    /**
     * 新建文章
     *
     * @param article 文章
     * @return 影响行数
     */
    int createArticle(Article article);

    /**
     * 通过用户id查询文章
     *
     * @param id 用户id
     * @return 文章列表
     */
    List<Article> getArticleByUserId(long id);

    /**
     * 新建文章标签map
     *
     * @param articleTag 文章标签对象
     * @return 影响行数
     */
    int setTagMap(ArticleTag articleTag);

    /**
     * 获取标签
     *
     * @param id 文章id
     * @return 标签列表
     */
    List<String> getTag(long id);

    /**
     * 获取标签数组
     *
     * @param id 文章id
     * @return 标签数组
     */
    List<Long> getTagId(long id);


    //收藏操作

    /**
     * 文章收藏
     *
     * @param map 文章id与用户id map
     * @return 影响行数
     */
    int collectionArticle(Map<String, Object> map);

    /**
     * 获取文章收藏人数
     *
     * @param id 文章id
     * @return list
     */
    List<Favorite> getCollectionCount(Long id);

    /**
     * 获取用户是否收藏文章
     *
     * @param favorite 收藏对象
     * @return list
     */
    List<Favorite> getCollectionStatus(Favorite favorite);

    /**
     * 取消收藏
     *
     * @param favorite 收藏对象
     */
    void removeCollection(Favorite favorite);

    /**
     * 删除文章的所有收藏
     * @param id 文章id
     */
    void removeAllCollection(long id);


    /**
     * 删除文章
     * 管理员专有
     *
     * @param id 文章id
     * @return 影响行数
     */
    int deleteArticle(Long id);

    /**
     * 修改文章
     *
     * @param article 文章
     * @return 影响行数
     */
    int editArticle(Article article);

    /**
     * 删除标签
     *
     * @param id 文章id
     */
    void deleteTag(long id);

    /**
     * 搜索文章
     *
     * @param map@return 文章对象
     */
    List<Article> search(Map<String, Object> map);

    /**
     * 搜索标签
     *
     * @param keyword 关键字
     * @return 文章标签
     */
    List<Long> searchTag(String keyword);

    /**
     * 获得用户收藏的文章
     *
     * @param id 用户id
     * @return 用户收藏文章列表
     */
    List<Article> getCollectionArticle(long id);
}
