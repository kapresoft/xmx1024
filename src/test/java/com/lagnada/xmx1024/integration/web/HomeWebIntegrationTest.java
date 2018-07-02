package com.lagnada.xmx1024.integration.web;

import com.lagnada.xmx1024.integration.WebIntegrationTestCase;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class HomeWebIntegrationTest extends WebIntegrationTestCase {

    @Test
    public void homePage() throws Exception
    {
        mvc.perform(get("/home").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("home"));

    }

}
