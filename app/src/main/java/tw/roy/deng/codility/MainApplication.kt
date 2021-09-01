package tw.roy.deng.codility

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import tw.roy.deng.codility.di.DaggerAppComponent
import tw.roy.deng.codility.di.module.AppModule
import javax.inject.Inject

class MainApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        AppInitializerProvider.get().init(this)

        DaggerAppComponent.builder()
            .application(this)
            .appModule(AppModule(this, applicationContext))
            .build()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return injector
    }
}