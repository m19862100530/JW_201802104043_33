import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showCookies")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //得到所有的cookie信息
        Cookie[] allCookies = req.getCookies();
        if (allCookies==null){
            resp.getWriter().println("no cookies available");
        }else {
            for (Cookie cookie:allCookies){
                resp.getWriter().println(cookie.getName() + "=" +cookie.getValue());
            }
        }
    }
}
