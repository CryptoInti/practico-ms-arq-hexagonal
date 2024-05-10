//package com.empresa.practica.infrastructure.services;
//
//import com.empresa.practica.domain.services.RuleExecutionService;
//import org.kie.api.KieServices;
//import org.kie.api.runtime.KieContainer;
//import org.kie.api.runtime.KieSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class DroolsRuleExecutionService implements RuleExecutionService {
//
//    private final KieContainer kieContainer;
//
//    @Autowired
//    public DroolsRuleExecutionService(KieContainer kieContainer) {
//        this.kieContainer = kieContainer;
//    }
//
//    @Override
//    public void executeRules(Object data) {
//        KieSession kieSession = kieContainer.newKieSession();
//        try {
//            // Insertar datos en la sesión de Drools
//            kieSession.insert(data);
//            // Ejecutar todas las reglas
//            kieSession.fireAllRules();
//        } finally {
//            kieSession.dispose();
//        }
//    }
//}
