/*
 * Copyright (c) 2016-2026. DesignThoughts Axon Sample
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lubanresearch.lubanmall.orderservice.infrastructure.config;

import com.lubanresearch.lubanmall.orderservice.domain.Deal;
import com.lubanresearch.lubanmall.orderservice.domain.Order;
import com.lubanresearch.lubanmall.orderservice.domain.OrderItem;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandlerFactoryBean;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.CommandGatewayFactoryBean;
import org.axonframework.commandhandling.interceptors.BeanValidationInterceptor;
import org.axonframework.common.jpa.ContainerManagedEntityManagerProvider;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;
import org.axonframework.repository.GenericJpaRepository;
import org.axonframework.repository.Repository;
import org.axonframework.unitofwork.SpringTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Arrays;

@Configuration
public class AxonConfig {

	@Autowired
	private PlatformTransactionManager platformTransactionManager;
	@Bean
	public AnnotationEventListenerBeanPostProcessor annotationEventListenerBeanPostProcessor() {
		AnnotationEventListenerBeanPostProcessor processor = new AnnotationEventListenerBeanPostProcessor();
		processor.setEventBus(eventBus());
		return processor;
	}
	
	@Bean
	public AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor() {
		AnnotationCommandHandlerBeanPostProcessor processor = new AnnotationCommandHandlerBeanPostProcessor();
		processor.setCommandBus(commandBus());
		return processor;
	}

	@Bean
	public CommandBus commandBus() {
		SimpleCommandBus commandBus = new SimpleCommandBus();
		commandBus.setHandlerInterceptors(Arrays.asList(new BeanValidationInterceptor()));
		commandBus.setTransactionManager(new SpringTransactionManager(platformTransactionManager));
		return commandBus;
	}

	@Bean
	public EventBus eventBus() {
		return new SimpleEventBus();
	}
	
	@Bean
	public CommandGatewayFactoryBean<CommandGateway> commandGatewayFactoryBean() {
		CommandGatewayFactoryBean<CommandGateway> factory = new CommandGatewayFactoryBean<CommandGateway>();
		factory.setCommandBus(commandBus());
		return factory;
	}


	@Bean
	public EntityManagerProvider entityManagerProvider() {
		EntityManagerProvider entityManagerProvider =  new ContainerManagedEntityManagerProvider();
		return entityManagerProvider;
	}
	@Bean
	public Repository<Deal> dealRepository(){
		GenericJpaRepository<Deal> repository =  new GenericJpaRepository<Deal>(entityManagerProvider(),Deal.class);
		return repository;
	}


	@Bean
	public Repository<Order> orderRepository(){
		GenericJpaRepository<Order> repository =  new GenericJpaRepository<Order>(entityManagerProvider(),Order.class);
		return repository;
	}


	@Bean
	public Repository<OrderItem> orderItemRepository(){
		GenericJpaRepository<OrderItem> repository =  new GenericJpaRepository<OrderItem>(entityManagerProvider(),OrderItem.class);
		return repository;
	}

	@Bean
	public EventStore eventStore(){

		EventStore eventStore = new FileSystemEventStore(new SimpleEventFileResolver(new File("/Users/hilbertcao/event")));
		return  eventStore;
	}


/*	@Bean
	public AggregateAnnotationCommandHandler taskCommandHandler() {
		return AggregateAnnotationCommandHandler.subscribe(Deal.class, dealRepository(), commandBus());
	}*/

	@Bean
	public AggregateAnnotationCommandHandlerFactoryBean aggregateAnnotationCommandHandlerFactoryBean(ApplicationContext applicationContext){
		AggregateAnnotationCommandHandlerFactoryBean factoryBean = new AggregateAnnotationCommandHandlerFactoryBean();
		factoryBean.setAggregateType(Deal.class);
		factoryBean.setApplicationContext(applicationContext);
		factoryBean.setCommandBus(commandBus());
		factoryBean.setRepository(dealRepository());
		try {
			factoryBean.afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return factoryBean;
	}
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}