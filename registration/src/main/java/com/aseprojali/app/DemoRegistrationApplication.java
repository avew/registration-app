package com.aseprojali.app;

import com.aseprojali.app.config.AppProperties;
import com.aseprojali.app.config.ApplicationConstant;
import com.aseprojali.app.config.DefaultProfileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication
@EnableConfigurationProperties({AppProperties.class})
@Slf4j
@RequiredArgsConstructor
public class DemoRegistrationApplication {

    private final Environment env;

    @PostConstruct
    public void initApplication() {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        //noinspection SingleStatementInBlock
        if (activeProfiles.contains(ApplicationConstant.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(ApplicationConstant.SPRING_PROFILE_PRODUCTION)) {
            log.error("You have misconfigured your application! It should not run " +
                    "with both the 'dev' and 'prod' profiles at the same time.");
        }
        //noinspection SingleStatementInBlock
        if (activeProfiles.contains(ApplicationConstant.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(ApplicationConstant.SPRING_PROFILE_CLOUD)) {
            log.error("You have misconfigured your application! It should not " +
                    "run with both the 'dev' and 'cloud' profiles at the same time.");
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(DemoRegistrationApplication.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        //noinspection SingleStatementInBlock
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        log.info("\n----------------------------------------------------------\n\t" +
                        "APPLICATION '{}' ^^ VERSION: '{}' ^^ BUILD TIME: {} \n\tIS RUNNING! ACCESS URLs:\n\t" +
                        "LOCAL: \t\t{}://localhost:{}\n\t" +
                        "EXTERNAL: \t{}://{}:{}\n\t" +
                        "PROFILE(S): \t{}\n----------------------------------------------------------",
                env.getProperty("app.application.name"),
                env.getProperty("app.application.version"),
                env.getProperty("app.application.timestamp"),
                protocol,
                env.getProperty("server.port"),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getActiveProfiles());

    }

}
