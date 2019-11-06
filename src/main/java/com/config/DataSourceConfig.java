package com.config;

import javax.activation.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author YBolshakova
 */
@Configuration
public class DataSourceConfig {
    @Bean
   public DataSource getDataSource(){
       DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();       
       return (DataSource) dataSourceBuilder.build();
   }
    

}
