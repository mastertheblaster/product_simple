package lt.markmerkk.mock_app.dagger.components

import dagger.Subcomponent
import lt.markmerkk.mock_app.MainActivity
import lt.markmerkk.mock_app.dagger.modules.ActivityModule
import lt.markmerkk.mock_app.dagger.scopes.PerActivityScope

/**
 * @author mariusmerkevicius
 * @since 2016-07-23
 */
@PerActivityScope
@Subcomponent(
        modules = arrayOf(ActivityModule::class)
)
interface ActivityComponent {
    fun inject(activity: MainActivity)
}