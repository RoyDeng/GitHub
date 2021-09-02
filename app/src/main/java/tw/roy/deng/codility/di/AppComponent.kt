package tw.roy.deng.codility.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import tw.roy.deng.codility.MainApplication
import tw.roy.deng.codility.di.module.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        RepositoryModule::class,
        AppModule::class,
        DatabaseModule::class,
        ActivityBuilderModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        DataSourceModule::class
    ]
)
interface AppComponent {
    fun inject(application: MainApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MainApplication): Builder
        fun appModule(module: AppModule): Builder
        fun build(): AppComponent
    }
}