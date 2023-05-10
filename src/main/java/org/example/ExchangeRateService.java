package org.example;


import java.util.List;

public interface ExchangeRateService {
    // methods
    List<ExchangeRate> listAllFromDB();
    List<ExchangeRate> listAllFromExtURL();
}
