
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


class RecyclerViewAdapter(var courseDataArrayList: ArrayList<RecipeModel>) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>(){

    private lateinit var viewFragment: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :  RecyclerViewHolder {
        viewFragment = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.home_rv_item_view,
            parent,
            false
        );
        return RecyclerViewHolder(viewFragment);
    }


    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        var recyclerData = courseDataArrayList.get(position);
        holder.title.setText(recyclerData.title);

        Picasso.get().load(recyclerData.image)
            .error(R.drawable.ic_action_delete)
            .placeholder(R.drawable.ic_action_delete)
            .fit()
            .centerCrop(Gravity.CENTER)
            .into(holder.image)

        holder.view.setOnClickListener {
            onAlertDialog(viewFragment, recyclerData)
        }

    }

    override fun getItemCount(): Int {
        return courseDataArrayList.size;
    }


    class RecyclerViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = itemView.findViewById(R.id.tv_home_item_title)
        var image: ImageView = itemView.findViewById(R.id.tv_home_item_img);
    }

    fun onAlertDialog(view: View, recyclerData: RecipeModel) {

        val dialogBuilder = AlertDialog.Builder(view.context)
        val dialogView: View = LayoutInflater.from(view.context).inflate(R.layout.view_recipe, null)
        dialogBuilder.setView(dialogView)

        val imageview = dialogView.findViewById<View>(R.id.recipe_image) as ImageView

        Picasso.get().load(recyclerData.image)
            .error(R.drawable.ic_action_delete)
            .placeholder(R.drawable.ic_action_delete)
            .fit()
            .centerCrop(Gravity.CENTER)
            .into(imageview)

        val title = dialogView.findViewById<View>(R.id.recipe_title) as TextView
        title.setText(recyclerData.title)

        val description = dialogView.findViewById<View>(R.id.recipe_description) as TextView
        description.setText(removeHTMLTags(recyclerData.instructions))

        val alertDialog = dialogBuilder.create()
        alertDialog.window?.setBackgroundDrawableResource(R.drawable.round_corner);
        alertDialog.show()

        val btn_back = dialogView.findViewById(R.id.back_button) as Button
        btn_back.setOnClickListener {
            alertDialog.hide();
        }
    }

    fun removeHTMLTags(str: String?): String =
        if (str.isNullOrEmpty()) "ups ... we are cooking this recipe for you."
        else str.replace("\\<.*?\\>".toRegex(), "")
}