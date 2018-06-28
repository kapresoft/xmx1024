package com.lagnada.xmx1024.integration;

import com.lagnada.xmx1024.config.ApplicationContextConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = ApplicationContextConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class IntegrationTestCase {
}
