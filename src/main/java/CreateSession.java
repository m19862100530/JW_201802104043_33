import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/createSession")
public class CreateSession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //如果请求对应着服务器中的一个session对象则返回该对象
        //如果如果服务器内存中没有session对象与当前请求对应，则创建一个session对象并返回他
        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(10);
        resp.getWriter().println("session will last 10 seconds");
    }
}
