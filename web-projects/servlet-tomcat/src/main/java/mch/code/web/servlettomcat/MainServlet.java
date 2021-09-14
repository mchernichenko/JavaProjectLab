package mch.code.web.servlettomcat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/users/")
public class MainServlet extends HttpServlet {
    private List<User> listUsers;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setBirthday(getDate(request.getParameter("date")));
        user.setId(user.getId());  // id пользователя это счётчик => в параметре не приходит
        listUsers.add(user);

        PrintWriter pw = response.getWriter();
        pw.write("User1 " + user.getName() + " успешно был создан");
    }

    private Date getDate(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

