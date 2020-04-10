package com.goku.enderecos.config;

import java.util.Arrays;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfiguration {

	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		Cache todosEnderecosCache = new ConcurrentMapCache("enderecos-cache");
		Cache enderecoCepCache = new ConcurrentMapCache("enderecos-cep-cache");
		cacheManager.setCaches(Arrays.asList(todosEnderecosCache, enderecoCepCache));
		return cacheManager;
	}

}
