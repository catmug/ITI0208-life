package ee.expensetracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.properties")
@ComponentScan(basePackages = {"ee.expensetracker.dao"})
public class DbConfig {

    @Autowired
    public Environment env;

    @Bean
    public DataSource dataSource() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.hsqldb.jdbcDriver");
        ds.setUrl(env.getProperty("db.url"));
        DatabasePopulatorUtils.execute(databasePopulator(), ds);

        return ds;
    }

    private ResourceDatabasePopulator databasePopulator() {
        return new ResourceDatabasePopulator(
                new ClassPathResource("schema.sql"),
                new ClassPathResource("data.sql"));
    }

    @Bean
    public JdbcTemplate getTemplate() {
        return new JdbcTemplate(dataSource());
    }

}