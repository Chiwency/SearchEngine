package xin.wangning.servlet;

import xin.wangning.UserFilter;
import xin.wangning.domain.User;
import xin.wangning.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserServlet extends BaseServlet {
    private UserService service = new UserService();
    public String Login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if(user.getUsername()==null){
            return "参数不足";
        }else if(user.getPassword()==null){
            return "参数不足";
        }
        boolean result = service.checkLogin(user.getUsername(),user.getPassword());
        if(result){
            HttpSession session = req.getSession();
            UserFilter.userSessionIdMap.put(user.getUsername(),session.getId());
            session.setAttribute("user",user);
            return "success";
        }else {
            return "fail";
        }
    }
    public String getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        System.out.println("输出用户信息");
        if(user==null){
            return "fail";
        }
        return user.getUsername();
    }

    public String deleteUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("删除用户信息");
        req.getSession().removeAttribute("user");
        return "success";
    }

    public String checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(session.getId().equals(UserFilter.userSessionIdMap.get(user.getUsername()))){
            return "isIn";
        }else {
            return "notIn";
        }
    }


}
