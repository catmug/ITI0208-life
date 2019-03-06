package ee.expensetracker.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import(DbConfig.class)
@ComponentScan(basePackages = {"ee.expensetracker.controller"})
public class ApiConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
