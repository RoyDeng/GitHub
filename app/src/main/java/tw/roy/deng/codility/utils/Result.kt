package tw.roy.deng.codility.utils

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Fail(val message: String) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "成功[data=$data]"
            is Fail -> "失敗[message=$message]"
        }
    }
}