package org.jasig.cas.authentication.duo

import groovy.transform.InheritConstructors
import org.jasig.cas.authentication.mfa.ChainedCredentials

@InheritConstructors
class DuoCredentials extends ChainedCredentials {
    String signedDuoResponse
}
