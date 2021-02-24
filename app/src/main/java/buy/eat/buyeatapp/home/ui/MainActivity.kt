package buy.eat.buyeatapp.home.ui

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import buy.eat.buyeatapp.R
import buy.eat.buyeatapp.home.data.RecipeModel
import buy.eat.buyeatapp.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.create_recipe_dialog.view.*

class MainActivity : AppCompatActivity(), HomeAdapter.HomeListener {

    private lateinit var vm:HomeViewModel
    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(this)[HomeViewModel::class.java]

        initAdapter()

        vm.fetchRandomRecipes()

        vm.recipeModelListLiveData?.observe(this, Observer {
            if (it!=null){
                println(it);
                rv_home.visibility = View.VISIBLE
                adapter.setData(it as ArrayList<RecipeModel>)
            }else{
                showToast("Something went wrong")
            }
            progress_home.visibility = View.GONE
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_create_recipe -> showCreatePOstDialog()
        }
        return true
    }

    private fun showCreatePOstDialog() {
        val dialog = Dialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.create_recipe_dialog, null)
        dialog.setContentView(view)

        var title = ""
        var body = ""

        view.btn_submit.setOnClickListener {
            title = view.et_title.text.toString().trim()
            body = view.et_body.text.toString().trim()

            if (title.isNotEmpty() && body.isNotEmpty()){
                val recipeModel = RecipeModel()
                recipeModel.id = 1
                recipeModel.title = title
                recipeModel.summary = body

                vm.createRecipe(recipeModel)

                vm.createRecipeLiveData?.observe(this, Observer {
                    if (it!=null){
                        adapter.addData(recipeModel)
                        rv_home.smoothScrollToPosition(0)
                    }else{
                        showToast("Cannot create recipe at the moment")
                    }
                    dialog.cancel()
                })

            }else{
                showToast("Please fill data carefully!")
            }
        }

        dialog.show()

        val window = dialog.window
        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)

    }

    private fun initAdapter() {
        adapter = HomeAdapter(this)
        rv_home.layoutManager = LinearLayoutManager(this)
        rv_home.adapter = adapter
    }

    override fun onItemDeleted(recipeModel: RecipeModel, position: Int) {
        recipeModel.id?.let { vm.deleteRecipe(it) }
        vm.deleteRecipeLiveData?.observe(this, Observer {
            if (it!=null){
                adapter.removeData(position)
            }else{
                showToast("Cannot delete recipe at the moment!")
            }
        })

    }

    private fun showToast(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

}
