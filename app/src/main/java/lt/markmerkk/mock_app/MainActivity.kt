package lt.markmerkk.mock_app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import lt.markmerkk.mock_app.dagger.modules.ActivityModule
import lt.markmerkk.mock_app.mvp.ProductsPresenterImpl
import lt.markmerkk.mock_app.mvp.ProductsView
import lt.markmerkk.mock_app.networking.ProductsService
import lt.markmerkk.mock_app.utils.Logger
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

import javax.inject.Inject

class MainActivity : AppCompatActivity(), ProductsView {

    @Inject
    lateinit var productsService: ProductsService

    val productsPresenter by lazy {
        ProductsPresenterImpl(
                view = this,
                api = productsService,
                uiScheduler = AndroidSchedulers.mainThread(),
                ioScheduler = Schedulers.io()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MockApplication)
                .component
                .activityComponent(ActivityModule(this))
                .inject(this)
        productsPresenter.onAttach()
    }

    override fun onDestroy() {
        productsPresenter.onDetach()
        super.onDestroy()
    }

    //region Products mvp

    override fun showProducts() {
        throw UnsupportedOperationException()
    }

    override fun showEmptyState() {
        throw UnsupportedOperationException()
    }

    override fun showError(error: Throwable) {
        throw UnsupportedOperationException()
    }

    //endregion

}
