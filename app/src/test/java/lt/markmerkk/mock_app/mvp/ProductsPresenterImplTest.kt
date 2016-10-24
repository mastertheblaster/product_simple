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
class ProductsPresenterImplTest {

    val view: ProductsView = mock()
    val api: ProductsService = mock()
    val presenter = ProductsPresenterImpl(
            view,
            api,
            Schedulers.immediate(),
            Schedulers.immediate()
    )

    @Test
    fun successResponse_showList() {
        // Assemble
        whenever(api.products()).thenReturn(
                Observable.just(listOf(
                        Product(), Product(), Product()
                ))
        )

        // Act
        presenter.onAttach()

        // Assert
        verify(view).showProducts(any())
    }

    @Test
    fun emptyList_showEmptyState() {
        // Assemble
        whenever(api.products()).thenReturn(
                Observable.just(emptyList())
        )

        // Act
        presenter.onAttach()

        // Assert
        verify(view).showEmptyState()
    }

    @Test
    fun responseError_showErrorDialog() {
        // Assemble
        whenever(api.products()).thenReturn(
                Observable.error(RuntimeException())
        )

        // Act
        presenter.onAttach()

        // Assert
        verify(view).showError(any())
    }
}