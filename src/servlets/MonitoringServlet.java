//中文UTF-8 NO BOM
package servlets;

import model.DataSourceConnection;
import model.WebPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Timer;

@WebServlet(urlPatterns = "/monitoring")
public class MonitoringServlet extends HttpServlet {
    private ArrayList<Timer> timers;

    public void init() {
        timers = new ArrayList<>();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // discard all previous status update schedules
        timers = clearTimers();
        ArrayList<WebPage> webPages = selectAll(request, response);

        for (int i = 0; i < webPages.size(); i++) {
            if (!webPages.get(i).getStatus().equals("NOT TRACKED")) {
                // update status manually
                webPages.get(i).setStatus();
                // set schedule that updates url status automatically (depending on its monitoring period)
                timers.get(i).schedule(webPages.get(i), 0, webPages.get(i).getMonitoringPeriod());
            }
        }

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        request.setAttribute("webPages", webPages);
        request.getRequestDispatcher("/monitoring_table").forward(request, response);
    }



    private ArrayList<Timer> clearTimers() {
        for (Timer timer : timers) {
            timer.cancel();
            timer.purge();
        }
        return new ArrayList<>();
    }

    private ArrayList<WebPage> selectAll (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<WebPage> webPages = new ArrayList<>();
        String sql = "SELECT * FROM url_table";
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        try (Connection connection = new DataSourceConnection().getConnection();
             Statement selectAll = connection.createStatement()) {

            ResultSet resultSelectAll = selectAll.executeQuery(sql);

            while (resultSelectAll.next()) {
                webPages.add(new WebPage(resultSelectAll.getInt("id"),
                        resultSelectAll.getString("url_address"),
                        resultSelectAll.getString("status"),
                        resultSelectAll.getString("url_name"),
                        resultSelectAll.getInt("monitoring_period"),
                        resultSelectAll.getInt("response_time"),
                        resultSelectAll.getInt("response_code"),
                        resultSelectAll.getString("response_substring"),
                        resultSelectAll.getString("response_charset"),                        
                        resultSelectAll.getInt("connect_timeout"),
                        resultSelectAll.getInt("read_timeout")));
                // add timers for each URL
                timers.add(new Timer(true));
            }

        } catch (ClassNotFoundException e) {
            request.getRequestDispatcher("/driver_error").forward(request, response);
        } catch (SQLException e) {
            request.getRequestDispatcher("/connection_error").forward(request, response);
        }

        return webPages;
    }
}
