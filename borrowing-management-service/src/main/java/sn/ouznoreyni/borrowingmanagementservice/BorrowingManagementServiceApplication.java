package sn.ouznoreyni.borrowingmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BorrowingManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BorrowingManagementServiceApplication.class, args);
    }

}
