package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

    @Controller
    @RequestMapping("/web-exchange-rate-list")
    class WebController {


        //http://localhost:8080/web-exchange-rate-list/listAll
        @GetMapping("/listAll")
        public String list(){
            return "main_list";
        }

        //http://localhost:8080/web-exchange-rate-list/listAll/detail
        @GetMapping("/listAll/detail")
        public String detail(@RequestParam(value = "id") String id){
            return "detail_list";
        }
    }

