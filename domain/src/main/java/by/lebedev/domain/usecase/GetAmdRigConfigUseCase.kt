package by.lebedev.domain.usecase

import android.content.Context
import android.util.Log
import by.lebedev.data.repository.database.DataBase
import by.lebedev.domain.entities.Config
import by.lebedev.domain.transformators.AmdConfigArrayResponseTransformator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetAmdRigConfigUseCase : GetConfigUseCase {
    override fun execute(context: Context, vendor: String): Single<ArrayList<Config>> {

        return DataBase.getInstance(context).db.configDao()
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                AmdConfigArrayResponseTransformator().execute(it)
            }

    }
}