package exercise;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private int count = 0;

    @Override
    public void init() throws ServletException {
        super.init();
        log("Step#1. Run init method\n");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Step#2.Enter service method" + ++count + "\n");
        super.service(req, resp);
        resp.getWriter().write("Step#4. Exit service method" + count + "\n");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("Step#3. Run doGet method" + count + "\n");
    }

    @Override
    public void destroy() {
        log("Step#5. Run destroy mathod\n");
    }
}
