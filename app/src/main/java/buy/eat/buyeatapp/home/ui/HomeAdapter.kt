package buy.eat.buyeatapp.home.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import buy.eat.buyeatapp.R
import buy.eat.buyeatapp.home.data.RecipeModel
import kotlinx.android.synthetic.main.home_rv_item_view.view.*

class HomeAdapter(var listener: HomeListener) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(){

    private var data : ArrayList<RecipeModel>?=null

    interface HomeListener{
        fun onItemDeleted(recipeModel: RecipeModel, position: Int)
    }

    fun setData(list: ArrayList<RecipeModel>){
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.home_rv_item_view, parent, false))
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bindView(item)
        holder.itemView.img_delete.setOnClickListener {
            item?.let { it1 ->
                //listener.onItemDeleted(it1, position)
            }
        }
    }

    fun addData(recipeModel: RecipeModel) {
        data?.add(0,recipeModel)
        notifyItemInserted(0)
    }

    fun removeData(position: Int) {
        data?.removeAt(position)
        notifyDataSetChanged()
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindView(item: RecipeModel?) {

            itemView.tv_home_item_title.text = item?.title
            itemView.tv_home_item_body.text = item?.summary
        }

    }

}