package org.jasig.cas.authentication.yubi

import org.jasig.cas.authentication.mfa.ChainedCredentials
import groovy.transform.InheritConstructors

@InheritConstructors
class YubiCredentials extends ChainedCredentials {
    String otp
}
