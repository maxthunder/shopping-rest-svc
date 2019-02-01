package shopping;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import shopping.util.SwaggerStatus;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application {

	private enum SwaggerStatus {
		AUTO_LAUNCH_SWAGGER("Auto Launch Swagger", true),
		QUIET_MODE("Quiet Mode", false);

		String name;
		boolean autoLaunchSwagger;

		SwaggerStatus(String name, boolean autoLaunchSwagger) {
			this.name = name;
			this.autoLaunchSwagger = autoLaunchSwagger;
		}
	}

	@Value("${auto.launch.swagger}")
	private String shouldAutoLaunchSwagger;

	private static SwaggerStatus swaggerStatus;

	@Value("${spring.data.rest.base-path}")
	private String basePath;

	@Value("${default.welcome.page.address}")
	private String defaultPage;


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Docket shirtOrdersApi() {
		List<String> paths = new ArrayList<>();
		paths.add(this.basePath + "/shirtOrders.*");
		paths.add(this.basePath + "/customers.*");
		paths.add(this.basePath + "/shirts.*");

		return (new Docket(DocumentationType.SWAGGER_2))
				.groupName("shopping")
				.apiInfo(this.shoppingApiInfo())
				.select()
				.paths(PathSelectors.regex(paths.stream().collect(Collectors.joining("|"))))
				.build();
	}

	private ApiInfo shoppingApiInfo() {
		return (new ApiInfoBuilder())
				.title("Shopping REST Service")
				.description("Shopping REST Service with Swagger")
				.contact(new Contact("Maxwell", "", ""))
				.license("Apache License Version 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
				.version("1.0")
				.build();
	}

	/*
	* EventListener and Browse are used to automatically open browser to swagger page on 'mvn spring-boot:run'
	*/

	@EventListener({ApplicationReadyEvent.class})
	public void applicationReadyEvent() {
		determineStatus(shouldAutoLaunchSwagger);
		if (swaggerStatus.equals(SwaggerStatus.AUTO_LAUNCH_SWAGGER)) {
			System.out.println("Application started ... launching browser now");
			Browse(defaultPage);
		} else {
			System.out.println("Application started ... browser not launched due to " + SwaggerStatus.QUIET_MODE);
		}

	}

	private static void Browse(String url) {
		if(Desktop.isDesktopSupported()){
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.browse(new URI(url));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		} else{
			Runtime runtime = Runtime.getRuntime();
			try {
				runtime.exec("open " + url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void determineStatus(String shouldAutoLaunchSwagger) {
		if (Boolean.valueOf(shouldAutoLaunchSwagger).equals(true)) {
			swaggerStatus = SwaggerStatus.AUTO_LAUNCH_SWAGGER;
		} else {
			swaggerStatus = SwaggerStatus.QUIET_MODE;
		}
	}
}
