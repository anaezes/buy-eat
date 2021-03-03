
package buy.eat.buyeatapp.home.ui

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import buy.eat.buyeatapp.R
import buy.eat.buyeatapp.home.data.RecipeModel
import com.squareup.picasso.Picasso
import java.util.*


class RecyclerViewAdapter(var courseDataArrayList: ArrayList<RecipeModel>) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :  RecyclerViewHolder {
        var view = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.home_rv_item_view,
            parent,
            false
        );
        return RecyclerViewHolder(view);
    }


    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        // Set the data to textview and imageview.
        var recyclerData = courseDataArrayList.get(position);
        holder.title.setText(recyclerData.title);
        //holder.summary.setText(recyclerData.summary);
        //holder.image.setImageResource(recyclerData);

        recyclerData.image

        Picasso.get().load(recyclerData.image)
            .error(R.drawable.ic_action_delete)
            .placeholder(R.drawable.ic_action_delete)
            //.fit()
            .resize(holder.image.layoutParams.height, holder.image.layoutParams.height)
            .centerCrop(Gravity.CENTER)
            .into(holder.image)

    }

    override fun getItemCount(): Int {
        return courseDataArrayList.size;
    }


    class RecyclerViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = itemView.findViewById(R.id.tv_home_item_title)
        var image: ImageView = itemView.findViewById(R.id.tv_home_item_img);
    }


/*    class CropSquareTransformation : Transformation
    {

        override fun transform(source: Bitmap): Bitmap {

            val size: Int = Math.min(source.getWidth(), source.getHeight())
            val x: Int = (source.getWidth() - size) / 2
            val y: Int = (source.getHeight() - size) / 2
            val result = Bitmap.createBitmap(source, x, y, size, size)
            if (result != source) {
                source.recycle()
            }
            return result
        }

        override fun key(): String {
            return "square()";
        }
    }*/

}