import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Deezer {

    public String getBodyRequest(int index) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://api.deezer.com/playlist/7994077022/tracks?index=" + index);
        try {
            HttpResponse response = httpClient.execute(request);
            InputStream is = response.getEntity().getContent();
            StringBuilder body = new StringBuilder();
            while(is.available() == 1) {
                body.append((char) is.read());
            }
            return new String(body.toString().getBytes(StandardCharsets.UTF_8));
        } catch(IOException e) {
            e.printStackTrace();
        }
        return "Error";
    }
}
