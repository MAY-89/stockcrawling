package com.stock.demo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.stock.crawling.Crawling;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException {
		Crawling.getStockPriceList();
		SpringApplication.run(DemoApplication.class, args);
	}

}
