package com.example.cryptoapp.domain.use_case.get_coin

import com.example.cryptoapp.common.Resource
import com.example.cryptoapp.data.remote.dto.toCoin
import com.example.cryptoapp.data.remote.dto.toCoinDetail
import com.example.cryptoapp.domain.model.Coin
import com.example.cryptoapp.domain.model.CoinDetail
import com.example.cryptoapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId : String) : Flow<Resource<CoinDetail>> = flow{
        try {
            emit(Resource.Loading())
            val coinDetail = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coinDetail))
        } catch (e : HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e : IOException) {
            emit(Resource.Error("Could not reach server. Check your internet connection"))
        }
    }
}