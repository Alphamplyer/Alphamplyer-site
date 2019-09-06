package com.alphamplyer.website.shop.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

/**
 * Configure spring to use local messages
 */
@Configuration
public class InternationalizationConfiguration implements WebMvcConfigurer {

    @Bean(name = "localeResolver")
    public LocaleResolver getLocaleResolver() {
        final CookieLocaleResolver ret = new CookieLocaleResolver();
        ret.setDefaultLocale(new Locale("fr"));
        return ret;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
