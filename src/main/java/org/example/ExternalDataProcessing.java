package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
@Slf4j
public class ExternalDataProcessing {

    // Field
    private static HttpURLConnection connection;
    String urlExternalDataSource = "https://webapi.developers.erstegroup.com/api/csas/public/sandbox/v2/rates/exchangerates?web-api-key=c52a0682-4806-4903-828f-6cc66508329e";

    public List<ExchangeRate> getExchangeRateListFromExtApi(){
        log.info("method in: ExternalDataProcessing.getExchangeRateListFromExtApi()");
        String jsonString = getJsonStringFromExtAPI(urlExternalDataSource);
        log.debug("method in: ExternalDataProcessing.getExchangeRateListFromExtApi() String data form URL " + jsonString);
        return getListOfExchangeRatesFromJsonString(jsonString);
    }

    private List<ExchangeRate> getListOfExchangeRatesFromJsonString(String jsonString){
        log.info("method in: ExternalDataProcessing.getListOfExchangeRatesFromJsonString()");
        log.debug("method in: ExternalDataProcessing.getListOfExchangeRatesFromJsonString() data in:" + jsonString);
        List<ExchangeRate> exRateListFromExtURL = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            exRateListFromExtURL = mapper.readValue(jsonString, new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            log.debug("method in: ExternalDataProcessing.getListOfExchangeRatesFromJsonString() data out:" + exRateListFromExtURL);
            return exRateListFromExtURL;
        }
    }

    private String getJsonStringFromExtAPI(String extUrl){
        log.info("method in: ExternalDataProcessing.getJsonStringFromExtAPI()");
        log.debug("method in: ExternalDataProcessing.getJsonStringFromExtAPI() data in:" + extUrl);
        BufferedReader reader;
        String line;
        String finalJsonString;
        StringBuffer resContent = new StringBuffer();

        try {
            // re-form String to URL object
            URL url = new URL(extUrl);

            // open connection to URL
            connection = (HttpURLConnection) url.openConnection();

            //REQ setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // save stats code from response
            int status = connection.getResponseCode();


            if(status>299){ //status code - error
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null){
                    resContent.append(line);
                }
                reader.close();
            } else { // status code - ok
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null){
                    resContent.append(line);
                }
                reader.close();
            }

        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            connection.disconnect();
            finalJsonString = resContent.toString();
        }
        log.debug("method in: ExternalDataProcessing.getJsonStringFromExtAPI() data out:" + finalJsonString);
        return finalJsonString;
    }
}