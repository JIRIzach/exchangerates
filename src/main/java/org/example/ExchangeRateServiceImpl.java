package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ExchangeRateServiceImpl implements ExchangeRateService {

    // Fields
    private ExchangeRatesRepository repository;
    private ExternalDataProcessing externalDataProcessing;

    @Autowired
    public ExchangeRateServiceImpl(ExchangeRatesRepository repository, ExternalDataProcessing theExternalDataProcessing){
        this.repository = repository;
        externalDataProcessing = theExternalDataProcessing;
    }


    // Methods

    @Transactional
    public List<ExchangeRate> listAllFromDB() {
        log.info("method in: ExchangeRateService.listAllFromDB()");
        return repository.findAll();
    }

    @Transactional
    public List<ExchangeRate> listAllFromExtURL() {
        log.info("method in: ExchangeRateService.listAllFromExtURL()");
        List<ExchangeRate> listOfExRatesFromExtURL = externalDataProcessing.getExchangeRateListFromExtApi();
        // saving new external data to DB
        repository.deleteAll();
        repository.saveAll(listOfExRatesFromExtURL);

        // returning external data to RestController
        return listOfExRatesFromExtURL;
    }
}