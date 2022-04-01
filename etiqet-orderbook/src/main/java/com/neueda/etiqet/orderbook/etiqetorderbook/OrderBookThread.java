package com.neueda.etiqet.orderbook.etiqetorderbook;

import com.neueda.etiqet.orderbook.etiqetorderbook.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;

public class OrderBookThread implements Runnable{

    private final MainController mainController;

    public OrderBookThread(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                if (this.mainController.isChanged()) {
                    System.out.print("\n\n");
                    Constants.orderBookLooger.info("=================================================================================");
                    Constants.orderBookLooger.info(".....................................BID.........................................");
                    this.mainController.getBuy().stream().sorted(Comparator.comparing(Order::getPrice)).forEach(System.out::println);
                    Constants.orderBookLooger.info(".....................................OFFER.......................................");
                    this.mainController.getSell().stream().sorted(Comparator.comparing(Order::getPrice, Comparator.reverseOrder())).forEach(System.out::println);
                    Constants.orderBookLooger.info("=================================================================================\n\n");
                    this.mainController.setChanged(false);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
