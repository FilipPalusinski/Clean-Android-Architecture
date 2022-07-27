package pl.palusinskifilip.usingokhttpandretrofit

data class UiState (
    val userList: List<UserEntity> = listOf(),
    val count: String = ""
)