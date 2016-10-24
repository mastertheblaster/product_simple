package lt.markmerkk.mock_app.mvp

import lt.markmerkk.mock_app.networking.ProductsService
import lt.markmerkk.mock_app.networking.entities.Product
import rx.Scheduler

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
    override fun onAttach() {
        api.products()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe({
                    handleSuccess(it)
                }, {
                    view.showError(it)
                })
    }

    override fun onDetach() {
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