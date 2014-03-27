package com.gc.drools;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatelessKnowledgeSession;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: gch
 * Date: 28.03.14 0:44
 */
public class RuleTest {

    private final KnowledgeFacade knowledgeFacade = new KnowledgeFacade();

    @Test
    public void bonusShouldNotProvidedWhenBalanceIsLow() throws Exception {
        KnowledgeBase knowledgeBase = knowledgeFacade.createKnowledgeBase("account_rule.drl");
        StatelessKnowledgeSession statelessKnowledgeSession = knowledgeBase.newStatelessKnowledgeSession();
        Account account = new Account();
        account.setBalance(100);
        account.setBonus(0);
        statelessKnowledgeSession.execute(account);
        assertThat(account.getBonus(), is(0L));
    }

    @Test
    public void bonusShouldBeProvidedWhenBalanceIsHigh() throws Exception {
        KnowledgeBase knowledgeBase = knowledgeFacade.createKnowledgeBase("account_rule.drl");
        StatelessKnowledgeSession statelessKnowledgeSession = knowledgeBase.newStatelessKnowledgeSession();
        Account account = new Account();
        account.setBalance(200_000_00);
        account.setBonus(0);
        statelessKnowledgeSession.execute(account);
        assertThat(account.getBonus(), is(100L));
    }
}

