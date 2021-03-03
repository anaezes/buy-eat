package buy.eat.buyeatapp.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import buy.eat.buyeatapp.R
import buy.eat.buyeatapp.home.data.RecipeModel
import buy.eat.buyeatapp.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_recipes.*

class RecipesFragment : Fragment() {

    private lateinit var vm: HomeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewFragment: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewFragment =  inflater.inflate(R.layout.activity_recipes, container, false)

        return viewFragment;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView = viewFragment.findViewById(R.id.rv_recipes)
        var layoutManager = GridLayoutManager(requireContext(), 2);

        vm = ViewModelProvider(this)[HomeViewModel::class.java]

        vm.fetchRandomRecipes()

        vm.recipeModelListLiveData?.observe(viewLifecycleOwner, Observer {
            if (it!=null){
                println(it);
                rv_recipes.visibility = View.VISIBLE

                var adapter = RecyclerViewAdapter(it as ArrayList<RecipeModel>);

                recyclerView?.setLayoutManager(layoutManager);
                recyclerView?.setAdapter(adapter);

            }else{
                showToast("Something went wrong")
            }
           progress_recipes.visibility = View.GONE
        })
    }

    private fun showToast(msg:String){
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}

