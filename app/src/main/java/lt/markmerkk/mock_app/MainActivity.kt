package lt.markmerkk.mock_app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import lt.markmerkk.mock_app.adapters.ProductListAdapter
import lt.markmerkk.mock_app.dagger.modules.ActivityModule
import lt.markmerkk.mock_app.mvp.ProductsPresenterImpl
import lt.markmerkk.mock_app.mvp.ProductsView
import lt.markmerkk.mock_app.networking.ProductsService
import lt.markmerkk.mock_app.networking.entities.Product
import lt.markmerkk.mock_app.utils.Logger
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

import javax.inject.Inject

class MainActivity : AppCompatActivity(), ProductsView {

    @Inject
    lateinit var productsService: ProductsService

    lateinit var adapter: ProductListAdapter
    lateinit var recyclerView: RecyclerView

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

        adapter = ProductListAdapter(this)
        recyclerView = findViewById(R.id.list_view) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        productsPresenter.onDetach()
        super.onDestroy()
    }

    //region Products mvp

    override fun showProducts(products: List<Product>) {
        adapter.products = products
    }

    override fun showEmptyState() {
    }

    override fun showError(error: Throwable) {
        AlertDialog
                .Builder(this)
                .setTitle(getString(R.string.general_error))
                .setMessage(getString(R.string.error_no_products))
                .show()
    }

    //endregion

}
