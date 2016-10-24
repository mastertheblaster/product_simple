package lt.markmerkk.mock_app

import android.app.Application
import android.content.Intent
import lt.markmerkk.mock_app.dagger.components.ApplicationComponent
import lt.markmerkk.mock_app.dagger.modules.ApplicationModule

/**
 * @author mariusmerkevicius
 * @since 2016-10-24
 */
class MockApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
//        assembleComponents()
    }

    fun assembleComponents() {
//        component = DaggerApplicationComponent
//                .builder()
//                .applicationModule(ApplicationModule(this))
//                .build()
        component.inject(this)
    }


}