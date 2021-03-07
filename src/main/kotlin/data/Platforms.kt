package data

sealed class Platforms(val raw: String) {
    object Android : Platforms("android")
    object IOS : Platforms("ios")
    object Web : Platforms("web")
    object Other : Platforms("other")
}
