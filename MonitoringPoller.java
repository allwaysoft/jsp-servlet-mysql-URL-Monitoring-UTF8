
//中文UTF-8 NO BOM

import model.DataSourceConnection;
import model.WebPage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class MonitoringPoller {

	public static void main(String args[]) throws IOException {

		ArrayList<WebPage> webPages = new ArrayList<>();
		String sql = "SELECT * FROM url_table";
		try (Connection connection = new DataSourceConnection().getConnection();
				Statement selectAll = connection.createStatement()) {

			ResultSet resultSelectAll = selectAll.executeQuery(sql);

			while (resultSelectAll.next()) {
				webPages.add(new WebPage(resultSelectAll.getInt("id"), resultSelectAll.getString("url_address"),
						resultSelectAll.getString("status"), resultSelectAll.getString("url_name"),
						resultSelectAll.getInt("monitoring_period"), resultSelectAll.getInt("response_time"),
						resultSelectAll.getInt("response_code"), resultSelectAll.getString("response_substring"),
						resultSelectAll.getString("response_charset"), resultSelectAll.getInt("connect_timeout"),
						resultSelectAll.getInt("read_timeout")));

			}
//			for (int i = 0; i < webPages.size(); i++) {
//				if (!webPages.get(i).getStatus().equals("NOT TRACKED")) {
//					// update status manually
//					webPages.get(i).setStatus();
//				}
//			}
			
			for (WebPage webPage : webPages) {
				if (!webPage.getStatus().equals("NOT TRACKED")) {
					// update status manually
					System.out.println("----");
					webPage.setStatus();
					System.out.println(webPage.getUrlName());
					System.out.println(webPage.getUrlAddress());
					System.out.println(webPage.getStatus());
				}
			}

//			for (WebPage webPage : webPages) {
//
//				System.out.println(webPage.getUrlName());
//				System.out.println(webPage.getUrlAddress());
//				System.out.println(webPage.getStatus());
//			}
			System.out.println("Java Version is: " + System.getProperty("java.version"));
			System.out.println(System.getProperty("sun.arch.data.model") + "-bit JVM");

		} catch (ClassNotFoundException e) {

		} catch (SQLException e) {

		}

	}
}
