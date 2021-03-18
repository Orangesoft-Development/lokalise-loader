package exceptions

class LokaliseLoadException(entity: String) : LokaliseException("Something wrong happened while loading $entity")