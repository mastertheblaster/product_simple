package lt.markmerkk.mock_app.dagger.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import lt.markmerkk.mock_app.dagger.scopes.PerApplicationScope

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

}