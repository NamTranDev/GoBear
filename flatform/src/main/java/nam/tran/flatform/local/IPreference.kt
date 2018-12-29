package nam.tran.flatform.local

interface IPreference{
    fun login(isRemember: Boolean)
    fun isRememberLogin() : Boolean
}

