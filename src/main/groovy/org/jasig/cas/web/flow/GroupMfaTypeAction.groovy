package org.jasig.cas.web.flow

import org.jasig.cas.domain.Group
import org.jasig.cas.domain.Person
import org.jasig.cas.service.PersonService

/**
 * Action state to determine if the current authentication type id a second factor (2fa) or a regular (primary) type
 * based on the current customer group
 *
 * @author JJ
 * @author Unicon, inc.
 */
class GroupMfaTypeAction {
    public static final String NONE_TYPE = "none"

    PersonService personService

    public String getType(Person person) {
        return person?.group?.mfaType ?: NONE_TYPE
    }
}
