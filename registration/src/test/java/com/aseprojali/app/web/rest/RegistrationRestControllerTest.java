package com.aseprojali.app.web.rest;

import com.aseprojali.app.config.ApiConstant;
import com.aseprojali.app.domain.Registration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class RegistrationRestControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void whenPostRegistration_thenReturnCreated() throws Exception {
        MvcResult result = mvc.perform(
                post(ApiConstant.ROOT + ApiConstant.V1 + ApiConstant.REGISTRATION.ROOT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"date\": 30,\n" +
                                "  \"email\": \"aseprojali@gmail.com\",\n" +
                                "  \"firstname\": \"Asep\",\n" +
                                "  \"gender\": \"Male\",\n" +
                                "  \"lastname\": \"Rojali\",\n" +
                                "  \"month\": 1,\n" +
                                "  \"phone\": \"085294316666\",\n" +
                                "  \"year\": 2020\n" +
                                "}"))
                .andDo(print())
                .andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(201, status);
        String contentAsString = result.getResponse().getContentAsString();
        Registration registration = super.mapFromJson(contentAsString, Registration.class);
        assertEquals("aseprojali@gmail.com", registration.getEmail());

    }

    @Test
    public void whenPostRegistrationInvalidPhone_thenReturnBadRequest() throws Exception {
        mvc.perform(
                post(ApiConstant.ROOT + ApiConstant.V1 + ApiConstant.REGISTRATION.ROOT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"date\": 30,\n" +
                                "  \"email\": \"aseprojali@gmail.com\",\n" +
                                "  \"firstname\": \"Asep\",\n" +
                                "  \"gender\": \"Male\",\n" +
                                "  \"lastname\": \"Rojali\",\n" +
                                "  \"month\": 1,\n" +
                                "  \"phone\": \"123123\",\n" +
                                "  \"year\": 2020\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON_VALUE));

    }
}