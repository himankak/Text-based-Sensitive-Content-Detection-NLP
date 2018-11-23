package mainapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
//@ComponentScan({"java.io.File"})
//@ComponentScan(basePackageClasses = Core_NLP_Example_2.class)
//@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
//@Component("java.io.File")
public class runapp {
    //@Autowired
    public static void main(String[] args){
       //System.out.println("aauak");
       SpringApplication.run(runapp.class, args);
    }
}