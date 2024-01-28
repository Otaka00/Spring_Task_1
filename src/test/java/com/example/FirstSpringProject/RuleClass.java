package com.example.FirstSpringProject;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "Testing rule", description = "Testing Status")
public class RuleClass {

        @Condition
        public boolean when() {
            return true;
        }

        @Action
        public void then() throws Exception {
            System.out.println("Testing is completed");
        }
    }

