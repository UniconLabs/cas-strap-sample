package org.jasig.cas.service

import org.jasig.cas.authentication.principal.Credentials
import org.jasig.cas.authentication.saml.SpringSecuritySamlCredentials
import org.jasig.cas.domain.Group
import org.jasig.cas.domain.Person
import org.jasig.cas.domain.SamlGroup

/**
 * Service for handling users and groups
 */
public interface PersonService {
    Group getGroup(Person person)

    /**
     * Get a group by an ID. The ID is used by the web flow so a fully hydrated group isn't stored in memory.
     *
     * @param id ID of the group
     * @return group with the given ID
     */
    Group getGroup(Long id)

    /**
     * Find a group by the discovery key. Used for login discovery.
     *
     * @param discoveryKey
     * @return
     */
    Group findGroupByDiscoveryKey(String discoveryKey)
    /**
     * Find a group by the user. User for login discovery.
     *
     * @param username
     * @return
     */
    Person findPersonByUsername(String username)

    /**
     * Find a person by an external ID and a group. This is used when returning from an external IdP authentication.
     * Both external ID and group are needed to resolve any collisions.
     *
     * @param externalId
     * @param group
     * @return
     */
    Person findPersonByExternalIdAndGroup(String externalId, Group group)

    /**
     * Find a person based on credentials.
     * @param credentials
     * @return
     */
    Person findPersonByCredentials(Credentials credentials)
}