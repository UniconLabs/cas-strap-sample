package org.jasig.cas.service

import org.jasig.cas.authentication.principal.Credentials
import org.jasig.cas.authentication.saml.SpringSecuritySamlCredentials
import org.jasig.cas.domain.ExternalPerson
import org.jasig.cas.domain.Group
import org.jasig.cas.domain.Idp
import org.jasig.cas.domain.Person
import org.jasig.cas.domain.SamlGroup

class PersonServiceImpl implements PersonService {
    def groups = [
            new SamlGroup().with {
                id = 1
                idp = new Idp(
                        idpId: "https://test.scaldingspoon.org/idp/shibboleth",
                        metadataUrl: "https://test.scaldingspoon.org/idp/profile/Metadata/SAML"
                )
                externalIdAttribute = "principal"
                discoveryKey = "idp"
                return it
            },
            new Group().with {
                id = 2
                mfaType = "yubi"
                discoveryKey = "yubi"
                return it
            },
            new Group(
                    id: 3,
                    mfaType: "duo",
                    discoveryKey: "duo"
            ),
            new Group(
                    id: 4,
                    discoveryKey: "auth"
            )
    ] as List<Group>

    def people = [
            new ExternalPerson().with {
                username = "jj"
                group = groups.find { it.id == 1 }
                externalId = "jj"
                return it
            },
            new Person().with {
                username = "yubi"
                group = groups.find { it.discoveryKey == "yubi" }
                yubiKey = "ccccccdbiikb"
                return it
            },
            new Person(
                    username: "duo",
                    group: groups.find { it.discoveryKey == "duo" }
            ),
            new Person(
                    username: "auth",
                    group: groups.find { it.discoveryKey == "auth" }
            )

    ] as List<Person>

    @Override
    Group findGroupByDiscoveryKey(String discoveryKey) {
        groups.find { it.discoveryKey == discoveryKey }
    }

    @Override
    Person findPersonByUsername(String username) {
        people.find { it.username == username }
    }

    @Override
    Person findPersonByExternalIdAndGroup(String externalId, Group group) {
        people.find {
            it instanceof ExternalPerson && ((ExternalPerson) it).externalId == externalId && it.group == group
        }
    }

    @Override
    Person findPersonByCredentials(Credentials credentials) {
        if (credentials instanceof SpringSecuritySamlCredentials) {
            return this.findPersonByExternalIdAndGroup(credentials.samlPrincipalId, credentials.samlGroup)
        }
        return null
    }

    @Override
    Group getGroup(Long id) {
        groups.find { it.id == id }
    }
}
