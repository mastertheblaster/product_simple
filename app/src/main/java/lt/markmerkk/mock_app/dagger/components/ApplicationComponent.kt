package lt.markmerkk.mock_app.dagger.components

import dagger.Component
import lt.markmerkk.mock_app.MockApplication
import lt.markmerkk.mock_app.dagger.modules.ActivityModule
import lt.markmerkk.mock_app.dagger.modules.ApplicationModule
import lt.markmerkk.mock_app.dagger.scopes.PerApplicationScope

/**
 * @author mariusmerkevicius
 * @since 2016-07-22
 */
@PerApplicationScope
@Component(
        modules = arrayOf(ApplicationModule::class)
)
interface ApplicationComponent {

    fun inject(application: MockApplication)

    fun activityComponent(activityModule: ActivityModule): ActivityComponent

}