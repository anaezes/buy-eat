package buy.eat.buyeatapp.network

import buy.eat.buyeatapp.home.data.RecipeModel
import buy.eat.buyeatapp.home.data.Recipes
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @Headers(
        "x-rapidapi-key:a78969ea49mshc2647ebb845c960p1894a5jsnab47e145dc66",
        "x-rapidapi-host:spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
    @GET("recipes/random?number=1")
    fun fetchRandomRecipes(): Call<Recipes>

    @POST("posts")
    fun createRecipe(@Body recipeModel: RecipeModel):Call<RecipeModel>

    @DELETE("posts/{id}")
    fun deleteRecipe(@Path("id") id:Int):Call<String>
}