package org.jasig.cas.verifier

import groovy.util.logging.Slf4j
import org.jasig.cas.authentication.handler.AuthenticationException
import org.jasig.cas.domain.Person
import org.springframework.binding.message.MessageBuilder
import org.springframework.binding.message.MessageContext

@Slf4j
class SimplePersonVerifier implements PersonVerifier {
    @Override
    void verify(Person person) throws AuthenticationException {

    }

    String doVerify(Person person, MessageContext messageContext) {
        try {
            this.verify(person)
        }
        catch (AuthenticationException e) {
            log.error(e.message, person.username)
            try {
                messageContext.addMessage(new MessageBuilder().error().code(e.getCode()).defaultText(e.getCode()).build());
            } catch (final Exception fe) {
                log.error(fe.getMessage(), fe);
            }
            return e.type
        }
        "success"
    }
}
