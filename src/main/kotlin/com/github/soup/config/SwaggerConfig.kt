package com.github.soup.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SwaggerConfig {
	@Bean
	fun docket(): Docket {
		return Docket(DocumentationType.OAS_30)
			.useDefaultResponseMessages(false)
			.apiInfo(apiInfo())
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.github.soup"))
			.paths(PathSelectors.any())
			.build()
	}

	private fun apiInfo(): ApiInfo {
		return ApiInfoBuilder()
			.title("soup service server")
			.version("1.0")
			.description("soup service server api documents")
			.build()
	}
}