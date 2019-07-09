package com.proper.phip.core.configs;

import com.proper.phip.core.scheduler.QuartzJobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Quartz任务调度组件配置
 */
@Configuration
public class QuartzSchedulerConfig {

    @Autowired
    private QuartzJobFactory quartzJobFactory;

    /**
     * 配置SchedulerFactoryBean
     * 将一个方法产生为Bean并交给Spring容器管理(@Bean只能用在方法上)
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) throws IOException {
        //Spring提供SchedulerFactoryBean为Scheduler提供配置信息,并被Spring容器管理其生命周期
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        //设置自定义Job Factory，用于Spring管理Job bean
        factory.setJobFactory(quartzJobFactory);
        return factory;
    }
}
