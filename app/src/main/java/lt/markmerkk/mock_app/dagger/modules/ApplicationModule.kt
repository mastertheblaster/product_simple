package lt.markmerkk.mock_app.dagger.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import lt.markmerkk.mock_app.dagger.scopes.PerApplicationScope
import lt.markmerkk.mock_app.networking.ProductsService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author mariusmerkevicius
 * @since 2016-07-22
 */
@Module
class ApplicationModule(
        val application: Application
) {

    @Provides
    @PerApplicationScope
    fun provideApplication(): Application {
        return application
    }

    @Provides
    @PerApplicationScope
    fun provideRetrofit(): Retrofit {
        return  Retrofit.Builder()
                .baseUrl("https://stark-atoll-33661.herokuapp.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @PerApplicationScope
    fun provideProductsService(retrofit: Retrofit): ProductsService {
        return retrofit.create(ProductsService::class.java)
    }

}