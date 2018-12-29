package nam.tran.flatform.model.core

interface IHeader<T>{
    val isHeader: Boolean
    val headerValue : T?
}