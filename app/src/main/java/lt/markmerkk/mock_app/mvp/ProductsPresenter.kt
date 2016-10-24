package lt.markmerkk.mock_app.mvp

/**
 * @author mariusmerkevicius
 * @since 2016-10-24
 */
interface ProductsPresenter {
    fun onAttach()
    fun onDetach()

    fun reloadProducts()
    fun appendProducts()
}