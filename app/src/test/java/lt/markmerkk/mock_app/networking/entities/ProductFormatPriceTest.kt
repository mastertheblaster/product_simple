package lt.markmerkk.mock_app.networking.entities

import org.junit.Assert.*
import org.junit.Test

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-10-24
 */
class ProductFormatPriceTest {
    @Test
    fun simple_correctFormat() {
        assertEquals("$1.00", Product(price = 1.0f).formattedPrice())
    }

    @Test
    fun decimal_correctFormat() {
        assertEquals("$1.20", Product(price = 1.2f).formattedPrice())
    }

    @Test
    fun big_decimal_correctFormat() {
        assertEquals("$2000.35", Product(price = 2000.35f).formattedPrice())
    }
}