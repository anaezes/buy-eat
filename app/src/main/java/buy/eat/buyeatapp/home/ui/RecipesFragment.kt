package buy.eat.buyeatapp.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
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
    private lateinit var btnMap: Map<String,ImageButton?>

    private var buttonsSelected: ArrayList<ImageButton> = ArrayList<ImageButton>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewFragment =  inflater.inflate(R.layout.activity_recipes, container, false)

        recyclerView = viewFragment.findViewById(R.id.rv_recipes);

        return viewFragment;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var layoutManager = GridLayoutManager(requireContext(), 2);

        vm = ViewModelProvider(this)[HomeViewModel::class.java]

        vm.fetchRandomRecipes()

        vm.recipeModelListLiveData?.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                println(it);
                rv_recipes.visibility = View.VISIBLE

                adapter = RecyclerViewAdapter(HashSet<RecipeModel>(it));

                recyclerView?.setLayoutManager(layoutManager);
                recyclerView?.setAdapter(adapter);

                setOnClickListeners();

            } else {
                Utils.showToast(requireContext(),"Something went wrong")
            }
            progress_recipes.visibility = View.GONE
        })
    }

    private fun setupMapBtns() {
        btnMap = mapOf( "vegetarian" to viewFragment.findViewById<ImageButton>(R.id.btn_vegetarian),
                        "dairyFree" to viewFragment.findViewById<ImageButton>(R.id.btn_dairyFree),
                        "glutenFree" to viewFragment.findViewById<ImageButton>(R.id.btn_glutenFree),
                        "lowFodmap" to viewFragment.findViewById<ImageButton>(R.id.btn_lowFodmap),
                        "veryHealthy" to viewFragment.findViewById<ImageButton>(R.id.btn_veryHealthy),
                        "sustainable" to viewFragment.findViewById<ImageButton>(R.id.btn_sustainable),
                        "cheap" to viewFragment.findViewById<ImageButton>(R.id.btn_cheap))
    }

    private fun setOnClickListeners() {

        var btn_all = viewFragment.findViewById<ImageButton>(R.id.btn_all);
        btn_all.setSelected(true); //by default is selected

        btn_all.setOnClickListener {
            if(!btn_all.isSelected){
                btn_all.isSelected = true;

                for (btn in buttonsSelected) {
                    btn.isSelected = false
                }
                buttonsSelected = ArrayList<ImageButton>();
                adapter.showAllRecipes();
            }
        }

        setupMapBtns();

        for(it in btnMap.iterator()) {
            setBtnOnClickListener(it.key, it.value)
        }
    }

    private fun setBtnOnClickListener(filter: String, button: ImageButton?) {

        if(button == null)
            return;

        button.setOnClickListener {
            button.isSelected = !button.isSelected;

            if(button.isSelected){
                buttonsSelected.add(button);
                adapter.hideRecipes(filter);
            }
            else {
                if(buttonsSelected.contains(button))
                    buttonsSelected.remove(button);
                adapter.showRecipes(filter);
            }

            if(buttonsSelected.isEmpty()){
                btn_all.isSelected = true;
                adapter.showAllRecipes();
            }
            else {
                btn_all.isSelected = false;
            }
        }
    }
}

