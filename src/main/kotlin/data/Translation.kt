package data

import com.google.gson.annotations.SerializedName

data class Translation(
    @SerializedName("translation_id")
    val translationId: Long,
    @SerializedName("key_id")
    val keyId: Long,
    @SerializedName("language_iso")
    val languageIso: String,
    @SerializedName("translation")
    val value: String,
)
