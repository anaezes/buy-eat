package buy.eat.buyeatapp.home.data

import com.google.gson.annotations.SerializedName

data class Recipes(
        @SerializedName("recipes")
        var Recipes: List<RecipeModel>?
)

data class RecipeModel(
         var vegetarian:Boolean?=false,
         var vegan:Boolean?=false,
         var glutenFree:Boolean?=false,
         var dairyFree:Boolean?=false,
         var veryHealthy:Boolean?=false,
         var cheap:Boolean?=false,
         var veryPopular:Boolean?=false,
         var sustainable:Boolean?=false,
         var weightWatcherSmartPovals:Int?=0,
         var gapsval:String?="",
         var lowFodmap:Boolean?=false,
         var aggregateLikes:Int?=0,
         var spoonacularScore:Double?=0.0,
         var healthScore:Double?=0.0,
         var creditsText:String?="",
         var license:String?="",
         var sourceName:String?="",
         var pricePerServing:Double?=0.0,
         //List<ExtendedIngredient> extendedIngredients:List<AnalyzedInstruction>,
         var id:Int?=0,
         var title:String?="",
         var readyInMinutes:Int?=0,
         var servings:Int?=0,
         var sourceUrl:String?="",
         var image:String?="",
         var imageType:String?="",
         var summary:String?="",
         //List<Object> cuisines;
         //List<String> dishTypes;
         //List<String> diets;
         //List<String> occasions;
         var instructions:String?="",
         //var analyzedInstructions : List<AnalyzedInstruction>,
         //Object originalId;
         var spoonacularSourceUrl:String?=""
)