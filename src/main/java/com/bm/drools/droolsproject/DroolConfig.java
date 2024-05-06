package com.bm.drools.droolsproject;

import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @PROJECT IntelliJ IDEA
 * @AUTHOR Bikash Mainali
 * @DATE 5/5/24
 */

@Configuration
public class DroolConfig {

    private final KieServices kieServices = KieServices.Factory.get();
    //private final String RULE_PATH = "rules/loan_rate.drl";
    private final String RULE_PATH = "rules/loan_rate.drl.xls";

    private KieFileSystem getKieFileSystem() throws IOException{
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(RULE_PATH));
        return kieFileSystem;
    }

    @Bean
    public KieContainer getKieContainer() throws IOException {
        System.out.println("Container created...");
        KieBuilder kieBuilder = kieServices.newKieBuilder(getKieFileSystem());
        kieBuilder.buildAll();
        KieRepository kRepository = kieServices.getRepository();
        ReleaseId kDefaultReleaseId = kRepository.getDefaultReleaseId();
        KieContainer kContainer = kieServices.newKieContainer(kDefaultReleaseId);
        return kContainer;
    }

    @Bean
    public KieSession getKieSession() throws IOException {
        System.out.println("session created...");
        return getKieContainer().newKieSession();
    }
}
