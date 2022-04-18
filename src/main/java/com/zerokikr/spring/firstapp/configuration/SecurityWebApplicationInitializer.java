package com.zerokikr.spring.firstapp.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.stereotype.Component;

@Component  // создание данного унаследованного бина включает безопасность в проекте
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}
