package exceptions

class ParameterNotSpecifiedException(parameterName: String) : LokaliseException("$parameterName must be specified")