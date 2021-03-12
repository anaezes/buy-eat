package buy.eat.buyeatapp.home.data

import com.google.gson.annotations.SerializedName

data class Recipes(
        @SerializedName("recipes")
        var Recipes: List<RecipeModel>?
)

data class RecipeModel(
         var vegetarian:Boolean,
         var vegan:Boolean,
         var glutenFree:Boolean,
         var dairyFree:Boolean,
         var veryHealthy:Boolean,
         var cheap:Boolean,
         var veryPopular:Boolean,
         var sustainable:Boolean,
         var weightWatcherSmartPovals:Int?=0,
         var gapsval:String?="",
         var lowFodmap:Boolean,
         var aggregateLikes:Int?=0,
         var spoonacularScore:Double?=0.0,
         var healthScore:Double?=0.0,
         var creditsText:String?="",
         var license:String?="",
         var sourceName:String?="",
         var pricePerServing:Double?=0.0,
         //List<ExtendedIngredient> extendedIngredients:List<AnalyzedInstruction>,
         var id:Int,
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