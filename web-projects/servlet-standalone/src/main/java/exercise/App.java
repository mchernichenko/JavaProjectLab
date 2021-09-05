package exercise;

import exercise.servlet.WelcomeServlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.Context;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class App {

    private static int getPort(String port) {
        if (port.equals("")) {
            port = System.getenv("PORT");
        }
        if (port != null) {
            return Integer.parseInt(port);
        }
        return 5000;
    }

    public static Tomcat getApp(int port) {
        Tomcat app = new Tomcat();

        app.setBaseDir(System.getProperty("java.io.tmpdir"));
        app.setPort(port);

        // получаем доступ к конфигу контейнера сервлетов web.xml для регистрации в него сервлетов
        // в нашем случае web.xml нет, поэтому указываем пустой путь
        Context ctx = app.addContext("", new File(".").getAbsolutePath());

        // BEGIN
        // добавление сервлета в контейнер
        app.addServlet(ctx, WelcomeServlet.class.getSimpleName(), new WelcomeServlet());
        // прописываем сервлеты в конфигурации контейнера, хранящейся в web.xml и указываем по каким путям они доступны
        ctx.addServletMappingDecoded("", WelcomeServlet.class.getSimpleName());
        // END

        return app;
    }

    public static void main(String[] args) throws LifecycleException, IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("google.com", 80)); // получить внешний ip-адрес
        String InetIP = socket.getLocalAddress().toString();

        int port = getPort("5000");
        String LocalIP = InetAddress.getLocalHost().getHostAddress();
        String baseUrl = "---> http:/" + InetIP + ":" + port;
        System.out.println(baseUrl);

        Tomcat app = getApp(port); // получаем экземпляр tomcat с заданной конфигурацией
        app.start();   // стартуем Embedded Tomcat на указанном порту
        app.getServer().await();

    }
}
