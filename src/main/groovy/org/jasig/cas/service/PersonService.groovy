package org.jasig.cas.service

import org.jasig.cas.authentication.saml.SpringSecuritySamlCredentials
import org.jasig.cas.domain.Group
import org.jasig.cas.domain.Person
import org.jasig.cas.domain.SamlGroup

/**
 * Created by jj on 2/25/14.
 */
public interface PersonService {
    Group getGroup(Person person)
    Group getGroup(Long id)

    Group findGroupByDiscoveryKey(String discoveryKey)
    Person findPersonByUsername(String username)

    Person findPersonByExternalIdAndGroup(String externalId, SamlGroup samlGroup)

    Person findPersonBySpringSecuritySamlCredentials(SpringSecuritySamlCredentials springSecuritySamlCredentials)
}