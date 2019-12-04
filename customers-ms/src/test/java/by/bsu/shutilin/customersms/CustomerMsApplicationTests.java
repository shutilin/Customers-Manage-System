package by.bsu.shutilin.customersms;

import by.bsu.shutilin.customersms.controller.CustomerTypeController;
import by.bsu.shutilin.customersms.model.CustomerTypes;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerMsApplicationTests {
    @MockBean
    CustomerTypeController typeController;

    @Autowired
    WebApplicationContext webApplicationContext;

    public MockMvc mvc;

    @Autowired
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    private <T> T mapFromJson(String json, Class<T> classWrapper)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, classWrapper);
    }

    @Test
    public void createType() throws Exception {
        CustomerTypes type = new CustomerTypes();
        type.setCaption("test");

        when(typeController.createType(any(CustomerTypes.class))).thenReturn(type);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/types")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(this.mapToJson(type))).andReturn();
        int status = mvcResult.getResponse().getStatus();
        CustomerTypes result = this.mapFromJson(mvcResult.getResponse().getContentAsString(), CustomerTypes.class);

        assertTrue(status == 200);
        assertEquals(result.getCaption(), "test");
    }

}
