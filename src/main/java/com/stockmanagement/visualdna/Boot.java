package com.stockmanagement.visualdna;

import com.stockmanagement.visualdna.Intergration.DatabaseSeed;
import com.stockmanagement.visualdna.Intergration.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;

@SpringBootApplication
public class Boot implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Boot.class);
    @Autowired
    private ItemRepository itemRepository;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Boot.class, args);
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
        log.info("************ SEEDING DATABASE ****************");
        itemRepository.save(DatabaseSeed.getItems());
        itemRepository.findAll().forEach(item -> log.info(item.toString()));
        log.info("************ DATABASE READY ****************");
    }
}