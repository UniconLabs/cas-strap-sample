package org.jasig.cas.verifier

import org.jasig.cas.authentication.handler.AuthenticationException
import org.jasig.cas.domain.Person

interface PersonVerifier {
    void verify(Person person) throws AuthenticationException
}
