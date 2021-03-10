package buy.eat.buyeatapp.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.view.get
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
    private lateinit var viewFragment: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewFragment =  inflater.inflate(R.layout.activity_recipes, container, false)

        recyclerView = viewFragment.findViewById(R.id.rv_recipes);

        setOnClickListeners();

        return viewFragment;
    }

    private fun setOnClickListeners() {
        var btn_vegetarian = viewFragment.findViewById<ImageButton>(R.id.btn_vegetarian);
        btn_vegetarian.setOnClickListener {
            btn_vegetarian.setSelected(!btn_vegetarian.isSelected());

            var data = adapter.getData()
            for (i in data.indices) {
                adapter.filterVegetarianRecipes(btn_vegetarian.isSelected(), recyclerView[i], i);
            }
        }

        var btn_dairyFree = viewFragment.findViewById<ImageButton>(R.id.btn_dairyFree);
        btn_dairyFree.setOnClickListener {
            btn_dairyFree.setSelected(!btn_dairyFree.isSelected());

            var data = adapter.getData()
            for (i in data.indices) {
                adapter.filterDairyFreeRecipes(btn_dairyFree.isSelected(), recyclerView[i], i);
            }
        }

        var btn_glutenFree = viewFragment.findViewById<ImageButton>(R.id.btn_glutenFree);
        btn_glutenFree.setOnClickListener {
            btn_glutenFree.setSelected(!btn_glutenFree.isSelected());
            var data = adapter.getData()
            for (i in data.indices) {
                adapter.filterGlutenFreeRecipes(btn_glutenFree.isSelected(), recyclerView[i], i);
            }
        }

        var btn_lowFodmap = viewFragment.findViewById<ImageButton>(R.id.btn_lowFodmap);
        btn_lowFodmap.setOnClickListener {
            btn_lowFodmap.setSelected(!btn_lowFodmap.isSelected());
            var data = adapter.getData()
            for (i in data.indices) {
                adapter.filterLowFodmapRecipes(btn_lowFodmap.isSelected(), recyclerView[i], i);
            }
        }

        var btn_veryHealthy = viewFragment.findViewById<ImageButton>(R.id.btn_veryHealthy);
        btn_veryHealthy.setOnClickListener {
            btn_veryHealthy.setSelected(!btn_veryHealthy.isSelected());
            var data = adapter.getData()
            for (i in data.indices) {
                adapter.filterVeryHealthRecipes(btn_veryHealthy.isSelected(), recyclerView[i], i);
            }
        }

        var btn_sustainable = viewFragment.findViewById<ImageButton>(R.id.btn_sustainable);
        btn_sustainable.setOnClickListener {
            btn_sustainable.setSelected(!btn_sustainable.isSelected());
            var data = adapter.getData()
            for (i in data.indices) {
                adapter.filterSustainableRecipes(btn_sustainable.isSelected(), recyclerView[i], i);
            }
        }

        var btn_cheap = viewFragment.findViewById<ImageButton>(R.id.btn_cheap);
        btn_cheap.setOnClickListener {
            btn_cheap.setSelected(!btn_cheap.isSelected());
            var data = adapter.getData()
            for (i in data.indices) {
                adapter.filterCheapRecipes(btn_cheap.isSelected(), recyclerView[i], i);
            }
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        var layoutManager = GridLayoutManager(requireContext(), 2);

        vm = ViewModelProvider(this)[HomeViewModel::class.java]

        vm.fetchRandomRecipes()

        vm.recipeModelListLiveData?.observe(viewLifecycleOwner, Observer {
            if (it!=null){
                println(it);
                rv_recipes.visibility = View.VISIBLE

                adapter = RecyclerViewAdapter(it as ArrayList<RecipeModel>);

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

