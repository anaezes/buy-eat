package buy.eat.buyeatapp.home.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import buy.eat.buyeatapp.network.ApiClient
import buy.eat.buyeatapp.network.ApiInterface

import retrofit2.*


class HomeRepository {
    private var apiInterface:ApiInterface?=null

    init {
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }


    fun fetchRandomRecipes():LiveData<List<RecipeModel>>{
        val data = MutableLiveData<List<RecipeModel>>()

        apiInterface?.fetchRandomRecipes()?.enqueue(object : Callback<Recipes>{

            override fun onFailure(call: Call<Recipes>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                    call: Call<Recipes>,
                    response: Response<Recipes>
            ) {

                val res = response.body()

                println(res);

                if (response.code() == 200 &&  res!=null){
                    data.value = res.Recipes
                }else{
                    data.value = null
                }

            }
        })

        return data
    }


        fun createRecipe(recipeModel: RecipeModel): LiveData<RecipeModel> {
            val data = MutableLiveData<RecipeModel>()

            apiInterface?.createRecipe(recipeModel)?.enqueue(object : Callback<RecipeModel> {
                override fun onFailure(call: Call<RecipeModel>, t: Throwable) {
                    data.value = null
                }

                override fun onResponse(call: Call<RecipeModel>, response: Response<RecipeModel>) {
                    val res = response.body()
                    if (response.code() == 201 && res != null) {
                        data.value = res
                    } else {
                        data.value = null
                    }
                }
            })

            return data

        }

        fun deleteRecipe(id: Int): LiveData<Boolean> {
            val data = MutableLiveData<Boolean>()

            apiInterface?.deleteRecipe(id)?.enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    data.value = false
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    data.value = response.code() == 200
                }
            })

            return data

        }
    }