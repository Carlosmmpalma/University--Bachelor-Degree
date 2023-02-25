/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.securingweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
        public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/home").setViewName("home");
                registry.addViewController("/").setViewName("home");
                registry.addViewController("/hello").setViewName("hello");
                registry.addViewController("/login").setViewName("login");
                registry.addViewController("/geral").setViewName("geral");
                registry.addViewController("/admin").setViewName("admin");

        }
}