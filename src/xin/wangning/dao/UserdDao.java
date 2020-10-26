package xin.wangning.dao;
import xin.wangning.domain.User;
public class UserdDao {
    public User findByName(String username){
        User user = new User();
        user.setUsername(username);
        user.setPassword("123456");
        return user;
    }

}
