package init.controller;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FinanceClient {
    
    private final Dotenv dotenv = Dotenv.configure().directory("src\\main\\resources\\.env").load();
    private final String apiKey = dotenv.get("API_KEY");
    private final String baseUrl = dotenv.get("URL_API");

    public double getCdiTax() {
        OkHttpClient client = new OkHttpClient();

        String url = baseUrl + "?key=" + apiKey;

        Request request = new Request.Builder()
            .url(url)
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
                JsonObject results = jsonObject.getAsJsonObject("results");
                JsonArray taxesArray = results.getAsJsonArray("taxes");
                JsonObject taxes = taxesArray.get(0).getAsJsonObject();
                return taxes.get("cdi").getAsDouble();
            } else {
                return 0;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
