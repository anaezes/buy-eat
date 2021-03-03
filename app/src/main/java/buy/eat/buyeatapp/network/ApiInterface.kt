package buy.eat.buyeatapp.network

import buy.eat.buyeatapp.BuildConfig
import buy.eat.buyeatapp.home.data.RecipeModel
import buy.eat.buyeatapp.home.data.Recipes
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @Headers(BuildConfig.API_KEY, BuildConfig.API_HOST)
    @GET("recipes/random?number=4")
    fun fetchRandomRecipes(): Call<Recipes>

    @POST("posts")
    fun createRecipe(@Body recipeModel: RecipeModel):Call<RecipeModel>

    @DELETE("posts/{id}")
    fun deleteRecipe(@Path("id") id:Int):Call<String>
}