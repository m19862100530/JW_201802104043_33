package cn.edu.sdjzu.xg.bysj.controller.basic;

import cn.edu.sdjzu.xg.bysj.domain.User;
import cn.edu.sdjzu.xg.bysj.service.UserService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login.ctl")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //根据request对象，获得代表参数的JSON字串
        //String user_json = JSONUtil.getJSON(request);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //设置响应字符编码为UTF-8
        response.setContentType("text/html;charset=UTF-8");
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        //在数据库表中增加Department对象
        try {
            User loggedUser = UserService.getInstance().login(username, password);
            if (loggedUser != null) {
                message.put("message", "login successful");
               //返回当前请求关联的Session对象
                HttpSession session = request.getSession();
                //十分中没有操作则失效
                session.setMaxInactiveInterval(10 * 60);
                //将user对象写入session的属性，名称为currentUser，以便在其他请求中使用
                session.setAttribute("currentUser", loggedUser);
                response.getWriter().println(message);
                //重定向到索引页（菜单也）
                return;
            }
        } catch (SQLException e) {
            message.put("message", "数据库操作异常");
            e.printStackTrace();
        } catch (Exception e) {
            message.put("message", "网络异常");
        }
        //响应message到前端
        response.getWriter().println(message);
    }
}

