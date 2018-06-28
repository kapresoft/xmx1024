package com.lagnada.xmx1024.integration;

import com.lagnada.xmx1024.config.ApplicationContextConfig;
import com.lagnada.xmx1024.config.search.TestServletContextConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationContextConfig.class, TestServletContextConfig.class})
@WebAppConfiguration
public abstract class WebIntegrationTestCase {

    @Autowired
    private WebApplicationContext context;

    protected MockMvc mvc;

    @Before
    public void setup()
    {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

}
