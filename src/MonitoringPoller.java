
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

			long startTimerPoller = System.currentTimeMillis();

//begin多线程并行不等待子线程
//			for (WebPage webPage : webPages) {
//				if (!webPage.getStatus().equals("NOT TRACKED")) {
//					new Thread(() -> {
//					    // Insert some method call here.
//						System.out.println("----");
//						webPage.setStatus();
//						System.out.println(webPage.getUrlName());
//						System.out.println(webPage.getUrlAddress());
//						System.out.println(webPage.getStatus());
//					}).start();
//					// update status manually
//
//				}
//			}
//end多线程并行不等待子线程

////begin多线程并行等待子线程
//
//			System.out.println("main start");
//
//			ThreadGroup tg = new ThreadGroup("Parent ThreadGroup");
//
//			for (WebPage webPage : webPages) {
//				if (!webPage.getStatus().equals("NOT TRACKED")) {
//					new Thread(tg, () -> {
//						// Insert some method call here.
//						System.out.println("----");
//						webPage.setStatus();
//						System.out.println(webPage.getUrlName());
//						System.out.println(webPage.getUrlAddress());
//						System.out.println(webPage.getStatus());
//					}).start();
//					// update status manually
//
//				}
//			}
//
//			while (tg.activeCount() > 0) {
//				try {
//					System.out.println("Waiting for " + tg.activeCount() + " CThreads to Complete");
//					Thread.sleep(1000); // Main Thread or someThradObject.sleep();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			System.out.println("main end");
////end多线程并行等待子线程	

////begin多线程并行等待子线程循环变量
//
//			System.out.println("main start");
//
//			ThreadGroup tg = new ThreadGroup("Parent ThreadGroup");
//
//			for (int i = 0; i < webPages.size(); i++) {
//				if (!webPages.get(i).getStatus().equals("NOT TRACKED")) {
//					int t = i;
//					new Thread(tg, () -> {
//
//						// Insert some method call here.
//						System.out.println("----");
//						webPages.get(t).setStatus();
//						System.out.println(webPages.get(t).getUrlName());
//						System.out.println(webPages.get(t).getUrlAddress());
//						System.out.println(webPages.get(t).getStatus());
//					}).start();
//					// update status manually
//
//				}
//			}
//
//			while (tg.activeCount() > 0) {
//				try {
//					System.out.println("Waiting for " + tg.activeCount() + " CThreads to Complete");
//					Thread.sleep(1000); // Main Thread or someThradObject.sleep();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			System.out.println("main end");
////end多线程并行等待子线程		

			// begin多线程并行等待子线程循环变量

			System.out.println("main start");

			ThreadGroup tg = new ThreadGroup("Parent ThreadGroup");

			for (int i = 0; i < webPages.size(); i++) {
				if (!webPages.get(i).getStatus().equals("NOT TRACKED")) {
					int t = i;
					new Thread(tg, "" + t) {

						public void run() {

							// Insert some method call here.
							System.out.println("----");
							webPages.get(t).setStatus();
							System.out.println(webPages.get(t).getUrlName());
							System.out.println(webPages.get(t).getUrlAddress());
							System.out.println(webPages.get(t).getStatus());

						}

					}.start();
					// update status manually

				}
			}

			while (tg.activeCount() > 0) {
				try {
					System.out.println("Waiting for " + tg.activeCount() + " CThreads to Complete");
					Thread.sleep(1000); // Main Thread or someThradObject.sleep();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("main end");
//end多线程并行等待子线程			

//begin单线程		
//			for (int i = 0; i < webPages.size(); i++) {
//				if (!webPages.get(i).getStatus().equals("NOT TRACKED")) {
//					// update status manually
//					System.out.println("----");
//					webPages.get(i).setStatus();
//					System.out.println(webPages.get(i).getUrlName());
//					System.out.println(webPages.get(i).getUrlAddress());
//					System.out.println(webPages.get(i).getStatus());
//				}
//			}
//end单线程				

			long stopTimerPoller = System.currentTimeMillis();

			int pollerTime = (int) ((int) stopTimerPoller - startTimerPoller);
			System.out.println("PollerTime: " + pollerTime);

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
