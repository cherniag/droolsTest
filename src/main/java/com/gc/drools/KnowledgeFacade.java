package com.gc.drools;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;

public class KnowledgeFacade {
    public KnowledgeBase createKnowledgeBase(String rulePath) {
        KnowledgeBuilder knowledgeBuilder = getKnowledgeBuilder(rulePath);
        KnowledgeBase knowledgeBase = getKnowledgeBase(knowledgeBuilder);
        return knowledgeBase;
    }

    private KnowledgeBase getKnowledgeBase(KnowledgeBuilder knowledgeBuilder) {
        KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
        return knowledgeBase;
    }

    private KnowledgeBuilder getKnowledgeBuilder(String path) {
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        knowledgeBuilder.add(
                ResourceFactory.newClassPathResource(path, getClass()),
                ResourceType.DRL
        );
        if (knowledgeBuilder.hasErrors()) {
            throw new RuntimeException("can't create knowledge builder\n"+knowledgeBuilder.getErrors());
        }
        return knowledgeBuilder;
    }
}