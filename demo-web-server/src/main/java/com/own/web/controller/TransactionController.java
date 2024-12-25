package com.own.web.controller;

import com.own.web.service.impl.TransactionServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuChang
 * @date 2023/3/29 17:07
 * @describe
 */

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionServiceImpl transactionService;

    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping("/t1")
    public void t1() {
        transactionService.t1();
    }

    @RequestMapping("/t2")
    public void t2() {
        transactionService.t2();
    }

    @RequestMapping("/t3")
    public void t3() {
        transactionService.transactionTest();
    }


    @RequestMapping("/select")
    public String select() {
       return transactionService.select();
    }


    @RequestMapping("/select2")
    public String select2() {
       return transactionService.select2();
    }


    @RequestMapping("/insert")
    public void insert() throws InterruptedException {
        transactionService.insert();
    }



}
