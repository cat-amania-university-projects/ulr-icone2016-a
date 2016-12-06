package fr.univlr.incone2016.config;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoTypeMapper;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;

import fr.univlr.incone2016.Application;

@Configuration
@Lazy
@EnableMongoRepositories(basePackageClasses = Application.class)
class MongoConfig {

	@Bean
	public MongoDbFactory mongoDbFactory() throws UnknownHostException {
		return new SimpleMongoDbFactory(new Mongo(), "cat-project");
	}

	@Bean
	public MongoTemplate mongoTemplate() throws UnknownHostException {
		MongoTemplate template = new MongoTemplate(mongoDbFactory(), mongoConverter());
		return template;
	}

	@Bean
	public MongoTypeMapper mongoTypeMapper() {
		return new DefaultMongoTypeMapper(null);
	}

	@Bean
	public MongoMappingContext mongoMappingContext() {
		return new MongoMappingContext();
	}

	@Bean
	public MappingMongoConverter mongoConverter() throws UnknownHostException {
		MappingMongoConverter converter = new MappingMongoConverter(mongoDbFactory(), mongoMappingContext());
		converter.setTypeMapper(mongoTypeMapper());
		return converter;
	}
}
