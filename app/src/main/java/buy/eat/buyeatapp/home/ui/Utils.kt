package buy.eat.buyeatapp.home.ui

import android.app.AlertDialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import buy.eat.buyeatapp.R
import buy.eat.buyeatapp.home.data.RecipeModel
import com.squareup.picasso.Picasso

class Utils {

    companion object {

        fun showToast(context: Context, msg: String){
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

        fun onAlertDialog(view: View, recyclerData: RecipeModel) {

            val dialogBuilder = AlertDialog.Builder(view.context)
            val dialogView: View = LayoutInflater.from(view.context).inflate(R.layout.view_recipe, null)
            dialogBuilder.setView(dialogView)

            val imageview = dialogView.findViewById<View>(R.id.recipe_image) as ImageView

            Picasso.get().load(recyclerData.image)
                .error(R.drawable.cooking)
                .placeholder(R.drawable.cooking)
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

        private fun removeHTMLTags(str: String?): String =
            if (str.isNullOrEmpty()) "ups ... we are cooking this recipe for you."
            else str.replace("\\<.*?\\>".toRegex(), "")

    }
}