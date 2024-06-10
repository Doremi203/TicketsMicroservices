package org.amogus.authenticationservice.domain.exceptions

import org.amogus.authenticationservice.domain.types.Email

class DuplicateEmailException(val email: Email) : RuntimeException("User with this email: ${email.value} already exists")