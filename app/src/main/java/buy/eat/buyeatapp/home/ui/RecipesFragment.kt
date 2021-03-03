package buy.eat.buyeatapp.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import buy.eat.buyeatapp.R
import buy.eat.buyeatapp.home.data.RecipeModel
import buy.eat.buyeatapp.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_recipes.*

class RecipesFragment : Fragment() {

    private lateinit var vm:HomeViewModel
    private lateinit var adapter: HomeFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_recipes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vm = ViewModelProvider(this)[HomeViewModel::class.java]

        initAdapter()

        vm.fetchRandomRecipes()

        vm.recipeModelListLiveData?.observe(viewLifecycleOwner, Observer {
            if (it!=null){
                println(it);
                rv_recipes.visibility = View.VISIBLE
                adapter.setData(it as ArrayList<RecipeModel>)
            }else{
                showToast("Something went wrong")
            }
            progress_recipes.visibility = View.GONE
        })
    }

    private fun initAdapter() {
        adapter = HomeFragment(listener = requireActivity())
        rv_recipes.layoutManager = LinearLayoutManager(requireContext())
        rv_recipes.adapter = adapter
    }

    private fun showToast(msg:String){
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}

