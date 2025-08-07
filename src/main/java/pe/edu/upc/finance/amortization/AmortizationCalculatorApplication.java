package pe.edu.upc.finance.amortization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AmortizationCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmortizationCalculatorApplication.class, args);
	}

}
