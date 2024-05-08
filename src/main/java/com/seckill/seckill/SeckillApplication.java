package com.seckill.seckill;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeckillApplication {

	public static void main(String[] args) {
		try {
            Process process = Runtime.getRuntime().exec("middleware_start.bat");
            int exitCode = process.waitFor();
            System.out.println("Batch script execution finished with exit code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        SpringApplication.run(SeckillApplication.class, args);
		
	}

}
