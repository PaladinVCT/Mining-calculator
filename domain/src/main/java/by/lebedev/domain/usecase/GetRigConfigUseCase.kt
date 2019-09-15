package by.lebedev.domain.usecase

class GetRigConfigUseCase:GetConfigUseCase {
    override fun execute() {

        val d = DataBase.getInstance(this).db.accountDao()
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                if (result.isEmpty()) {
                    noAccountText.visibility = View.VISIBLE
                } else {
                    noAccountText.visibility = View.INVISIBLE
                    setupRecycler(result)
                    accountLocalList.list = result as ArrayList<Account>
                }
                progressAccountLoad.visibility = View.GONE
            }

    }
}