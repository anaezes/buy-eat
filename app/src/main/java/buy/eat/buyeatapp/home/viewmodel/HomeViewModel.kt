package buy.eat.buyeatapp.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import buy.eat.buyeatapp.home.data.HomeRepository
import buy.eat.buyeatapp.home.data.RecipeModel
import buy.eat.buyeatapp.home.data.Recipes

class HomeViewModel(application: Application): AndroidViewModel(application) {
    private var homeRepository:HomeRepository?=null
    var recipeModelListLiveData : List<RecipeModel>?=null
    var createRecipeLiveData:LiveData<RecipeModel>?=null
    var deleteRecipeLiveData:LiveData<Boolean>?=null

    init {
        homeRepository = HomeRepository()
        //recipeModelListLiveData = MutableLiveData()
        createRecipeLiveData = MutableLiveData()
        deleteRecipeLiveData = MutableLiveData()
    }

    fun fetchRandomRecipes(){
        //recipeModelListLiveData = homeRepository?.fetchRandomRecipes()
    }

    fun createRecipe(RecipeModel: RecipeModel){
        createRecipeLiveData = homeRepository?.createRecipe(RecipeModel)
    }

    fun deleteRecipe(id:Int){
        deleteRecipeLiveData = homeRepository?.deleteRecipe(id)
    }
}