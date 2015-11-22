package com.sparkvalley.alexa.base.config;

import com.sparkvalley.alexa.base.services.intf.IStorageService;
import com.sparkvalley.alexa.base.services.storage.FilesystemStorageService;
import org.h2.Driver;
import org.h2.tools.RunScript;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileReader;

@Configuration
@ComponentScan(basePackages = {"com.sparkvalley.alexa"})
@EnableAutoConfiguration
public class ApplicationConfig {

    @Value("${alexa.filesBasePath}") String basePath;

    @Bean
    public DataSource dataSource() {
        return new SimpleDriverDataSource(
                Driver.load(),
//                "jdbc:h2:mem:;MODE=PostgreSQL"
                "jdbc:h2:file:"+basePath+"/alexa.db;MODE=PostgreSQL"
        );
    }

    @Bean
    public IStorageService filesystemStorage() {
        return new FilesystemStorageService(new File(basePath, "files"));
    }

    //@Bean
    public Void initDatabase(DataSource dataSource) throws Throwable {
        RunScript.execute(dataSource.getConnection(), new FileReader("schema.sql"));
        return null;
    }
}
