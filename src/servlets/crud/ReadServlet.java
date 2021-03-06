//中文UTF-8 NO BOM
package servlets.crud;

import model.DataSourceConnection;
import model.WebPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = "/read")
public class ReadServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPage = Integer.parseInt(request.getParameter("id"));
        WebPage webPage = selectById(idPage, request, response);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        request.setAttribute("webPage", webPage);
        request.getRequestDispatcher("/update_table").forward(request, response);
    }

    private WebPage selectById (int idPage, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql = "SELECT * FROM url_table WHERE id=?";
        WebPage webPage = null;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        try (Connection connection = new DataSourceConnection().getConnection();
             PreparedStatement getById = connection.prepareStatement(sql)) {

            getById.setInt(1, idPage);
            ResultSet resultGetById = getById.executeQuery();

            while (resultGetById.next()) {
                webPage = new WebPage(resultGetById.getInt("id"),
                        resultGetById.getString("url_address"),
                        resultGetById.getString("status"),
                        resultGetById.getString("url_name"),
                        resultGetById.getInt("monitoring_period"),
                        resultGetById.getInt("response_time"),
                        resultGetById.getInt("response_code"),
                        resultGetById.getString("response_substring"),
                        resultGetById.getString("response_charset"),                        
                        resultGetById.getInt("connect_timeout"),
                        resultGetById.getInt("read_timeout"));
            }

        } catch (ClassNotFoundException e) {
            request.getRequestDispatcher("/driver_error").forward(request, response);
        } catch (SQLException e) {
            request.getRequestDispatcher("/connection_error").forward(request, response);
        }

        return webPage;
    }

}
