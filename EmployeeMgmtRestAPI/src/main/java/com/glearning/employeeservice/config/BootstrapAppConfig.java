package com.glearning.employeeservice.config;

import com.github.javafaker.Faker;
import com.glearning.employeeservice.model.Dependent;
import com.glearning.employeeservice.model.Employee;
import com.glearning.employeeservice.model.Role;
import com.glearning.employeeservice.model.User;
import com.glearning.employeeservice.repository.EmployeeRepository;
import com.glearning.employeeservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import java.time.ZoneId;
import java.util.HashSet;

@Configuration
@Slf4j
public class BootstrapAppConfig implements ApplicationListener<ApplicationReadyEvent> {
    private final EmployeeRepository employeeRepository;

    private final UserRepository userRepository;

    private final Faker faker = new Faker();

    public BootstrapAppConfig(EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
      log.info("=========Application-initialized==========");
      for (int i = 0; i < 50; i ++){

          Employee employee = Employee
                                .builder()
                                    .dob(faker.date().birthday(26, 56).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                                    .email(faker.internet().emailAddress())
                                    .salary(faker.number().randomDouble(2, 45_000, 80_000))
                                    .name(faker.name().fullName())
                                .build();
          for(int index = 0; index < faker.number().numberBetween(2,4); index ++) {
              Dependent dependent = Dependent.builder().name(faker.name().fullName()). age(faker.number().numberBetween(20, 50)).build();
              employee.addDependent(dependent);
          }
          this.employeeRepository.save(employee);
      }

        User kiran = User.builder().name("kiran").password("welcome").salary(25000).build();
        User vinay = User.builder().name("vinay").password("welcome").salary(35000).build();

        Role user = Role.builder().roleName("USER").build();
        Role admin = Role.builder().roleName("ADMIN").build();



        kiran.addRole(user);
        vinay.addRole(user);
        vinay.addRole(admin);

      /*  this.userRepository.save(kiran);
        this.userRepository.save(vinay);
*/
      log.info("=========Application-initialized==========");
    }

   /* @Bean
    public Faker faker(){
        return new Faker();
    }*/
}
