package lt.markmerkk.mock_app.mvp

/**
 * @author mariusmerkevicius
 * @since 2016-10-24
 */
interface ProductsView {
    fun showProducts()
    fun showEmptyState()
    fun showError(error: Throwable)
}