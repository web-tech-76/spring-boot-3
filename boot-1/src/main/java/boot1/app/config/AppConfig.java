package boot1.app.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
@Slf4j
@PropertySource("classpath:application.properties")
@ComponentScan
@EnableTransactionManagement
class AppConfig {

    /*static class MyPropertySource extends org.springframework.core.env.PropertySource<String> {

        public MyPropertySource() {
            super("someProperty");
        }

        @Override
        public Object getProperty(String name) {
            if(name.startsWith("spring.datasource")){
                return "";
            }
            return null;
        }
    }*/


    @Bean
    DataSource dataSource(Environment environment) {
        var ds = new DriverManagerDataSource(
                environment.getProperty("spring.datasource.url"),
                environment.getProperty("spring.datasource.username"),
                environment.getProperty("spring.datasource.password")
        );
        ds.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        return ds;

    }

    @Bean
    JdbcTemplate jdbcTemplate(DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean
    DataSourceTransactionManager transactionManager(DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }

    @Bean
    TransactionTemplate transactionTemplate(PlatformTransactionManager ptm) {
        return new TransactionTemplate(ptm);
    }


}







/*


    // post bean creation processing ......
        @Bean
    TransactionalBeanPostProcessor transactionalBeanPostProcessor(BeanFactory bf) {
        return new TransactionalBeanPostProcessor(bf);
    }

    static class TransactionalBeanPostProcessor implements BeanPostProcessor {

        private final BeanFactory bf;

        TransactionalBeanPostProcessor(BeanFactory bf) {
            this.bf = bf;

        }


        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

            log.info("post processing bean {} and {} ", bean, beanName);

            if (bean instanceof DefaultCustomerService dsc) {
                return transactionalCustomerService(
                        this.bf.getBean(TransactionTemplate.class),
                        dsc);
            }
            return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
        }
    }




    //

    // register bean definition so that it can be scanned/ fetched  by spring and can be loaded ....
    // using BeanDefinitionRegistryPostProcessor , provide Class DefaultCustomerService
    // constructor arguments
    // can be further achieved by @Component, @Service etc.. annotations...

    @Bean
    static MyBeanDefinitionRegistryPostProcessor myBeanDefinitionRegistryPostProcessor() {
        return new MyBeanDefinitionRegistryPostProcessor();
    }


    static class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

        @Override
        public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
            BeanDefinition customerServiceBeanDef = BeanDefinitionBuilder
                    .genericBeanDefinition(DefaultCustomerService.class)
//                            .addConstructorArgReference("transactionTemplate")
                    .addConstructorArgReference("jdbcTemplate")
                    .getBeanDefinition();

            registry.registerBeanDefinition("dcs", customerServiceBeanDef);

        }

        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
            BeanDefinitionRegistryPostProcessor.super.postProcessBeanFactory(beanFactory);
        }
    }



    // Transactional support can be achieved by another alternative to Jdk Proxy service ...
    // use ProxyFactoryBean and register delegate class and invoke methods through execute...


    @Bean
    DefaultCustomerService defaultCustomerService(TransactionTemplate tt, JdbcTemplate jt) {
        return transactionalCustomerService(tt, new DefaultCustomerService(jt));
    }

    private static DefaultCustomerService transactionalCustomerService(
            TransactionTemplate tt, DefaultCustomerService dcs) {

        var proxyBean = new ProxyFactoryBean();
        proxyBean.setTarget(dcs);
        proxyBean.setProxyTargetClass(true);
        proxyBean.addAdvice((MethodInterceptor) invocation -> {
            var method = invocation.getMethod();
            var args = invocation.getArguments();
            return tt.execute(status -> {
                try {
                    return method.invoke(dcs, args);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        });

        return (DefaultCustomerService) proxyBean.getObject();
    }



    /// Transactional support can be achieved by inheritance directly....

    @Bean
    TransactionalCustomerService transactionalCustomerService(JdbcTemplate jt, TransactionTemplate tt) {
        return new TransactionalCustomerService(jt, tt);
    }



*/


