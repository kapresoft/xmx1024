package com.lagnada.xmx1024.integration.controller;

import com.lagnada.xmx1024.integration.WebIntegrationTestCase;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountRepresentationWebIntegrationTest extends WebIntegrationTestCase {

    @Test
    public void accountNotFound() throws Exception
    {
        mvc.perform(get("/api/account/1234").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isNotFound());
        //.andExpect(header().string("X-My-Header", "Value"));
    }

}
