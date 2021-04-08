package com.pwc.denali2.estimator.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.pwc.denali2.estimator.aop")
@EnableAspectJAutoProxy
public class AopConfiguration {

}
