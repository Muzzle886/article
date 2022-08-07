package ga.muzzle.pojo;

import java.text.SimpleDateFormat;

/**
 * @author himea
 */
public class Article {
    private long id;
    private String title;
    private long author;
    private String authorName;
    private String date;
    private long categoryId;
    private String category;
    private String text;

    /**
     * 文章导入
     *
     * @param title    文章标题
     * @param author   文章作者
     * @param category 分类id
     * @param text     文章内容
     */
    public Article(String title, long author, long category, String text) {
        this.title = title;
        this.author = author;
        this.date = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        this.categoryId = category;
        this.text = text;
    }

    public Article(long id, String title, long category, String text) {
        this.id = id;
        this.title = title;
        this.categoryId = category;
        this.text = text;
    }

    /**
     * 文章导出
     *
     * @param id         文章id
     * @param title      文章标题
     * @param authorName 文章作者
     * @param date       发布日期
     * @param category   分类名称
     * @param text       文章内容
     */
    public Article(long id, String title, long author, String authorName, String date, long categoryId, String category, String text) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.authorName = authorName;
        this.date = date;
        this.categoryId = categoryId;
        this.category = category;
        this.text = text;
    }

    /**
     * 原始数据
     *
     * @param id         文章id
     * @param title      文章标题
     * @param author     文章作者
     * @param date       发布日期
     * @param categoryId 分类id
     * @param text       文本
     */
    public Article(long id, String title, long author, String date, long categoryId, String text) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
        this.categoryId = categoryId;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getAuthor() {
        return author;
    }

    public void setAuthor(long author) {
        this.author = author;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
