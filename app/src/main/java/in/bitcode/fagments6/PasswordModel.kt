package `in`.bitcode.fagments6

import java.io.Serializable

data class PasswordModel(
    val id : Int,
    val username : String,
    val password : String,
    val category : Int = -1
) : Serializable {
    companion object {
        val KEY_PASSWORD_MODEL = "password_model"

        const val CATEGORY_EMAIL = 1
        const val CATEGORY_SOCIAL_MEDIA = 2
        const val CATEGORY_ECOMMERCE = 3
        const val CATEGORY_JOB_PORTAL = 4

    }
}