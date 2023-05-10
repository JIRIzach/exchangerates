package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin()
class ExRatesRestAPIController {
    private ExchangeRateService exchangeRateService;

    @Autowired
    public ExRatesRestAPIController(ExchangeRateService exRaDAO){
        exchangeRateService = exRaDAO;
    }

    @GetMapping("/list")
    public List<ExchangeRate> list(@RequestParam(value = "usedb", defaultValue = "true") String usedb){
        log.info("method in: ExRatesRestAPIController.list() GET http://localhost:8080/api/list?" + usedb);
        List<ExchangeRate> listOfEntities;
        if(usedb.equals("false")){
            log.info("method in: ExRatesRestAPIController.list() getting data from external URL");
            listOfEntities = exchangeRateService.listAllFromExtURL();
            log.info("method in: ExRatesRestAPIController.list() data from external URL: " + listOfEntities);
            return listOfEntities;
        } else {
            log.info("method in: ExRatesRestAPIController.list() getting data from DB");
            listOfEntities = exchangeRateService.listAllFromDB();
            log.info("method in: ExRatesRestAPIController.list() data from DB: " + listOfEntities);
            var x =new ExchangeRate();
            x.setId(1);
            x.setName("MyName");
            listOfEntities.add(x);
            return listOfEntities;
        }

    }
}