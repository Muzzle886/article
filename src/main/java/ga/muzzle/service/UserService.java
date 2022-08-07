package ga.muzzle.service;

import ga.muzzle.mapper.UserMapper;
import ga.muzzle.pojo.ReturnMessage;
import ga.muzzle.pojo.User;
import ga.muzzle.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Muzzle
 */
public class UserService {

    public static ReturnMessage createUser(User user) {
        ReturnMessage returnMessage = new ReturnMessage(false, "未知错误");
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        if (mapper.getUserByUserName(user.getUsername()) != null) {
            returnMessage.setMessage("用户已存在");
            MybatisUtils.closeSqlSession(sqlSession);
            return returnMessage;
        }
        if (mapper.getUserByName(user.getName()) != null) {
            returnMessage.setMessage("昵称已存在");
            MybatisUtils.closeSqlSession(sqlSession);
            return returnMessage;
        }
        mapper.reg(user);
        returnMessage.setSuccess(true);
        returnMessage.setMessage("注册成功");
        MybatisUtils.closeSqlSession(sqlSession);
        return returnMessage;
    }

    public static boolean banUser(long id) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.banUser(id);
        MybatisUtils.closeSqlSession(sqlSession);
        return i > 0;
    }

    public static boolean deleteUser(long id) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.removeUser(id);
        MybatisUtils.closeSqlSession(sqlSession);
        return i > 0;
    }

    public static boolean follow(long follower, long followers) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<>(2);
        map.put("follower", follower);
        map.put("followers", followers);
        int i = mapper.follow(map);
        MybatisUtils.closeSqlSession(sqlSession);
        return i > 0;
    }

    public static boolean unfollow(long follower, long followers) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<>(2);
        map.put("follower", follower);
        map.put("followers", followers);
        int i = mapper.unfollow(map);
        MybatisUtils.closeSqlSession(sqlSession);
        return i > 0;
    }

    public static boolean getFollowStatus(long follower, long followers) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<>(2);
        map.put("follower", follower);
        map.put("followers", followers);
        long status = mapper.getFollowStatus(map);
        return status > 0;
    }

    public static long getFollowCount(long id) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        long count = mapper.getFollowCount(id);
        MybatisUtils.closeSqlSession(sqlSession);
        return count;
    }
}
