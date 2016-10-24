package lt.markmerkk.mock_app.mvp

import lt.markmerkk.mock_app.networking.ProductsService
import lt.markmerkk.mock_app.networking.entities.Product
import rx.Scheduler
import rx.Subscription

/**
 * @author mariusmerkevicius
 * @since 2016-10-24
 */
class ProductsPresenterImpl(
        private val view: ProductsView,
        private val api: ProductsService,
        private val uiScheduler: Scheduler,
        private val ioScheduler: Scheduler
) : ProductsPresenter {

    var subscribtion: Subscription? = null

    override fun onAttach() {
        loadProducts()
    }

    override fun onDetach() {
        subscribtion?.unsubscribe()
    }

    override fun loadProducts() {
        subscribtion?.unsubscribe()
        subscribtion = api.products()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .doOnSubscribe { view.showProgress() }
                .doOnUnsubscribe { view.hideProgress() }
                .subscribe({
                    handleSuccess(it)
                }, {
                    view.showError(it)
                })
    }

    //region Convenience

    fun handleSuccess(products: List<Product>) {
        if (products.size > 0) {
            view.showProducts(products)
        } else {
            view.showEmptyState()
        }
    }

    //endregion

}