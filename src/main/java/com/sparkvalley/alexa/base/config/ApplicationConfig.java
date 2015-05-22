package com.sparkvalley.alexa.base.config;

import com.sparkvalley.alexa.base.dao.FilesDao;
import com.sparkvalley.alexa.base.dao.TagDao;
import com.sparkvalley.alexa.base.dao.intf.IFilesDao;
import com.sparkvalley.alexa.base.dao.intf.ITagDao;
import com.sparkvalley.alexa.base.services.FileService;
import com.sparkvalley.alexa.base.services.intf.IFileService;
import org.h2.Driver;
import org.h2.tools.RunScript;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.io.FileReader;

/**
 * Created by Barak on 4/13/2015.
 */
@Configuration
@ComponentScan(basePackages = {"com.sparkvalley.alexa"})
@PropertySource("classpath:alexa.properties")
public class ApplicationConfig {

    @Value("${alexa.filesBasePath}")
    String basePath;


    @Bean
    public DataSource dataSource() {
        return new SimpleDriverDataSource(
                Driver.load(),
//                "jdbc:h2:mem:;MODE=PostgreSQL"
                "jdbc:h2:file:"+basePath+"/alexa.db;MODE=PostgreSQL"
        );
    }

    @Bean
    public IFilesDao filesDao() {
        return new FilesDao(dataSource());
    }

    @Bean
    public ITagDao tagDao() {
        return new TagDao(dataSource());
    }

    //@Bean
    public Void initDatabase() throws Throwable {
        RunScript.execute(dataSource().getConnection(), new FileReader("schema.sql"));
        return null;
    }
}
