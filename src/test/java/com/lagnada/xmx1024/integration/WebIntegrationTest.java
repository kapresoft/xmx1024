package com.lagnada.xmx1024.integration;

import com.lagnada.xmx1024.config.ApplicationContextConfig;
import com.lagnada.xmx1024.config.search.TestServletContextConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = TestServletContextConfig.class),
        @ContextConfiguration(classes = ApplicationContextConfig.class)
})
public @interface WebIntegrationTest {
}
