package by.lebedev.domain.usecase

import android.content.Context
import by.lebedev.data.repository.database.DataBase
import by.lebedev.data.repository.database.entity.ConfigResponse
import by.lebedev.domain.collections.AmdDevices
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class SaveAmdConfigUseCase:SaveConfigUseCase {

    private val array = arrayListOf<Int>()

    override fun execute(context:Context,name:String) {

        for (i in 0 until AmdDevices.instance.list.size){
            array.add(i,AmdDevices.instance.list.get(i).count)
        }


        val complete = Completable.fromAction {
            DataBase.getInstance(context).db.configDao().insert(
                ConfigResponse(
                    Calendar.getInstance().timeInMillis,
                    name,
                    "Amd",
                    array

                )
            )
        }
        val disposable = complete.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {}

    }
}