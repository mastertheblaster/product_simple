package lt.markmerkk.mock_app.networking.entities

/**
 * @author mariusmerkevicius
 * @since 2016-10-24
 */
data class Product(
        val title: String? = null,
        val image: String? = null,
        val price: Float = 0.0f
) {
    fun formattedPrice(): String {
        return "$" + String.format("%2.2f", price)
    }
}