package com.iscoding.mytask.di.koin

import com.google.gson.Gson
import com.iscoding.mytask.data.remote.PostRemoteDataSource
import com.iscoding.mytask.data.remote.RetryInterceptor
import com.iscoding.mytask.data.repository.PostsRepositoryImp
import com.iscoding.mytask.domain.Constatns
import com.iscoding.mytask.domain.repository.PostsRepository
import com.iscoding.mytask.domain.usecases.GetAllPosts
import com.iscoding.mytask.domain.usecases.GetPost
import com.iscoding.mytask.domain.usecases.PostsUseCase
import com.iscoding.mytask.presentation.screens.allpostsscreen.AllPostsViewModel
import com.iscoding.mytask.presentation.screens.detailsScreen.DetailsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {

    // for properties with read from file or just properties u need to define them like that
    single(named("apiUrl")) {
        getProperty("api.url") ?: "default_api_url"
    }

    single(named("apiKey")) {
        getProperty("api.key") ?: "default_api_key"
    }
    // define the HttpLoggingInterceptor
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    // define the RetryInterceptor
    single {
        RetryInterceptor(maxRetries = 3, retryIntervalSeconds = 2)
    }

    // define the OkHttpClient
    single {
        OkHttpClient.Builder().addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get<RetryInterceptor>()).build()
    }

    // define the Retrofit (the retrofit interface PostRemoteDataSource)
    single {
        Retrofit.Builder().baseUrl(Constatns.BASE_URL)
//            .baseUrl(get<String>(named("apiUrl")))
            .addConverterFactory(GsonConverterFactory.create(Gson())).client(get<OkHttpClient>())
            .build().create(PostRemoteDataSource::class.java)
    }

    single<PostsRepository> {
        PostsRepositoryImp(get())
    }
    // another way for bind the interface with the IMPL
//    single {
//        PostsRepositoryImp(get())
//    } bind PostsRepository::class
//
    //define the whole use case
    single {
        PostsUseCase(
            getAllPosts = get<GetAllPosts>(), getPost = get<GetPost>()
        )
    }

    single {
        GetAllPosts(get())
    }

    single {
        GetPost(get())
    }
// define the viewmodels
    viewModel {
        AllPostsViewModel(useCase = get<PostsUseCase>())
    }
    viewModel {
        DetailsViewModel(useCase = get<PostsUseCase>())
    }
}