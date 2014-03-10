package org.jasig.cas.authentication.yubi

import com.yubico.client.v2.YubicoClient
import com.yubico.client.v2.YubicoResponseStatus
import org.jasig.cas.authentication.handler.AuthenticationException
import org.jasig.cas.authentication.handler.AuthenticationHandler
import org.jasig.cas.authentication.principal.Credentials
import org.jasig.cas.domain.Person
import org.jasig.cas.service.PersonService

class YubiAuthenticationHandler implements AuthenticationHandler {
    final PersonService personService
    final YubicoClient yubicoClient

    public YubiAuthenticationHandler(PersonService personService, YubicoClient yubicoClient) {
        this.personService = personService
        this.yubicoClient = yubicoClient
    }

    @Override
    boolean authenticate(Credentials credentials) throws AuthenticationException {
        def yubiCredentials = credentials as YubiCredentials
        if (yubiCredentials.principal?.id) {
            def person = personService.findPersonByUsername(yubiCredentials.principal.id)
            if (person.yubiKey == yubicoClient.getPublicId(yubiCredentials.otp)) {
                def response = yubicoClient.verify(yubiCredentials.otp)
                return response.status == YubicoResponseStatus.OK
            }
        }
        return false
    }

    @Override
    boolean supports(Credentials credentials) {
        YubiCredentials.isAssignableFrom(credentials.class)
    }
}
