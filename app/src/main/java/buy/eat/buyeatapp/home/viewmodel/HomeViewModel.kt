package buy.eat.buyeatapp.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import buy.eat.buyeatapp.home.data.HomeRepository
import buy.eat.buyeatapp.home.data.PostModel

class HomeViewModel(application: Application): AndroidViewModel(application) {
    private var homeRepository:HomeRepository?=null
    var postModelListLiveData : LiveData<List<PostModel>>?=null
    var createPostLiveData:LiveData<PostModel>?=null
    var deletePostLiveData:LiveData<Boolean>?=null

    init {
        homeRepository = HomeRepository()
        postModelListLiveData = MutableLiveData()
        createPostLiveData = MutableLiveData()
        deletePostLiveData = MutableLiveData()
    }

    fun fetchAllPosts(){
        postModelListLiveData = homeRepository?.fetchAllPosts()
    }

    fun createPost(postModel: PostModel){
        createPostLiveData = homeRepository?.createPost(postModel)
    }

    fun deletePost(id:Int){
        deletePostLiveData = homeRepository?.deletePost(id)
    }
}