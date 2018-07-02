package com.lagnada.xmx1024.integration;

import com.lagnada.xmx1024.config.search.TestServletContextConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.lagnada.xmx1024.servlet.CharacterEncodingFilterBuilder.newCharacterEncodingFilterBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy(@ContextConfiguration(classes = TestServletContextConfig.class))
public abstract class WebIntegrationTestCase extends IntegrationTestCase {

    protected static final String UTF_8 = "UTF-8";

    @Autowired
    private WebApplicationContext context;

    protected MockMvc mvc;

    @Before
    public void setup()
    {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(newCharacterEncodingFilterBuilder().build())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

}
