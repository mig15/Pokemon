package ru.androiddev.pokemon.presentation.di.module

import android.util.Log
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.androiddev.pokemon.remote.service.api.PokemonApi
import tech.thdev.network.flowcalladapterfactory.FlowCallAdapterFactory

@Module
class NetworkModule {

    @Provides
    fun provideOkHttpClientBuilder (): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            /*.addInterceptor { chain ->
                val request = chain.request()
                Log.d("---My Log---", "request: $request")

                val response = chain.proceed(request)
                Log.d("---My Log---", "response: $response")

                response
            }*/
            .addInterceptor(HttpLoggingInterceptor())
            .retryOnConnectionFailure(false)
            .protocols(listOf(Protocol.HTTP_2, Protocol.HTTP_1_1))
    }

    @Provides
    fun provideApiService(okHttpClientBuilder: OkHttpClient.Builder): PokemonApi {
        return makeRetrofit(
            okHttpClientBuilder.build(),
            PokemonApi::class.java,
        )
    }

    private fun<T> makeRetrofit(okHttpClient: OkHttpClient,
                                clazz: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .build()
        return retrofit.create(clazz)
    }
}