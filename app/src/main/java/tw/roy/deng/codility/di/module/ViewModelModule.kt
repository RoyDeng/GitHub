package tw.roy.deng.codility.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tw.roy.deng.codility.factory.ViewModelKey
import tw.roy.deng.codility.factory.ViewModelProviderFactory
import tw.roy.deng.codility.ui.user.UserFragmentViewModel

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(
        factory: ViewModelProviderFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UserFragmentViewModel::class)
    abstract fun providesAnalyticsFragmentViewModel(
        viewModel: UserFragmentViewModel
    ): ViewModel
}