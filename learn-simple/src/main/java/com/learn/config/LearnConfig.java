package com.learn.config;

import com.learn.test.InitDesotryMethodTest;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.annotation.*;

@Configuration
//@ComponentScan(basePackages = {"com.learn"})
//用于导入xml的bean
@ImportResource("${a}")
@EnableAspectJAutoProxy
//用于导入配置
@PropertySource("classpath:application.properties")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
@Import({MyImportBeanDefinitionRegistrar.class,Post4.class})
//这个注解的功能由Conditional实现，在要解析配置类之前都会判断Conditional
//需要在命令行中设置spring.profiles.active，或者SpringApplicationBuilder中启动
//@Profile("")
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
