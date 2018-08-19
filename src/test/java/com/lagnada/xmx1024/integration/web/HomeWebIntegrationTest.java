package com.lagnada.xmx1024.integration.web;

import com.lagnada.xmx1024.integration.WebIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.lagnada.xmx1024.servlet.CharacterEncodingFilterBuilder.newCharacterEncodingFilterBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebIntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
public class HomeWebIntegrationTest {

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

    @Test
    public void homePage() throws Exception
    {
        mvc.perform(get("/home").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("home"));

    }

}
