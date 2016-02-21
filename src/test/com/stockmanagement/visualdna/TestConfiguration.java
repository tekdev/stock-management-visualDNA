package com.stockmanagement.visualdna;

import com.stockmanagement.visualdna.Intergration.ItemRepository;
import com.stockmanagement.visualdna.domain.Item;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by modev on 21/02/2016.
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = ItemRepository.class)
@EntityScan(basePackageClasses = Item.class)
@Import({ DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class TestConfiguration {
}
