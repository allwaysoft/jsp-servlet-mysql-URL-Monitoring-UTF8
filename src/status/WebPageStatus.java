//中文UTF-8 NO BOM
package status;

import model.WebPage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class WebPageStatus {
	private WebPage webPage;
	private String statusInfo = "";

	private ArrayList<String> critical = new ArrayList<>();
	private ArrayList<String> warning = new ArrayList<>();

	public WebPageStatus(WebPage webPage) {
		this.webPage = webPage;
	}

	public String determineStatus() {

		HttpURLConnection connection;

		try {
			URL url = new URL(webPage.getUrlAddress());
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(webPage.getConnectTimeout());
			connection.setReadTimeout(webPage.getReadTimeout());

			long startTimerConnect = System.currentTimeMillis();
			connection.connect();

			long stopTimerConnect = System.currentTimeMillis();

			int connectTime = (int) ((int) stopTimerConnect - startTimerConnect);
			System.out.println("ConnetionTime: " + connectTime);

			// compare response code
			responseCode(connection);

			// compare response substring search if it's defined
			long startTimerRead = System.currentTimeMillis();
			responseSubstring(connection);
			long stopTimerRead = System.currentTimeMillis();
			int readTime = (int) ((int) stopTimerRead - startTimerRead);
			System.out.println("ReadTime: " + readTime);

			// compare response code range
			// responseRange(connection);

			connection.disconnect();

		} catch (MalformedURLException malformedEx) {
			return "Not valid URL!";
		} catch (IOException e) {
			return "Connection Error!";
		}

		if (critical.size() != 0) {
			statusInfo = statusInfo.concat("CRITICAL: ");
			for (String s : critical) {
				statusInfo = statusInfo.concat(s);
			}
		}

		if (warning.size() != 0) {
			if (!statusInfo.equals(""))
				statusInfo = statusInfo + "\n";
			statusInfo = statusInfo.concat("WARNING: ");
			for (String s : warning) {
				statusInfo = statusInfo.concat(s);
			}
		}

		if (statusInfo.equals(""))
			return "OK";
		else
			return statusInfo;
	}

	private void responseCode(HttpURLConnection connection) throws IOException {
		if (connection.getResponseCode() != webPage.getResponseCode())
			critical.add("another response code (" + connection.getResponseCode() + "); ");
	}

	private void responseSubstring(HttpURLConnection connection) throws IOException {
		BufferedReader in = new BufferedReader(
				new InputStreamReader(connection.getInputStream(), webPage.getResponseCharset()));
		String line;
		boolean hasSubstring = false;

		if (!webPage.getResponseSubstring().equals("")) {
			while ((line = in.readLine()) != null) {
				if (line.contains(webPage.getResponseSubstring()))
					hasSubstring = true;
			}
		} else
			hasSubstring = true;

		if (!hasSubstring)
			critical.add("required substring is missing; ");
		in.close();
	}

}
