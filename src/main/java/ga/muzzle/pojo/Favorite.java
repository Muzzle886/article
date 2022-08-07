package ga.muzzle.pojo;

public class Favorite {
    private Long id;
    private Long articleId;
    private Long userId;

    public Favorite(Long id, Long articleId, Long userId) {
        this.id = id;
        this.articleId = articleId;
        this.userId = userId;
    }

    public Favorite(Long articleId, Long userId) {
        this.articleId = articleId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
