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
    var page = 1

    override fun onAttach() {
        reloadProducts()
    }

    override fun onDetach() {
        subscribtion?.unsubscribe()
    }

    override fun reloadProducts() {
        page = 1
        load()
    }

    override fun appendProducts() {
        page += 1
        load()
    }

    private fun load() {
        subscribtion?.unsubscribe()
        subscribtion = api.products(page)
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
            if (page == 1) {
                view.showProducts(products)
            } else {
                view.appendProducts(products)
            }
        } else {
            page = 1
            view.showEmptyState()
        }
    }

    //endregion

}