package com.ad;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.WorkingMemory;

public class HelloWorldTest {

    public static void main(String[] args) throws DroolsParserException, IOException {

        HelloWorldTest helloWorldTest = new HelloWorldTest();
        System.out.println("Object created");
        helloWorldTest.executeHelloWorldRules();
        System.out.println("Rule Executed");

    }

    public void executeHelloWorldRules() throws IOException, DroolsParserException {

        PackageBuilder packageBuilder = new PackageBuilder();

        String ruleFile = "/com/ad/Sample.drl";
        //Convert rule file to InputStream
        InputStream resourceAsStream = getClass().getResourceAsStream(ruleFile);

        Reader reader = new InputStreamReader(resourceAsStream);
        packageBuilder.addPackageFromDrl(reader);
        org.drools.core.rule.Package rulesPackage = packageBuilder.getPackage();
        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage(rulesPackage);

        //Create new WorkingMemory session for this RuleBase. By default the RuleBase retains a weak reference to returned WorkingMemory
        WorkingMemory workingMemory = ruleBase.newStatefulSession();

        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setPrintMessage("AD Drools Hello World!");

        //Insert and fire all rules until its empty
        workingMemory.insert(helloWorld);
        workingMemory.fireAllRules();

    }

}
