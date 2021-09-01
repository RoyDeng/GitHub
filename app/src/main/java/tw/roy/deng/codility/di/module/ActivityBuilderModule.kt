package tw.roy.deng.codility.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import tw.roy.deng.codility.ui.MainActivity
import tw.roy.deng.codility.ui.user.UserFragment

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun injectMainActivityFragments(): MainActivity
}

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeUserFragment(): UserFragment
}