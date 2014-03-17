package org.jasig.cas.service

import org.jasig.cas.authentication.principal.Credentials
import org.jasig.cas.authentication.saml.SpringSecuritySamlCredentials
import org.jasig.cas.domain.ExternalPerson
import org.jasig.cas.domain.Group
import org.jasig.cas.domain.Idp
import org.jasig.cas.domain.Person
import org.jasig.cas.domain.SamlGroup

/**
 * A super simple implementation of the PersonService
 */
class SuperSimplePersonServiceImpl implements PersonService {
    SamlGroup samlGroup = new SamlGroup(
            discoveryKey: "idp",
            id: 0,
            idp: new Idp(
                    idpId: "https://test.scaldingspoon.org/idp/shibboleth"
            ),
            externalIdAttribute: "principal"
    )

    @Override
    Group getGroup(Person person) {
        return person.group
    }

    @Override
    Group getGroup(Long id) {
        id == 0 ? samlGroup : new Group(id: id)
    }

    @Override
    Group findGroupByDiscoveryKey(String discoveryKey) {
        discoveryKey == "idp" ? samlGroup : new Group(discoveryKey: discoveryKey)
    }

    @Override
    Person findPersonByUsername(String username) {
        return new Person(username: username, group: new Group())
    }

    @Override
    Person findPersonByExternalIdAndGroup(String externalId, Group group) {
        return new ExternalPerson(externalId: externalId, username: externalId, group: group)
    }

    @Override
    Person findPersonByCredentials(Credentials credentials) {
        if (credentials instanceof SpringSecuritySamlCredentials) {
            return new ExternalPerson(externalId: credentials.samlPrincipalId, username: credentials.samlPrincipalId, group: credentials.samlGroup)
        }
    }
}
