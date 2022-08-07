package ga.muzzle.pojo;

import java.util.List;

/**
 * @author Muzzle
 */
public class ArticleTag {
    private long articleId;
    private List<Long> tagsId;
    private List<String> tagsName;

    public ArticleTag(long articleId, List<Long> tagsId, List<String> tagsName) {
        this.articleId = articleId;
        this.tagsId = tagsId;
        this.tagsName = tagsName;
    }

    public ArticleTag(long articleId, List<Long> tagsId) {
        this.articleId = articleId;
        this.tagsId = tagsId;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public List<Long> getTagsId() {
        return tagsId;
    }

    public void setTagsId(List<Long> tagsId) {
        this.tagsId = tagsId;
    }

    public List<String> getTagsName() {
        return tagsName;
    }

    public void setTagsName(List<String> tagsName) {
        this.tagsName = tagsName;
    }
}
