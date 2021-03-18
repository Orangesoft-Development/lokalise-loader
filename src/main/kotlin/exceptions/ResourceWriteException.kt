package exceptions

class ResourceWriteException(failedLocales: List<String>) :
    LokaliseException("Error occurred while writing locales: $failedLocales")