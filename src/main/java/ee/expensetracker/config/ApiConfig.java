package ee.expensetracker.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import(DbConfig.class)
@ComponentScan(basePackages = {"ee.expensetracker.controller"})
public class ApiConfig {

}
