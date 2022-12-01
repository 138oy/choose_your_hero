package practice.effective.chooseyourhero

import javax.inject.Inject
import practice.effective.chooseyourhero.database.dao.HeroDao

class PushNotificationInteractor @Inject constructor(private val dao: HeroDao) {
    fun getRandomId(): String = dao.getRandomId()
}
