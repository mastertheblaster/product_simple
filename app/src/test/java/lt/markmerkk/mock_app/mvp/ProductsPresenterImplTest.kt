package lt.markmerkk.mock_app.mvp

import com.nhaarman.mockito_kotlin.mock
import lt.markmerkk.mock_app.networking.ProductsService
import org.junit.Assert.*
import org.junit.Test

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-10-24
 */
class ProductsPresenterImplTest {

    val view: ProductsView = mock()
    val api: ProductsService = mock()
    val presenter = ProductsPresenterImpl(view, api)

    @Test
    fun init() {
        assertNull(presenter)
    }
}