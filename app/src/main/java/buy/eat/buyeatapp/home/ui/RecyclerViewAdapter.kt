
package buy.eat.buyeatapp.home.ui

import android.app.AlertDialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import buy.eat.buyeatapp.R
import buy.eat.buyeatapp.home.data.RecipeModel
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.HashSet


class RecyclerViewAdapter(var displayRecipes: HashSet<RecipeModel>) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>(){

    private lateinit var viewFragment: View
    private var hiddenRecipes: HashSet<RecipeModel> = HashSet<RecipeModel>()
    private var hiddenRecipesLabel: ArrayList<String> = ArrayList<String>()

    class RecyclerViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var id: Int = 0
        var title: TextView = itemView.findViewById(R.id.tv_home_item_title)
        var image: ImageView = itemView.findViewById(R.id.tv_home_item_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :  RecyclerViewHolder {
        viewFragment = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.home_rv_item_view,
            parent,
            false
        );
        return RecyclerViewHolder(viewFragment);
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        var recyclerData = displayRecipes.elementAt(position);
        holder.title.setText(recyclerData.title);
        holder.id = recyclerData.id;

        Picasso.get().load(recyclerData.image)
            .error(R.drawable.cooking)
            .placeholder(R.drawable.cooking)
            .fit()
            .centerCrop(Gravity.CENTER)
            .into(holder.image)

        holder.view.setOnClickListener {
            Utils.onAlertDialog(viewFragment, recyclerData)
        }

    }

    override fun getItemCount(): Int {
        return displayRecipes.size;
    }

    private fun applyFilter(filter: String) {
        when (filter) {
            "vegetarian" -> {
                for (it in displayRecipes.indices) {
                    if (!displayRecipes.elementAt(it).vegetarian) remove(it)
                }
            }
            "dairyFree" -> {
                for (it in displayRecipes.indices) {
                    if (!displayRecipes.elementAt(it).dairyFree) remove(it)
                }
            }
            "glutenFree" -> {
                for (it in displayRecipes.indices) {
                    if (!displayRecipes.elementAt(it).glutenFree) remove(it)
                }
            }
            "lowFodmap" -> {
                for (it in displayRecipes.indices) {
                    if (displayRecipes.elementAt(it).lowFodmap) remove(it)
                }
            }
            "veryHealthy" -> {
                for (it in displayRecipes.indices) {
                    if (!displayRecipes.elementAt(it).veryHealthy) remove(it)
                }
            }
            "sustainable" -> {
                for (it in displayRecipes.indices) {
                    if (!displayRecipes.elementAt(it).sustainable) remove(it)
                }
            }
            "cheap" -> {
                for (it in displayRecipes.indices) {
                    if (!displayRecipes.elementAt(it).cheap) remove(it)
                }
            }
        }
    }

    private fun remove(position: Int) {
        hiddenRecipes.add(displayRecipes.elementAt(position))
    }

    fun hideRecipes(filter: String) {

        applyFilter(filter)

        hiddenRecipesLabel.add(filter);
        displayRecipes.removeAll(hiddenRecipes)

        notifyDataSetChanged()
    }

    fun showRecipes(filter: String) {
        hiddenRecipesLabel.remove(filter);

        displayRecipes.addAll(hiddenRecipes)
        hiddenRecipes.clear();

        for (it in hiddenRecipesLabel) {
            applyFilter(it)
        }

        displayRecipes.removeAll(hiddenRecipes)
        notifyDataSetChanged()
    }

    fun showAllRecipes() {
        displayRecipes.addAll(hiddenRecipes)

        hiddenRecipes.clear();
        hiddenRecipesLabel = ArrayList<String>();

        notifyDataSetChanged()
    }
}