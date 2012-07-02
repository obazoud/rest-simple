package org.examples.application;

import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.Clock;
import com.yammer.metrics.core.MetricPredicate;
import com.yammer.metrics.graphite.GraphiteReporter;
import com.yammer.metrics.reporting.ConsoleReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.concurrent.TimeUnit;

/**
 * @author obazoud
 */
@Configuration
@ImportResource({"WEB-INF/beans.xml"})
@ComponentScan(basePackages = "org.examples.rest")
public class ApplicationConfig {
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationConfig.class);

    @Bean
    public GraphiteReporter getGraphiteReporter() throws Exception {
        try {
            LOG.info("Creating Graphite reporter");
            GraphiteReporter reporter = new GraphiteReporter(Metrics.defaultRegistry(),
                                                             null,
                                                             MetricPredicate.ALL,
                                                             new GraphiteReporter.DefaultSocketProvider("127.0.0.1", 2003),
                                                             Clock.defaultClock());
            reporter.start(5, TimeUnit.SECONDS);
            return reporter;
        } catch (Exception e) {
            LOG.error("Error creating/starting Graphite reporter:", e);
            throw e;
        }
    }
}
