package nam.tran.data.local

interface IPreference{
    fun login(isRemember: Boolean)
    fun isRememberLogin() : Boolean
}

