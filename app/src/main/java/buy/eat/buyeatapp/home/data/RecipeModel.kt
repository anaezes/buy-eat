package buy.eat.buyeatapp.home.data

import com.google.gson.annotations.SerializedName

data class Recipes(
    @SerializedName("recipes")
    var Recipes: List<RecipeModel>?
)

data class RecipeModel(
    var vegetarian: Boolean,
    var vegan: Boolean,
    var glutenFree: Boolean,
    var dairyFree: Boolean,
    var veryHealthy: Boolean,
    var cheap: Boolean,
    var veryPopular: Boolean,
    var sustainable: Boolean,
    var weightWatcherSmartPovals: Int? = 0,
    var gapsval: String? = "",
    var lowFodmap: Boolean,
    var aggregateLikes: Int? = 0,
    var spoonacularScore: Double? = 0.0,
    var healthScore: Double? = 0.0,
    var creditsText: String? = "",
    var license: String? = "",
    var sourceName: String? = "",
    var pricePerServing: Double? = 0.0,
    @SerializedName("extendedIngredients")
    var extendedIngredients:List<ExtendedIngredient>?,
    var id: Int,
    var title: String? = "",
    var readyInMinutes: Int? = 0,
    var servings: Int? = 0,
    var sourceUrl: String? = "",
    var image: String? = "",
    var imageType: String? = "",
    var summary: String? = "",
    //List<Object> cuisines;
    //List<String> dishTypes;
    //List<String> diets;
    //List<String> occasions;
    var instructions: String? = "",
    //var analyzedInstructions : List<AnalyzedInstruction>,
    //Object originalId;
    var spoonacularSourceUrl: String? = ""
)

class Us {
    var amount = 0.0
    var unitShort: String? = null
    var unitLong: String? = null
}

class Metric {
    var amount = 0.0
    var unitShort: String? = null
    var unitLong: String? = null
}

class Measures {
    var us: Us? = null
    var metric: Metric? = null
}

class ExtendedIngredient {
    var id = 0
    var aisle: String? = null
    var image: String? = null
    var consistency: String? = null
    var name: String = ""
    var nameClean: String? = null
    var original: String? = null
    var originalString: String? = null
    var originalName: String? = null
    var amount = 0.0
    var unit: String = ""
    //var meta: List<String>? = null
    //var metaInformation: List<String>? = null
    //var measures: Measures? = null
}

