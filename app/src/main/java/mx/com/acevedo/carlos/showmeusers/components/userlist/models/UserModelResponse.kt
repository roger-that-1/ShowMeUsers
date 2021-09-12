package mx.com.acevedo.carlos.showmeusers.components.userlist.models

import com.google.gson.annotations.SerializedName

data class UserModelResponse(
    @SerializedName("results")
    val results: List<Result>?
) {
    data class Result(
        @SerializedName("name")
        val name: Name?,
        @SerializedName("location")
        val location: Location?,
        @SerializedName("picture")
        val profilePicture: ProfilePicture
    )

    data class Name(
        @SerializedName("title")
        val title: String?,
        @SerializedName("first")
        val first: String?,
        @SerializedName("last")
        val last: String?
    )

    data class Location(
        @SerializedName("country")
        val country: String?,
        @SerializedName("street")
        val street: Street?,
        @SerializedName("city")
        val city: String?,
        @SerializedName("state")
        val state: String?,
        @SerializedName("postcode")
        val postCode: Any?
    )

    data class Street(
        @SerializedName("number")
        val number: Int?,
        @SerializedName("name")
        val name: String?
    )

    data class ProfilePicture(
        @SerializedName("medium")
        val pictureMedium: String,
        @SerializedName("large")
        val pictureLarge: String
    )
}