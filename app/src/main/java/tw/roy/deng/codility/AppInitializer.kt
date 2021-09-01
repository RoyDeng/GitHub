package tw.roy.deng.codility

interface AppInitializer {
    fun init(app: MainApplication)
}

open class AppInitializerImpl : AppInitializer {
    override fun init(app: MainApplication) {

    }
}