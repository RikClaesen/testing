package com.example.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.example.exception.RestExceptionHandler;
import com.example.repository.TestRepository;


@ContextConfiguration(classes = MockServletContext.class) // Tells Spring to set up an empty WebApplicationContext
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloControllerTest {
	@InjectMocks
	HelloController helloController;
	
	@Mock
	private TestRepository repository;

	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		//here we need to setup a dummy application context that only registers the RestExceptionHandler
		final StaticApplicationContext applicationContext = new StaticApplicationContext();
		applicationContext.registerBeanDefinition("exceptionController", new RootBeanDefinition(RestExceptionHandler.class));
		
		final ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver();
		//exceptionResolver.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		exceptionResolver.getMessageConverters().add(new Jaxb2RootElementHttpMessageConverter());
		//set the application context of the resolver to the dummy application context we just created
		exceptionResolver.setApplicationContext(applicationContext);
		//needed in order to force the exception resolver to update it's internal caches
		exceptionResolver.afterPropertiesSet();
	
		mockMvc = standaloneSetup(helloController).setHandlerExceptionResolvers(exceptionResolver).build();
//		mockMvc = standaloneSetup(helloController).build();
						
	}	
	
	@Test
	public void greetingWithparamJson() throws Exception{
		when(repository.getAgentId()).thenReturn("001");
		
		
        
		mockMvc.perform(get("/greeting?name=Rik")
							.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json("{id:1,content:Hello Rik001!}") );
						
	}
	
	@Test
	public void greetingWithPathVariableJsonException() throws Exception{
		Mockito.when(repository.getAgentId()).thenReturn("001");
		mockMvc.perform(get("/greeting/Mark")
							.accept(MediaType.APPLICATION_XML))
				.andExpect(status().isNotFound());
						
	}

}
