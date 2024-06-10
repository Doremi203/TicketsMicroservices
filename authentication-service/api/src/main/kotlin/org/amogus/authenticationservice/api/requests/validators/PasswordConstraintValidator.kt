package org.amogus.authenticationservice.api.requests.validators

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.amogus.authenticationservice.api.configuration.properties.PasswordProperties
import org.passay.*


class PasswordConstraintValidator(
    private val passwordSettings: PasswordProperties
) : ConstraintValidator<ValidPassword, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        val validator = PasswordValidator(
            listOf(
                LengthRule(passwordSettings.minLength, passwordSettings.maxLength),
                CharacterRule(EnglishCharacterData.UpperCase, passwordSettings.minUpperCase),
                CharacterRule(EnglishCharacterData.LowerCase, passwordSettings.minLowerCase),
                CharacterRule(EnglishCharacterData.Digit, passwordSettings.minDigit),
                CharacterRule(EnglishCharacterData.Special, passwordSettings.minSpecial),
                WhitespaceRule()
            )
        )

        val result = validator.validate(PasswordData(value))
        if (result.isValid) {
            return true
        }
        context?.buildConstraintViolationWithTemplate(
            validator.getMessages(result).stream().findFirst().get()
        )
            ?.addConstraintViolation()
            ?.disableDefaultConstraintViolation()
        return false
    }
}