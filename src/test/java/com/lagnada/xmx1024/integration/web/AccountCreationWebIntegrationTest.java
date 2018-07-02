package com.lagnada.xmx1024.integration.web;

import com.lagnada.xmx1024.integration.WebIntegrationTestCase;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountCreationWebIntegrationTest extends WebIntegrationTestCase {

    public static final String EXPECTED_BAD_REQUEST_MSG = "{\"errors\":[{\"code\":\"NotEmpty\",\"field\":\"email\",\"message\":\"Email is invalid\"}],\"count\":1}";

    @Test
    public void createAccount_WhenContentIsInvalid_ShouldReturn_400_BadRequest() throws Exception
    {
        mvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(EXPECTED_BAD_REQUEST_MSG))
                .andExpect(content().encoding(UTF_8))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

}
