import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ga.muzzle.mapper.ArticleMapper;
import ga.muzzle.pojo.Article;
import ga.muzzle.service.SearchService;
import ga.muzzle.service.UserService;
import ga.muzzle.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class SomeTest {
    @Test
    public void LogTest() throws JsonProcessingException {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        List<Article> allArticle = mapper.getAllArticle();
        System.out.println(new ObjectMapper().writeValueAsString(allArticle));
    }
    @Test
    public void getFollowStatusTest(){
        System.out.println(UserService.getFollowStatus(1, 3));
    }
}
