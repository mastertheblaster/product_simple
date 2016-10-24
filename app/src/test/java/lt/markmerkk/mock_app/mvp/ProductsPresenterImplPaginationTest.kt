package lt.markmerkk.mock_app.mvp

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import lt.markmerkk.mock_app.networking.ProductsService
import lt.markmerkk.mock_app.networking.entities.Product
import org.junit.Assert.*
import org.junit.Test
import rx.Observable
import rx.schedulers.Schedulers

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-10-24
 */
class ProductsPresenterImplPaginationTest {
    val view: ProductsView = mock()
    val api: ProductsService = mock()
    val presenter = ProductsPresenterImpl(
            view,
            api,
            Schedulers.immediate(),
            Schedulers.immediate()
    )

    @Test
    fun reload_reloadPage() {
        // Assemble
        whenever(api.products(any())).thenReturn(
                Observable.just(listOf(
                        Product(), Product(), Product()
                ))
        )

        // Act
        presenter.reloadProducts()

        // Assert
        verify(view).showProducts(any())
    }

    @Test
    fun append_appendResponse() {
        // Assemble
        whenever(api.products(any())).thenReturn(
                Observable.just(listOf(
                        Product(), Product(), Product()
                ))
        )

        // Act
        presenter.appendProducts()

        // Assert
        verify(view).appendProducts(any())
    }
}