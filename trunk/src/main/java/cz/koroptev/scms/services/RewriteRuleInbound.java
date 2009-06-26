package cz.koroptev.scms.services;

import org.apache.tapestry5.urlrewriter.URLRewriterRule;

/**
 * Rewrite rule is introduced as standard service. Because of I need to easy
 * access it and inject other services in it.
 * 
 * @author jan
 * 
 */
public interface RewriteRuleInbound extends URLRewriterRule {

}
