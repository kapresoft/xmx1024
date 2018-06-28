package com.lagnada.xmx1024.integration;

import com.lagnada.xmx1024.config.ApplicationContextConfig;
import com.lagnada.xmx1024.config.search.TestServletContextConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * You must manually wire all beans that the controller depends on
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationContextConfig.class, TestServletContextConfig.class})
@WebAppConfiguration
public abstract class StandaloneWebIntegrationTestCase {

    protected MockMvc mvc;

    protected abstract Class<?> getControllerType();

    @Before
    public void setup()
    {
        mvc = MockMvcBuilders.standaloneSetup(newControllerInstance()).build();
    }

    private Object newControllerInstance()
    {
        final Class<?> controllerType = getControllerType();
        try
        {
            return controllerType.newInstance();
        }
        catch (Exception e)
        {
            throw new IllegalStateException("Unable to instantiate controller class: " + controllerType);
        }
    }

}
