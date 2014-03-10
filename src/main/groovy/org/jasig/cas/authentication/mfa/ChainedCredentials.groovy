package org.jasig.cas.authentication.mfa

import org.jasig.cas.authentication.Authentication
import org.jasig.cas.authentication.principal.Credentials
import org.jasig.cas.authentication.principal.Principal

class ChainedCredentials implements Credentials {
    final Authentication previousAuthentication
    final Credentials previousCredentials

    public ChainedCredentials(Authentication previousAuthentication, Credentials previousCredentials) {
        this.previousAuthentication = previousAuthentication
        this.previousCredentials = previousCredentials
    }

    public Principal getPrincipal() {
        this.previousAuthentication?.principal
    }
}
