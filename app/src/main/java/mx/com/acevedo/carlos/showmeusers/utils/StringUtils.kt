package mx.com.acevedo.carlos.showmeusers.utils

/**
 * Extension for Not null or blank validation
 */
fun String?.isNotNullOrBlank() = this.isNullOrBlank().not()