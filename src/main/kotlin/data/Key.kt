package data

import com.google.gson.annotations.SerializedName

data class Key(
    @SerializedName("key_id")
    val keyId: Long,
    @SerializedName("key_name")
    val keyName: KeyName,
    @SerializedName("platforms")
    val platforms: List<String>,
)
