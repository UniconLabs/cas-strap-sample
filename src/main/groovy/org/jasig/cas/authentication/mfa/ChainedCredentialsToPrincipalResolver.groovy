package org.jasig.cas.authentication.mfa

import org.jasig.cas.authentication.principal.Credentials
import org.jasig.cas.authentication.principal.CredentialsToPrincipalResolver
import org.jasig.cas.authentication.principal.Principal
import org.jasig.cas.authentication.principal.SimplePrincipal

class ChainedCredentialsToPrincipalResolver implements CredentialsToPrincipalResolver {
    @Override
    Principal resolvePrincipal(Credentials credentials) {
        def chainedCredentials = credentials as ChainedCredentials
        new SimplePrincipal(chainedCredentials.principal?.id, chainedCredentials.principal?.attributes)
    }

    @Override
    boolean supports(Credentials credentials) {
        ChainedCredentials.isAssignableFrom(credentials.class)
    }
}
