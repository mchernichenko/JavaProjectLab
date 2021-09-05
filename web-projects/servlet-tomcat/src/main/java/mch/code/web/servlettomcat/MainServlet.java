package mch.code.web.servlettomcat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users/")
public class MainServlet extends HttpServlet {
    private List<User> listUsers;
    private int idCounter;

    @Override
    public void init() throws ServletException {
        super.init();
        this.listUsers = new ArrayList<>();
    }

    // вернуть список пользователей
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter(); // ответ всегда пишем в OutputStream
        for (User user : this.listUsers) {
            pw.write(user.toString());
        }
        pw.close();
    }

    // создание пользователя
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        super.doPost(req, resp);
    }

}

