package ga.muzzle.mapper;

import ga.muzzle.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @author himea
 */
public interface UserMapper {
    /**
     * 获取所有用户列表
     *
     * @return java.util.List<ga.muzzle.pojo.User>
     */
    List<User> getUser();

    /**
     * 通过id查询用户
     *
     * @param id 用户id
     * @return ga.muzzle.pojo.User
     */
    User getUserById(Long id);

    /**
     * 通过登录用户名查询用户
     *
     * @param username 登录用户名
     * @return ga.muzzle.pojo.User
     */
    User getUserByUserName(String username);

    /**
     * 通过昵称查询用户
     *
     * @param name 昵称
     * @return User
     */
    User getUserByName(String name);

    /**
     * 用户注册
     *
     * @param user 注册用户信息
     */
    void reg(User user);

    /**
     * 修改用户信息
     *
     * @param user 编辑用户信息
     */
    void editUser(User user);

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 影响行数
     */
    int removeUser(Long id);

    /**
     * 封禁用户
     *
     * @param id 用户id
     * @return 影响行数
     */
    int banUser(Long id);

    /**
     * 解封用户
     *
     * @param id 用户id
     */
    void pardonUser(Long id);

    /**
     * 管理员登录
     *
     * @param map map
     * @return 如正确返回管理员id
     */
    Long adminLogin(Map<String, Object> map);

    /**
     * 搜索用户
     *
     * @param keyword 关键字
     * @return 用户列表
     */
    List<User> searchUser(String keyword);

    /**
     * 关注用户
     * @param map map
     * @return 影响行数
     */
    int follow(Map<String,Object> map);

    /**
     * 取消关注
     * @param map map
     * @return 影响行数
     */
    int unfollow(Map<String,Object> map);

    long getFollowStatus(Map<String,Object> map);

    long getFollowCount(long id);
}
