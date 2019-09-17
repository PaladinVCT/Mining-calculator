package by.lebedev.domain.usecase

import android.content.Context
import by.lebedev.data.repository.database.DataBase
import by.lebedev.data.repository.database.entity.ConfigResponse
import by.lebedev.domain.collections.AmdDevices
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.completable.CompletableFromAction
import io.reactivex.schedulers.Schedulers
import java.util.*

class DeleteSelectedConfigUseCase : DeleteConfigUseCase {
    override fun execute(context: Context, name: String, vendor: String) {

        CompletableFromAction {
            DataBase.getInstance(context).db.configDao()
                .delete(name, vendor)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

}