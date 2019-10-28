package com.phil.config;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryPanda",
        transactionManagerRef="transactionManagerPanda",
        basePackages= { "com.phil.panda.dao" })
public class RepositoryConfigPanda {
	
	@Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("pandaDataSource")
    private DataSource pandaDataSource;
    
    @Bean(name = "entityManagerPanda")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPanda(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactoryPanda")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPanda (EntityManagerFactoryBuilder builder) {
        return builder
                //设置数据源
                .dataSource(pandaDataSource)
                //设置数据源属性
                .properties(getVendorProperties())
                //设置实体类所在位置.扫描所有带有 @Entity 注解的类
                .packages("com.phil.panda.pojo")
                // Spring会将EntityManagerFactory注入到Repository之中.有了 EntityManagerFactory之后,
                // Repository就能用它来创建 EntityManager 了,然后Entity就可以针对数据库执行操作
                .persistenceUnit("pandaPersistenceUnit")
                .build();

    }

    private Map<String, Object> getVendorProperties() {
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }

    //事务控制
    @Bean(name = "transactionManagerPanda")
    PlatformTransactionManager transactionManagerPanda(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPanda(builder).getObject());
    }
    
    

}
