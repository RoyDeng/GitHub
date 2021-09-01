package tw.roy.deng.codility

object AppInitializerProvider {
    fun get(): AppInitializer = AppInitializerImpl()
}