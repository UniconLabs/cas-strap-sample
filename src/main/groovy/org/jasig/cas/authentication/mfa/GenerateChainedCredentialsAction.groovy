package org.jasig.cas.authentication.mfa

import groovy.util.logging.Slf4j
import net.unicon.cas.addons.authentication.AuthenticationSupport
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials
import org.jasig.cas.web.support.WebUtils
import org.springframework.webflow.execution.RequestContext

@Slf4j
class GenerateChainedCredentialsAction {
    private final AuthenticationSupport authenticationSupport

    public GenerateChainedCredentialsAction(AuthenticationSupport authenticationSupport) {
        this.authenticationSupport = authenticationSupport
    }

    String createChainedCredentials(RequestContext context, Class<? extends ChainedCredentials> toClass) {
        def tgtId = WebUtils.getTicketGrantingTicketId(context)
        def originalAuthentication = this.authenticationSupport.getAuthenticationFrom(tgtId)
        def principal = originalAuthentication?.principal
        if (!principal) {
            log.error("Failed to retrieve authentication principal from TGT {}", tgtId)
            return "error"
        }
        def credentials  = toClass.newInstance(originalAuthentication, new UsernamePasswordCredentials(username: principal.id))
        log.debug("Retrieved authentication context. Building Duo credentials [{}]", credentials)
        context.flowScope.put("chainedCredentials", credentials)
        return "created"
    }

    String createChainedCredentials(RequestContext context, String className) {
        return this.createChainedCredentials(context, Class.forName(className))
    }
}
