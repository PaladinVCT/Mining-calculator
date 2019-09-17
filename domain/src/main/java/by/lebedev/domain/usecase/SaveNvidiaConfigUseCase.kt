package by.lebedev.domain.usecase

import android.content.Context
import by.lebedev.data.repository.database.DataBase
import by.lebedev.data.repository.database.entity.ConfigResponse
import by.lebedev.domain.collections.AmdDevices
import by.lebedev.domain.collections.NvidiaDevices
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class SaveNvidiaConfigUseCase:SaveConfigUseCase {

    private val array = arrayListOf<Int>()
    private val vendor = "Nvidia"

    override fun execute(context:Context,name:String) {

        for (i in 0 until NvidiaDevices.instance.list.size){
            array.add(i,NvidiaDevices.instance.list.get(i).count)
        }


        val complete = Completable.fromAction {
            DataBase.getInstance(context).db.configDao().insert(
                ConfigResponse(
                    Calendar.getInstance().timeInMillis,
                    name,
                    vendor,
                    array

                )
            )
        }
        val disposable = complete.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {}

    }
}