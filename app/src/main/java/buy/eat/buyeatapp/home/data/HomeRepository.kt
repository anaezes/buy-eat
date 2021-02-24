package buy.eat.buyeatapp.home.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import buy.eat.buyeatapp.network.ApiClient
import buy.eat.buyeatapp.network.ApiInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*
import ru.gildor.coroutines.retrofit.awaitResponse
import ru.gildor.coroutines.retrofit.awaitResult
import ru.gildor.coroutines.retrofit.getOrNull

class HomeRepository {
    private var apiInterface:ApiInterface?=null

    init {
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }

    suspend fun fetchRandomRecipes(): List<RecipeModel>? {
        val data = MutableLiveData<List<RecipeModel>>()

        println("pim");

        val recipes: Recipes? = apiInterface?.fetchRandomRecipes()?.awaitResult()?.getOrNull()



        /*// Works for non-nullable type
        val nullableResult = api.getUser("username").awaitResult().getOrNull()

        try {
            // Wait (suspend) for response
            val response: Response<Recipes> = apiInterface?.fetchRandomRecipes()!!.awaitResponse()
            if (response.isSuccessful()) {
                // Now we can work with response object
                //println("User ${response.body().name} loaded")
            }
        } catch (e: Throwable) {
            // All other exceptions (non-http)
            log("Something broken", e)
        }*/

            /*   apiInterface?.fetchRandomRecipes()?.enqueue(object : Callback<Recipes>{

            override fun onFailure(call: Call<Recipes>, t: Throwable) {
                println("pam: $data");
                data.value = null
            }

            override fun onResponse(
                call: Call<Recipes>,
                response: Response<Recipes>
            ) {
                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    data.value = res.recipes
                    println("data: " + data.value);
                }else{
                    data.value = null
                }
            }
        })*/

            println(recipes);
            return recipes?.Recipes
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