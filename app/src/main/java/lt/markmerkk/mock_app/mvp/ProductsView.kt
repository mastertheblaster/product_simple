package lt.markmerkk.mock_app.mvp

import lt.markmerkk.mock_app.networking.entities.Product

/**
 * @author mariusmerkevicius
 * @since 2016-10-24
 */
interface ProductsView {
    fun showProducts(products: List<Product>)
    fun showEmptyState()
    fun showError(error: Throwable)
}