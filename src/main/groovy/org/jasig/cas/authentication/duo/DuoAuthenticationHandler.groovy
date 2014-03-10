package org.jasig.cas.authentication.duo

import groovy.util.logging.Slf4j
import org.jasig.cas.authentication.handler.AuthenticationException
import org.jasig.cas.authentication.handler.AuthenticationHandler
import org.jasig.cas.authentication.principal.Credentials

@Slf4j
class DuoAuthenticationHandler implements AuthenticationHandler {
    private final DuoAuthenticationService duoAuthenticationService

    DuoAuthenticationHandler(DuoAuthenticationService duoAuthenticationService) {
        this.duoAuthenticationService = duoAuthenticationService
    }

    @Override
    boolean authenticate(Credentials credentials) throws AuthenticationException {
        final duoCredentials = credentials.asType(DuoCredentials)

        // Do an out of band request using the DuoWeb api (encapsulated in DuoAuthenticationService) to the hosted duo service, if it is successful
        // it will return a String containing the username of the successfully authenticated user, but will
        // return a blank String otherwise.
        final duoVerifyResponse = this.duoAuthenticationService.authenticate(duoCredentials.signedDuoResponse)
        log.debug("Response from Duo verify: [{}]", duoVerifyResponse)
        final principalId = duoCredentials.principal?.id
        final isGoodAuthentication = duoVerifyResponse == principalId
        if (isGoodAuthentication) {
            log.info("Successful Duo authentication for [{}]", principalId)
            return true
        }
        log.error("Duo authentication error! Login username: [{}], Duo response: [{}]", principalId ?: 'null', duoVerifyResponse)
        false
    }

    @Override
    boolean supports(Credentials credentials) {
        DuoCredentials.isAssignableFrom(credentials.class)
    }
}
