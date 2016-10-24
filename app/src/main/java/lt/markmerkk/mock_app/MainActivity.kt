package lt.markmerkk.mock_app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import lt.markmerkk.mock_app.dagger.modules.ActivityModule
import lt.markmerkk.mock_app.networking.ProductsService
import lt.markmerkk.mock_app.utils.Logger

import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var productsService: ProductsService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MockApplication)
                .component
                .activityComponent(ActivityModule(this))
                .inject(this)


        Logger.log("Service $productsService")
    }
}
