package buy.eat.buyeatapp.home.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BulletSpan
import android.text.style.StyleSpan
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import buy.eat.buyeatapp.R
import buy.eat.buyeatapp.home.data.ExtendedIngredient
import buy.eat.buyeatapp.home.data.RecipeModel
import com.squareup.picasso.Picasso


class Utils {

    companion object {

        fun showToast(context: Context, msg: String){
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

        @RequiresApi(Build.VERSION_CODES.P)
        fun onAlertDialog(view: View, recyclerData: RecipeModel) {

            val dialogBuilder = AlertDialog.Builder(view.context)
            val dialogView: View = LayoutInflater.from(view.context).inflate(
                R.layout.view_recipe,
                null
            )
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

            var ingredientsText = getIngredientsText(recyclerData.extendedIngredients!!, view);
            val ingredients = dialogView.findViewById<View>(R.id.recipe_ingredients) as TextView
            ingredients.setText(ingredientsText, TextView.BufferType.SPANNABLE);

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

        @RequiresApi(Build.VERSION_CODES.P)
        private fun getIngredientsText(ingredientsList: List<ExtendedIngredient>, view: View): SpannableStringBuilder {
            var arr = ArrayList<String>()

            arr.add("Ingredients: \n")
            for(i in ingredientsList){
                var amount = (i.amount).format(2) //todo
                arr.add("  $amount ${i.unit} ${i.name}");
            }

            return  addBullet(arr, view)
        }

        @SuppressLint("NewApi")
        private fun addBullet(arr: ArrayList<String>, view: View): SpannableStringBuilder {
            val ssb = SpannableStringBuilder()
            for (i in 0 until arr.size) {
                if(i == 0) {
                    val line = arr[i]
                    val ss = SpannableString(line)
                    ss.setSpan(StyleSpan(Typeface.BOLD), 0, line.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    ssb.append(ss)
                    continue
                }

                val line = arr[i]
                val ss = SpannableString(line)
                ss.setSpan(
                    BulletSpan(20, ContextCompat.getColor(view.context, R.color.green), 10), 0, line.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                ssb.append(ss)
                if (i + 1 < arr.size) ssb.append("\n")
            }
            return ssb
        }

        fun Double.format(digits: Int) = "%.${digits}f".format(this)

        private fun removeHTMLTags(str: String?): String =
            if (str.isNullOrEmpty()) "ups ... we are cooking this recipe for you."
            else str.replace("\\<.*?\\>".toRegex(), "")
    }
}