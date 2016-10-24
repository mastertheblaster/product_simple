package lt.markmerkk.mock_app.mvp

import lt.markmerkk.mock_app.networking.ProductsService
import lt.markmerkk.mock_app.utils.Logger
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
                    Logger.log("Response: $it")
                }, {
                    Logger.log("Error: $it")
                })
    }

    override fun onDetach() {
    }
}