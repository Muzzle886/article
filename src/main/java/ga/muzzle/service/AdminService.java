package ga.muzzle.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.muzzle.mapper.UserMapper;
import ga.muzzle.pojo.ReturnMessage;
import ga.muzzle.utils.MybatisUtils;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Muzzle
 */
public class AdminService {
    @SneakyThrows
    public static Boolean adminLogin(String username, String password) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<>(2);
        map.put("username", username);
        map.put("password", password);
        Long login = mapper.adminLogin(map);
        MybatisUtils.closeSqlSession(sqlSession);
        return login != null;
    }
}
