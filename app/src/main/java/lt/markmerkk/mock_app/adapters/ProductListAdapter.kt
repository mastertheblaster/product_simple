package lt.markmerkk.mock_app.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter
import lt.markmerkk.mock_app.R
import lt.markmerkk.mock_app.networking.entities.Product

/**
 * @author mariusmerkevicius
 * @since 2016-10-24
 */
class ProductListAdapter(
        private val context: Context
) : UltimateViewAdapter<ProductListAdapter.ProductViewHolder>() {

    val inflater = LayoutInflater.from(context)
    var products: List<Product> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateHeaderViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder? {
        throw UnsupportedOperationException()
    }

    override fun onBindViewHolder(holder: ProductViewHolder?, position: Int) {
        holder!!.titleView.text = products[position].title
        holder!!.priceView.text = products[position].price.toString()
        Glide.with(context)
                .load(products[position].image)
                .centerCrop()
                .crossFade()
                .into(holder!!.imageView)
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        throw UnsupportedOperationException()
    }

    override fun getItemCount(): Int = products.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(inflater.inflate(R.layout.list_item_product, parent, false))
    }

    override fun getAdapterItemCount(): Int = products.size

    override fun newHeaderHolder(view: View): ProductViewHolder? {
        return null
    }

    override fun newFooterHolder(view: View): ProductViewHolder? {
        return null
    }

    override fun generateHeaderId(position: Int): Long {
        return -1L
    }

    override fun onCreateViewHolder(parent: ViewGroup): ProductViewHolder {
        return ProductViewHolder(inflater.inflate(R.layout.list_item_product, parent, false))
    }

    class ProductViewHolder(
            val view: View
    ) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById(R.id.product_image) as ImageView
        val titleView = view.findViewById(R.id.product_title) as TextView
        val priceView = view.findViewById(R.id.product_price) as TextView
    }

}