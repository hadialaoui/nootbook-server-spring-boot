package com.hadialaoui.notebookserverspringboot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hadialaoui.notebookserverspringboot.constants.InterpreterConstants;
import com.hadialaoui.notebookserverspringboot.exceptions.ReturnCode;
import com.hadialaoui.notebookserverspringboot.models.UserRequest;
import com.hadialaoui.notebookserverspringboot.services.NotebookServerService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
 public class NotebookServerSpringBootApplicationTests {

	
	@Autowired
	private ObjectMapper objectmapper;
	@Autowired
    private WebApplicationContext webApplicationContext;
	@Autowired
	private NotebookServerService notebookServerService;
	
	private MockMvc mockMvc;
	
    @Before
    public void setup () {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.webApplicationContext);
        this.mockMvc = builder.build();
    }
    
    @Test
   	public void interpreterPythonTest() throws Exception {
   		 UserRequest request = new UserRequest();
   		 request.setCode("%python print 3+3");
   		 MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(InterpreterConstants.ResourcePaths.BASE_PATH)
   				 .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectmapper.writeValueAsString(request)))
   	                .andReturn();
   		 int status = result.getResponse().getStatus();
   			assertEquals(200, status);
   			assertEquals("{\"result\":\"6\\r\\n\"}", result.getResponse().getContentAsString());
   	}
    
    @Test
   	public void preservingStateInterpreterPythonWithoutSessionTest() throws Exception {
   		 boolean sessionIsEnable = notebookServerService.sessionModeIsEnable();
   		assumeTrue(!sessionIsEnable);
		UserRequest request = new UserRequest();
   		request.setCode("%python a=5");
   		 MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(InterpreterConstants.ResourcePaths.BASE_PATH)
   				 .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectmapper.writeValueAsString(request)))
   	                .andReturn();
   		 int status = result.getResponse().getStatus();
   			assertEquals(200, status);
   			assertEquals("{\"result\":\"\"}", result.getResponse().getContentAsString());
   			
		request.setCode("%python print a+3");
  		  result = this.mockMvc.perform(MockMvcRequestBuilders.post(InterpreterConstants.ResourcePaths.BASE_PATH)
  				 .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectmapper.writeValueAsString(request)))
  	                .andReturn();
  		  status = result.getResponse().getStatus();
  			assertEquals(200, status);
  			assertEquals("{\"result\":\"8\\r\\n\"}", result.getResponse().getContentAsString());
   	}
    
    @Test
   	public void interpreterPythonWithSessionTest() throws Exception {
   		 boolean sessionIsEnable = notebookServerService.sessionModeIsEnable();
   		assumeTrue(sessionIsEnable);
		UserRequest request = new UserRequest();
   		request.setCode("%python a=5");
   		request.setSessionId("4444");
   		 MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(InterpreterConstants.ResourcePaths.BASE_PATH)
   				 .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectmapper.writeValueAsString(request)))
   	                .andReturn();
   		 int status = result.getResponse().getStatus();
   			assertEquals(200, status);
   			assertEquals("{\"result\":\"\"}", result.getResponse().getContentAsString());
   		
   		//test with other id session 
		request.setCode("%python print a+3");
		request.setSessionId("5555");
  		  result = this.mockMvc.perform(MockMvcRequestBuilders.post(InterpreterConstants.ResourcePaths.BASE_PATH)
  				 .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectmapper.writeValueAsString(request)))
  	                .andReturn();
  		  status = result.getResponse().getStatus();
  			assertEquals(400, status);
  			assertEquals(ReturnCode.ERROR_PYTHON_PROCESS.getMessage(), result.getResolvedException().getMessage());
  		
  		//test the same id session
  			request.setCode("%python print a+3");
  			request.setSessionId("4444");
		 result = this.mockMvc.perform(MockMvcRequestBuilders.post(InterpreterConstants.ResourcePaths.BASE_PATH)
  				 .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectmapper.writeValueAsString(request)))
  	                .andReturn();
  		  status = result.getResponse().getStatus();
  			assertEquals(200, status);
  			assertEquals("{\"result\":\"8\\r\\n\"}", result.getResponse().getContentAsString());
  			
  		
   	}
    @Test
	public void errorParsingCodeTest() throws Exception {
		 UserRequest request = new UserRequest();
		 request.setCode("python print 3+3");
		 MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(InterpreterConstants.ResourcePaths.BASE_PATH)
				 .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectmapper.writeValueAsString(request)))
	                .andReturn();
		 int status = result.getResponse().getStatus();
			assertEquals(400, status);
			assertEquals(ReturnCode.ERROR_PARSING_CODE.getMessage(), result.getResolvedException().getMessage());
	}
    
    @Test
   	public void unknownInterpreterTest() throws Exception {
   		 UserRequest request = new UserRequest();
   		 request.setCode("%kotlin print 3+3");
   		 MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(InterpreterConstants.ResourcePaths.BASE_PATH)
   				 .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectmapper.writeValueAsString(request)))
   	                .andReturn();
   		 int status = result.getResponse().getStatus();
   			assertEquals(400, status);
   			assertEquals(ReturnCode.UNKNOWN_INTERPRETER.getMessage(), result.getResolvedException().getMessage());
   	}
    
    @Test
   	public void errorPythonProcessTest() throws Exception {
   		 UserRequest request = new UserRequest();
   		 request.setCode("%python print x+3");
   		 MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(InterpreterConstants.ResourcePaths.BASE_PATH)
   				 .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectmapper.writeValueAsString(request)))
   	                .andReturn();
   		 int status = result.getResponse().getStatus();
   			assertEquals(400, status);
   			assertEquals(ReturnCode.ERROR_PYTHON_PROCESS.getMessage(), result.getResolvedException().getMessage());
   	}
    
   

}
