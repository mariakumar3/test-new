package com.mycompany.myapp.cucumber;

import com.mycompany.myapp.TestnewApp;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = TestnewApp.class)
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
