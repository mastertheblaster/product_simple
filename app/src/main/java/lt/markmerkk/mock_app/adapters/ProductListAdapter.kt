package lt.markmerkk.mock_app.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import lt.markmerkk.mock_app.R
import lt.markmerkk.mock_app.networking.entities.Product

/**
 * @author mariusmerkevicius
 * @since 2016-10-24
 */
class ProductListAdapter(
        private val context: Context
) : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    val inflater = LayoutInflater.from(context)
    var products: List<Product> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.titleView.text = products[position].title
    }

    override fun getItemCount(): Int = products.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(inflater.inflate(R.layout.list_item_product, parent, false))
    }

    class ProductViewHolder(
            val view: View
    ) : RecyclerView.ViewHolder(view) {
        val titleView = view.findViewById(R.id.product_title) as TextView
    }

}