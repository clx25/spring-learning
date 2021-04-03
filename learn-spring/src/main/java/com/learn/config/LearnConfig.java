package com.learn.config;

import com.learn.test.InitDesotryMethodTest;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"com.learn"})
//@ImportResource("classpath:a.properties")
@EnableAspectJAutoProxy
//@PropertySource("classpath:a.properties")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
@Import({MyImportBeanDefinitionRegistrar.class,Post4.class})
public class LearnConfig {

	@Bean
	public Post2 post2() {
		return new Post2();
	}

	//这个destroyMethod参数是默认值表示在返回对象的类中自动迅找close或shutdown方法作为销毁方法
	@Bean(initMethod = "initMethod", destroyMethod = AbstractBeanDefinition.INFER_METHOD)
	public InitDesotryMethodTest initDesotryMethodTest(){
		return new InitDesotryMethodTest();
	}
}
