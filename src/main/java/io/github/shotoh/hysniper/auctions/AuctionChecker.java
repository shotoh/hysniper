package io.github.shotoh.hysniper.auctions;

import com.google.common.reflect.TypeToken;
import io.github.shotoh.hysniper.HySniper;
import io.github.shotoh.hysniper.prices.PriceChecker;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class AuctionChecker {
    public static void checkAuctions() {
        Map<String, Double> map = getLowestBins();
        PriceChecker.AUCTION_PRICES.putAll(map);
    }

    public static Map<String, Double> getLowestBins() {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet httpGet = new HttpGet("https://moulberry.codes/lowestbin.json");
            httpGet.addHeader("content-type", "application/json; charset=UTF-8");
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            return HySniper.GSON.fromJson(new InputStreamReader(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8),
                    new TypeToken<Map<String, Double>>(){}.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}